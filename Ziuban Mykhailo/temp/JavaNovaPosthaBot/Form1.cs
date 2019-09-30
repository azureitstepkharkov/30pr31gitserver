using JavaNovaPosthaBot.MyClasses;
using JavaNovaPosthaBot.MyContext;
using JavaNovaPosthaBot.MyKeyboards;
using JavaNovaPosthaBot.NovaPoshtaRequest;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Telegram.Bot;
using Telegram.Bot.Args;
using Telegram.Bot.Types.Enums;

namespace JavaNovaPosthaBot
{
    public partial class Form1 : Form
    {
        private static TelegramBotClient BOT;

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            BOT = new TelegramBotClient("966313623:AAElr8O-1mQrtqA__JIctEzBQbInFXo8Psc");
            BOT.StartReceiving();
            BOT.OnMessage += BotOnMessage;
            BOT.OnCallbackQuery += BOT_OnCallbackQuery;
            bwReminder.RunWorkerAsync();
        }

        private async void BOT_OnCallbackQuery(object sender, CallbackQueryEventArgs e)
        {
            using (Context db = new Context())
            {
                User user = db.Users.First(x => x.Telegram == e.CallbackQuery.From.Id);
                Reminder reminder = new Reminder();
                reminder.TTN = e.CallbackQuery.Data.Substring(0, 14);
                reminder.UserId = Convert.ToInt32( e.CallbackQuery.Data.Substring(e.CallbackQuery.Data.IndexOf("|us")+3, e.CallbackQuery.Data.IndexOf("/us")- e.CallbackQuery.Data.IndexOf("|us") -3));
                reminder.StatusId = Convert.ToInt32(e.CallbackQuery.Data.Substring(e.CallbackQuery.Data.IndexOf("st:") + 3));
                db.Reminders.Add(reminder);
                await db.SaveChangesAsync();
                await BOT.SendTextMessageAsync(e.CallbackQuery.From.Id, $"Готово{Environment.NewLine}В случае изменния статуса вам прийдет уведомление");
            }
        }

        private async void BotOnMessage(object sender, MessageEventArgs e)
        {
            using (Context db =new Context())
            {
                if (db.Users.Any(x=>x.Telegram==e.Message.From.Id))
                {
                    User user = db.Users.First(x => x.Telegram == e.Message.From.Id);
                    
                    switch (user.Status)
                    {
                        case 1:
                            {
                                if (e.Message.Type == MessageType.Contact)
                                {
                                    user.Name = e.Message.Contact.LastName + " " + e.Message.Contact.FirstName;
                                    user.Status = 2;
                                    await db.SaveChangesAsync();
                                    await BOT.SendTextMessageAsync(e.Message.From.Id, $"{user.Name}, теперь вы можете отслеживать свои посылки ", replyMarkup: Keyboards.MainKeyboard());

                                }
                                else
                                {
                                    await BOT.SendTextMessageAsync(e.Message.From.Id, "Сейчас я ожидаю контактов");
                                }
                                break;
                            }
                        case 2:
                            {
                                if (e.Message.Text == "Треккинг")
                                {
                                    user.Status = 3;
                                    await db.SaveChangesAsync();
                                    await BOT.SendTextMessageAsync(e.Message.From.Id, $"Отправьте номер ТТН");
                                }
                                else if(e.Message.Text == "Мои посылки")
                                {
                                    List<Reminder> list =  db.Reminders.Where(x => x.UserId == user.Id).ToList();
                                    if (list.Count != 0)
                                    {
                                        string answer = $"Вот список отслеживаемых посылок:{Environment.NewLine}";
                                        foreach (Reminder item in list)
                                        {
                                            answer += $"<i>{item.TTN}</i>{Environment.NewLine}";
                                        }
                                        await BOT.SendTextMessageAsync(e.Message.From.Id, answer, ParseMode.Html);
                                    }
                                    else
                                    {
                                        await BOT.SendTextMessageAsync(e.Message.From.Id, "У вас нет отслеживаемых посылок");
                                    }
                                }
                                else
                                {
                                    await BOT.SendTextMessageAsync(e.Message.From.Id, "Сейчас ожидаю нажатия кнопок главного меню");
                                }
                                break;
                            }
                        case 3:
                            {
                                TrackRequest request = NovaPoshtaApi.SendRequest(e.Message.Text,null);
                                if (request.data[0].Status != "Номер не знайдено")
                                {
                                    await BOT.SendTextMessageAsync(e.Message.From.Id, $"Статус документа: {request.data[0].Status}",replyMarkup:Keyboards.GetTracking(e.Message.Text,user.Id,request.data[0].StatusCode));
                                }
                                else
                                {
                                    await BOT.SendTextMessageAsync(e.Message.From.Id, request.data[0].Status);
                                }
                                user.Status = 2;
                                await db.SaveChangesAsync();
                                break;
                            }
                        default:
                            break;
                    }
                }
                else
                {
                    db.Users.Add(new User(e.Message.From.Id));
                    await db.SaveChangesAsync();
                    await BOT.SendTextMessageAsync(e.Message.From.Id, $"Добрый день{Environment.NewLine}Данный бот позволяет отслеживать ТТН Новой почты{Environment.NewLine}Для начала работы отправьте свои контакты", replyMarkup: Keyboards.GetFio());
                    
                }
            }
        }

        private void BwReminder_DoWork(object sender, DoWorkEventArgs e)
        {
            using (Context db = new Context())
            {
                List<Reminder> list =  db.Reminders.ToList();
                if (list.Count != 0)
                {
                    foreach (Reminder item in list)
                    {
                        TrackRequest track = NovaPoshtaApi.SendRequest(item.TTN, null);
                        if (track.data[0].StatusCode != item.StatusId.ToString())
                        {
                            User user = db.Users.First(x=>x.Id==item.UserId);
                            item.StatusId = Convert.ToInt32(track.data[0].StatusCode);
                            BOT.SendTextMessageAsync(user.Telegram, $"Добрый день!{Environment.NewLine}По ТТН{item.TTN} произошли изменения статуса - теперь документ в статусе {track.data[0].Status}");
                            db.SaveChanges();
                        }

                    }
                }
                else
                {
                    return;
                }
            }
        }

        private void Timer1_Tick(object sender, EventArgs e)
        {
            bwReminder.RunWorkerAsync();
        }
    }
}
