/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itstep.myjavabot.ControlLayer;

import org.itstep.myjavabot.DatabaseLayer.DataBase;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.Date;
import org.itstep.myjavabot.DatabaseLayer.Reminder;
import org.itstep.myjavabot.DatabaseLayer.Request;
import org.itstep.myjavabot.DatabaseLayer.User;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.LongPollingBot;
import static org.telegram.telegrambots.logging.BotLogger.log;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class Bot extends TelegramLongPollingBot
{
    public static LongPollingBot getBot() {
        return new Bot();
    }
    //DataBase db = nulll
    public Bot()
    {
        super();
        //db = new 
    }

    @Override
    public String getBotUsername() {

        return "javaZiubanTestBot";//имя бота ждля обращения человеком
    }


    @Override
    public String getBotToken() {

       return "966313623:AAElr8O-1mQrtqA__JIctEzBQbInFXo8Psc";//здесь токен(=номер,имя), которое выдал
    }

    @Override
    public void onUpdateReceived(Update update) 
    {
        DataBase d = new DataBase();

        if (   update.hasMessage() && update.getMessage().hasText())
        {
            if (d.isUser(update.getMessage().getFrom().getId())) {
                User user = d.getUser(update.getMessage().getFrom().getId());
                switch (user.getStatus()){
                    case (1):
                        user.setName(update.getMessage().getContact().getLastName() + " " 
                                + update.getMessage().getContact().getFirstName() );
                        user.setStatus(2);
                        break;
                    case (2):
                        if (update.getMessage().getText()== "Треккинг") {
                            sendMsg(user.getTelegram(),"Отправляйте ТТН");
                            user.setStatus(3);
                        }
                        else if (update.getMessage().getText()== "Мои посылки") {
                            ArrayList<Reminder> list = d.getListReminder(user.getId());
                            if (list.size()!=0) {
                                sendMsg(user.getTelegram(),list.toString());
                            }
                            else
                            {
                                sendMsg(user.getTelegram(),"У вас нет отслеживаемых посылок");
                            }
                        }
                        else
                        {
                            sendMsg(user.getTelegram(),"Сейчас я ожидаю нажатия кнопок главного ");
                        }
                        break;
                    case (3):
                        Request request = d.getRequest(update.getMessage().getText());
                        sendMsg(user.getTelegram(),"Статус документа: "+ request.getStaus());
                        break;
                }
                        
            }
            else{
                d.addUser(new User(update.getMessage().getFrom().getId().toString()));
            }
            
            
            //
        }
        else {
            SendMessage message = new SendMessage();
            //message.setReplyMarkup(replyMarkup)
            sendMsg(update.getMessage().getChatId().toString(),update.getMessage().getLocation().getLatitude().toString());
        }
    }
    
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            //log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }
}

    