using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Telegram.Bot.Types.ReplyMarkups;

namespace JavaNovaPosthaBot.MyKeyboards
{
    class Keyboards
    {
        public static ReplyKeyboardMarkup MainKeyboard()
        {
            return new ReplyKeyboardMarkup(new List<List<KeyboardButton>> { new List<KeyboardButton> { new KeyboardButton("Треккинг"), new KeyboardButton("Мои посылки  ") } },true);
        }
        public static ReplyKeyboardMarkup GetFio()
        {
            KeyboardButton button = new KeyboardButton("Отправить ФИО");
            button.RequestContact = true;
            return new ReplyKeyboardMarkup(new List<List<KeyboardButton>> { new List<KeyboardButton> { button } }, true);
        }

        public static InlineKeyboardMarkup GetTracking(string doc, int userId, string status)
        {
            return new InlineKeyboardButton { CallbackData = doc + "|us" + userId + "/us|st:" + status, Text = "Подписаться под изменениями в документе" };
        }

    }
}
