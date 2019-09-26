package com.mycompany.shoptelegrambotoracle;

import ControllerLayer.Bot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class MyJavaBot {


    public static void main(String[] args) 
    {
        System.out.print("Hello, world from Java Bot!");
        ApiContextInitializer.init();

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(Bot.getBot());

        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
        //
    } 
    
   
}
