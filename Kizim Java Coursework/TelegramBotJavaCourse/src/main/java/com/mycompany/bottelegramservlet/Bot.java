package com.mycompany.bottelegramservlet;

import java.util.logging.Level;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.LongPollingBot;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Pattern;


public class Bot extends TelegramLongPollingBot {
    final String[] COMMON_PHRASES = {
        "The Cake is a LIE",
        "I'm Error",
        "'Need as well as greed have followed us to the stars'",
        "'The end comes...Beyond chaos'",
        "I'm sorry, but your princess is in another castle"
     };
    
       final String[] BOOK_PHRASES = {
        "Mishima,' The Golden Temple' ",
        "Lem,'Solaris'",
        "Hoeg,'Smilla's sense of snow '",
        "Murakami,'Dance Dance Dance'",
        "Strugatsky,'Hard to be a god'",
        "Gaiman,'American gods' ",
        "Akutagava,'Web'"
        
     };
       
      final String[] GAME_PHRASES = {
        "Dark Souls 3",
        "Dishonored",
        "NieR Automata",
        "Night in the Woods",
        "Control",
        "Sekiro: Shadows Die Twice",
        "Bioshok",
        "Inside",
        "Hellblade - Senua's Sacrifice"
     };
      
      
         final String[] MOVIES_PHRASES = {
        "Midsommar",
        "Whiplash",
        "Breakfast Club",
        "Parasites",
        "His Dark Materials",
        "Okja",
        "Hereditary",
        "The Witch"
     };
    final String[] ELUSIVE_ANSWERS = {
        "Difficult question",
        "Need more time",
        "Another time, maybe?"
        };
    final Map<String, String> PATTERNS_FOR_ANALYSIS = new HashMap<String, String>() {{
        // hello
        put("хай", "hello");
        put("привет", "hello");
        put("здорово", "hello");
        put("здравствуй", "hello");
        // who
        put("кто\\s.*ты", "who");
        put("ты\\s.*кто", "who");
        // name
        put("как\\s.*зовут", "name");
        put("как\\s.*имя", "name");
        put("есть\\s.*имя", "name");
        put("какое\\s.*имя", "name");
        // howareyou
        put("как\\s.*дела", "howareyou");
        put("как\\s.*жизнь", "howareyou");
        // whatdoyoudoing
        put("зачем\\s.*тут", "whatdoyoudoing");
        put("зачем\\s.*здесь", "whatdoyoudoing");
        put("что\\s.*делаешь", "whatdoyoudoing");
        put("чем\\s.*занимаешься", "whatdoyoudoing");
        // whatdoyoulike
        put("что\\s.*нравится", "whatdoyoulike");
        put("что\\s.*любишь", "whatdoyoulike");
        // iamfeelling
        put("кажется", "iamfeelling");
        put("чувствую", "iamfeelling");
        put("испытываю", "iamfeelling");
        // yes
        put("^да", "yes");
        put("согласен", "yes");
        // whattime
        put("который\\s.*час", "whattime");
        put("сколько\\s.*время", "whattime");
        // bye
        put("прощай", "bye");
        put("увидимся", "bye");
        put("до\\s.*свидания", "bye");
    }};
    final Map<String, String> ANSWERS_BY_PATTERNS = new HashMap<String, String>() {{
        put("hello", "Glad to see you");
        put("who", "I'm just a Bot");
        put("name", "Call me whatever you like");
        put("howareyou", "I'm fine, thanks");
        put("whatdoyoudoing", "Trying to talk with you");
        put("whatdoyoulike", "I love games&books");
        put("iamfeelling", "Tell me more");
        put("yes", "Okay");
        put("bye", "Goodbye, see ya soon");
    }};
    
    final Map<String, String> PATTERNS_FOR_BOOKS = new HashMap<String, String>() {{
        
        put("/books","Mishima,' The Golden Temple' ");
        put("/books","Lem,'Solaris' ");
        put("/books","Hoeg,'Smilla's sense of snow '");
        put("/books","Murakami,'Dance Dance Dance' ");
        put("/books","Strugatsky,'Hard to be a god' ");
        put("/books","Gaiman,'American gods' ");
        put("/books","Akutagava,'Web' ");
        
        
        
    }};
    
       final Map<String, String> PATTERNS_FOR_GAMES = new HashMap<String, String>() {{
        
        put("/games","Dark Souls 3");
        put("/games","Dishonored");
        put("/games","NieR Automata");
        put("/games","Night in the Woods");
        put("/games","Control");
        put("/games","Sekiro: Shadows Die Twice");
        put("/games","Bioshok");
        put("/games","Inside");
        put("/games","Hellblade - Senua's Sacrifice");
        
        
    }};
       
          final Map<String, String> PATTERNS_FOR_MOVIES = new HashMap<String, String>() {{
        
        put("/movies","Whiplash");
        put("/movies","Breakfast Club");
        put("/movies","Midsommar");
        put("/movies","Parasites");
        put("/movies","His Dark Materials");
        put("/movies","Okja");
        put("/movies","Hereditary");
        put("/movies","The Witch");
        
    }};
    Pattern pattern; // for regexp
    Random random; // for random answers
    Date date; // for date and time

    public Bot() {
        random = new Random();
        date = new Date();
    }

    static LongPollingBot getBot() {
        return new Bot();
    }
    
    @Override
    public String getBotUsername() {
        return "JavaCourse_30PR31_Bot";
    }
    
    @Override
    public String getBotToken() {
        return "956266929:AAGns9G9PyNMcMILWXMDKzX7xveveZIjLcs";
    }
    
    @Override
      public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();
            String messageReturn = "";
            String message = String.join(" ", messageText.toLowerCase().split("[ {,|.}?]+"));
            
            if(messageText.equals("/start") || messageText.equals("/help")){
                messageReturn = "Hello! This Bot wiil choose your entertainment using random. Select the category you are interested in : /books, /games, /movies ; ";
            }
            
            if (messageText.equals("/books")) {
                for (Map.Entry<String, String> o : PATTERNS_FOR_BOOKS.entrySet()) {
                    messageReturn = (messageText.trim().endsWith("?"))? ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]: BOOK_PHRASES[random.nextInt(BOOK_PHRASES.length)];
            }
               
            }
            
            if (messageText.equals("/games")) {
                for (Map.Entry<String, String> o : PATTERNS_FOR_GAMES.entrySet()) {
                    messageReturn = (messageText.trim().endsWith("?"))? ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]: GAME_PHRASES[random.nextInt(GAME_PHRASES.length)];
            }
            }
            
             if (messageText.equals("/movies")) {
                for (Map.Entry<String, String> o : PATTERNS_FOR_MOVIES.entrySet()) {
                    messageReturn = (messageText.trim().endsWith("?"))? ELUSIVE_ANSWERS[random.nextInt(ELUSIVE_ANSWERS.length)]: MOVIES_PHRASES[random.nextInt(MOVIES_PHRASES.length)];
            }
            }
             else {
                for (Map.Entry<String, String> o : PATTERNS_FOR_ANALYSIS.entrySet()) {
                    pattern = Pattern.compile(o.getKey());
                    if (pattern.matcher(message).find())
                        if (o.getValue().equals("whattime"))
                            messageReturn = date.toString();

                        else
                            messageReturn = ANSWERS_BY_PATTERNS.get(o.getValue());
                   
                }
            
            SendMessage sendMessage = new SendMessage().setChatId(chat_id).setText(messageReturn);         
            try {
                sendMessage(sendMessage);
            } catch (TelegramApiException ex) {
                Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
      }
}
}
