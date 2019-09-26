package ControllerLayer;

import static com.google.common.collect.FluentIterable.from;
import com.mycompany.shoptelegrambotoracle.Customers;
import com.mycompany.shoptelegrambotoracle.Manufacturers;
import com.mycompany.shoptelegrambotoracle.Newusers;
import com.mycompany.shoptelegrambotoracle.OrderDetails;
import com.mycompany.shoptelegrambotoracle.OrderDetailsProduct;
import com.mycompany.shoptelegrambotoracle.Orders;
import com.mycompany.shoptelegrambotoracle.Pictures;
import com.mycompany.shoptelegrambotoracle.Products;
import com.mycompany.shoptelegrambotoracle.TypesProduct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.telegram.telegrambots.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.generics.LongPollingBot;
import com.vdurmont.emoji.EmojiParser;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.CallbackQuery;



public class Bot extends TelegramLongPollingBot {

    private static SessionFactory factory; 
    private static Session session = null;
    private String smile_emoji = EmojiParser.parseToUnicode(":smiley: some text");
    private String share_number_emoji = EmojiParser.parseToUnicode(":phone:");
    private String money_emoji = EmojiParser.parseToUnicode(":moneybag:");
    private String telephone_receiver = EmojiParser.parseToUnicode(":telephone_receiver:");
    private String card_file_box = EmojiParser.parseToUnicode(":card_file_box:");
    private String shopping_cart = EmojiParser.parseToUnicode(":shopping_cart:");
    private String briefcase = EmojiParser.parseToUnicode(":briefcase:");
    private String one = EmojiParser.parseToUnicode(":one:");
    private String two = EmojiParser.parseToUnicode(":two:");
    private String three = EmojiParser.parseToUnicode(":three:");
    private String four = EmojiParser.parseToUnicode(":four:");
    private String five = EmojiParser.parseToUnicode(":five:");
    private String six = EmojiParser.parseToUnicode(":six:");
    private String seven = EmojiParser.parseToUnicode(":seven:");
    private String eight = EmojiParser.parseToUnicode(":eight:");
    private String nine = EmojiParser.parseToUnicode(":nine:");
    String from = "andrey.p23.33";
    String pass = "jordanm23";
    

    public Bot() {
         
        try {
            factory = new Configuration().configure().buildSessionFactory();
            
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    
    public static LongPollingBot getBot() {
        return new Bot();
    }

    @Override
    public String getBotUsername() {
        return "ShopTelegramOracleBot";
    }

    @Override
    public String getBotToken() {
       return "898545054:AAEnL9SKJa-5pAr2hLtwpHYFn9sNGmEjNeE";
    }

    @Override
    public void onUpdateReceived(Update update) { 
        if (update.hasMessage() || update.hasCallbackQuery()) {
            long chat_id;

            if (update.hasMessage()) {
                chat_id = update.getMessage().getChatId();
            } else {
                CallbackQuery callbackQuery = update.getCallbackQuery();
                chat_id = callbackQuery.getMessage().getChatId();
            }

            try {
                List<Newusers> newUsers = getNewusers();

                if (newUsers.stream().anyMatch(x -> x.getBotNumber() == chat_id)) {
                    Newusers newusers = newUsers.stream().filter(x -> x.getBotNumber() 
                            == chat_id).findFirst().get();

                    String message_text = null;

                    if (newusers.getStatus() < 5) {
                        message_text = update.getMessage().getText();
                    } else {
                        message_text = update.getCallbackQuery().getData();
                    }
                    switch (newusers.getStatus()) {
                        case 1: {
                            if (isFullname(message_text)) {
                                newusers.setName(message_text);
                                newusers.setStatus(2);
                                editNewUser(newusers);
                                sendContact(Long.toString(chat_id), "Теперь нажмите на "
                                        + "появившуюся кнопку, что бы передать "
                                        + "боту номер телефона");
                            } else {
                                sendMsg(Long.toString(chat_id), "Имя введено не верно");
                            }
                        }break;
                        case 2: {
                            if (update.getMessage().getContact() != null) {
                                newusers.setPhoneNumber(update.getMessage().getContact().getPhoneNumber());
                                newusers.setStatus(3);
                                editNewUser(newusers);

                                List<Customers> customers = getCustomers();

                                if (customers.stream().anyMatch(x -> x.getPhoneNumber().equals(newusers.getPhoneNumber()))) {
                                    Customers customer = customers.stream()
                                            .filter(x -> x.getPhoneNumber().equals(newusers.getPhoneNumber()))
                                            .findFirst().get();
                                    newusers.setIdCustomer(customer.getId());
                                    newusers.setStatus(4);
                                    editNewUser(newusers);
                                    sendMsgWithMenu(Long.toString(chat_id), "Рады приветствовать вас, " 
                                            + customer.getFirstName());
                                } else {
                                    sendMsg(Long.toString(chat_id), "Вы не зарегистрированы как клиент. "
                                            + "Ожидайте, с вами свяжется администратор");
                                }
                            } else {
                                sendContact(Long.toString(chat_id), "Нужно нажать кнопку");
                            }
                        }break;
                        case 3: {
                            sendMsg(Long.toString(chat_id), "Ожидайте, с вами свяжется администратор");
                        }break;
                        case 4: {  
                            String result = "";
                            if (message_text.contains("Корзина")) {
                                result = message_text.substring(message_text.indexOf("Корзина"), 10);
                            }else {
                                result = message_text;
                            }                             
                            switch (result) {
                                case "\ud83d\uddc3 Каталог товаров": {

                                    List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

                                    for (TypesProduct item : getTypesProduct()) {
                                        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
                                        buttons1.add(new InlineKeyboardButton().setText(item.getName())
                                                .setCallbackData(item.getName()));
                                        buttons.add(buttons1);
                                    }
                                    List<InlineKeyboardButton> buttonBack = new ArrayList<>();
                                    buttonBack.add(new InlineKeyboardButton().setText("В меню")
                                            .setCallbackData("В меню"));
                                    buttons.add(buttonBack);
                                    InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
                                    markupKeyboard.setKeyboard(buttons);

                                    newusers.setStatus(5);
                                    editNewUser(newusers);
                                    sendMsgWithInBut(Long.toString(chat_id), "--------------------------------", 
                                            markupKeyboard);
                                    sendMsg(Long.toString(chat_id), "Выберите категорию товара:");
                                }break;
                                case "\ud83d\udcb0 Товары в наличии": {

                                    List<Products> products = getProducts().stream()
                                            .filter(e -> e.getProductCount() > 0).collect(Collectors.toList());
                                    List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

                                    for (Products item : products) {
                                        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
                                        buttons1.add(new InlineKeyboardButton()
                                                .setText(item.getModel() + " - " + item.getProductCount() + "шт.")
                                                .setCallbackData(item.getModel()));
                                        buttons.add(buttons1);
                                    }
                                    InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
                                    markupKeyboard.setKeyboard(buttons);
                                    newusers.setStatus(6);
                                    editNewUser(newusers);
                                    sendMsgWithInBut(Long.toString(chat_id), "Позиции:", markupKeyboard);
                                    
                                    List<List<InlineKeyboardButton>> buttonsNew = new ArrayList<>();
                                    List<InlineKeyboardButton> buttons1 = new ArrayList<>();
                                    buttons1.add(new InlineKeyboardButton().setText("В меню").setCallbackData("В меню"));
                                    buttonsNew.add(buttons1);

                                    InlineKeyboardMarkup markupKeyboardNew = new InlineKeyboardMarkup();
                                    markupKeyboard.setKeyboard(buttonsNew);
                                    sendMsgWithInBut(Long.toString(chat_id), "Сделайте ваш выбор:", markupKeyboardNew);
                                }
                                break;
                                case "\ud83d\udcbc Мои заказы": {
                                    List<Orders> orders = getOrders();
                                    
                                    for (Orders item : orders.stream()
                                            .filter(e -> e.getIdCustomer() == (long) newusers.getIdCustomer()
                                            && e.getOrderStatus() == 1).collect(Collectors.toList())) {
                                        sendMsg(Long.toString(chat_id), "Заказ № " + item.getNumberOrder() + "\n"
                                                + "Дата заказа: " + item.getDateOrder() + " Сумма заказа " 
                                                + item.getOrderPrice() + " грн.\n"
                                                + "Позиции: \n");
                                        int c = 1;
                                        for (OrderDetailsProduct item1 : getOrderDetailsByOrder(item.getOrderId())) {
                                             sendMsg(Long.toString(chat_id), c + "." + item1.getProducyName() 
                                                     + " Количество: " + item1.getCountProduct() + " Цена: "
                                                     + item1.getPrice() + " грн.\n");
                                        }
                                    }
                                    
                                    sendMsgWithMenu(Long.toString(chat_id), "Выберите пункт меню");
                                }break;
                                case "Корзина": {
                                    basket(newusers, chat_id);
                                }break;
                                default: {
                                    sendMsgWithMenu(Long.toString(chat_id), "Выберите пункт меню");
                                }break;
                            }
                        }break;
                        case 5: {
                            switch (message_text) {
                                case "Phone": {
                                    newusers.setStatus(6);
                                    editNewUser(newusers);
                                    sendMsgWithInBut(Long.toString(chat_id), "--------------------------------", 
                                            searchProducts("Phone"));
                                    sendMsg(Long.toString(chat_id), "Выберите товар:");
                                }break;
                                case "Laptop": {
                                    newusers.setStatus(6);
                                    editNewUser(newusers);
                                    sendMsgWithInBut(Long.toString(chat_id), "--------------------------------", 
                                            searchProducts("Laptop"));
                                    sendMsg(Long.toString(chat_id), "Выберите товар:");
                                }break;
                                case "PC": {
                                    newusers.setStatus(6);
                                    editNewUser(newusers);
                                    sendMsgWithInBut(Long.toString(chat_id), "--------------------------------", 
                                            searchProducts("PC"));
                                    sendMsg(Long.toString(chat_id), "Выберите товар:");
                                }break;
                                case "Tablet": {
                                    newusers.setStatus(6);
                                    editNewUser(newusers);
                                    sendMsgWithInBut(Long.toString(chat_id), "--------------------------------", 
                                            searchProducts("Tablet"));
                                    sendMsg(Long.toString(chat_id), "Выберите товар:");
                                }break;
                                case "Watch": {
                                    newusers.setStatus(6);
                                    editNewUser(newusers);
                                    sendMsgWithInBut(Long.toString(chat_id), "--------------------------------", 
                                            searchProducts("Watch"));
                                    sendMsg(Long.toString(chat_id), "Выберите товар:");
                                }break;
                                case "В меню": {
                                    newusers.setStatus(4);
                                    editNewUser(newusers);
                                    sendMsgWithMenu(Long.toString(chat_id), "Выберите пункт меню");
                                }break;
                                default: {
                                    answerCallbackQuery(update.getCallbackQuery().getId(), "Не верный выбор");
                                }break;
                            }
                        }break;
                        case 6: {
                            
                            if (message_text.equals("В меню")) {
                                newusers.setStatus(4);
                                editNewUser(newusers);
                                sendMsgWithMenu(Long.toString(chat_id), "Выберите пункт меню");
                                return;
                            }
                            
                            List<Products> products = getProducts();
                            final String model = message_text ;
                            
                            if (products.stream().anyMatch(x -> x.getModel().equals(model))) {
                                newusers.setStatus(7);
                                editNewUser(newusers);
                                
                                Products p = getProduct(message_text);
                                
                                showImage(chat_id, p.getProductId());
                                String str = "Название: " + p.getModel()
                                        + "\n" + "Производитель: " + getManufacturer(p.getIdManufacturer())
                                        + "\n" + "Цена: " + p.getPrice()
                                        + "\n" + "В наличии: " + p.getProductCount();

                                sendMsg(Long.toString(chat_id), str);
                                sendMsgWithInBut(Long.toString(chat_id), "Ваш выбор", setInlineForProduct(p.getProductId()));
                            }else{
                                answerCallbackQuery(update.getCallbackQuery().getId(), "Не верный выбор");
                            }
                        }break;
                        case 7: {
                            if (message_text.equals("В меню")) {
                                newusers.setStatus(4);
                                editNewUser(newusers);
                                sendMsgWithMenu(Long.toString(chat_id), "Выберите пункт меню");
                                return;
                            }
                            
                            if (message_text.contains("&")) {
                                String string = message_text;
                                String[] parts = string.split("&");
                                String part1 = parts[0];
                                String part2 = parts[1];

                                if (part1.equals("В корзину")) {
                                    Products p = getProducts().stream().filter(x -> x.getProductId() == 
                                            Long.parseLong(part2)).findFirst().get();
                                    if (p.getProductCount() == 0) {
                                        answerCallbackQuery(update.getCallbackQuery().getId(), "Нет в наличии");
                                    }else{
                                        newusers.setStatus(8);
                                        editNewUser(newusers);
                                        sendMsgWithInBut(Long.toString(chat_id), "Выберите количество:", 
                                                setInlineForQuantity(p));
                                    }
                                }
                            } else {
                                answerCallbackQuery(update.getCallbackQuery().getId(), "Не верный выбор");
                            }
                        }break;
                        case 8: {
                            if (message_text.equals("В меню")) {
                                newusers.setStatus(4);
                                editNewUser(newusers);
                                sendMsgWithMenu(Long.toString(chat_id), "Выберите пункт меню");
                                return;
                            }
                            
                            if (message_text.contains("&")) {
                                String string = message_text;
                                String[] parts = string.split("&");
                                String part1 = parts[0];
                                String part2 = parts[1];
                                
                                int num;
                                
                                try {
                                    num = Integer.parseInt(part1);
                                } catch (NumberFormatException e) {
                                    answerCallbackQuery(update.getCallbackQuery().getId(), "Не верный выбор");
                                    return;
                                }
                                
                                Products p = getProducts().stream().filter(x -> x.getProductId() == 
                                        Long.parseLong(part2)).findFirst().get();
                                
                                if (p.getProductCount() >= num) {
                                    List<Orders> orders = getOrders();
                                    final String model = message_text;
                                    Long orderId = null;

                                    if (getOrders().stream().anyMatch(x -> x.getIdCustomer() == 
                                            (int)newusers.getIdCustomer() && x.getOrderStatus() == 0)) {
                                        Orders od = getOrders().stream().filter(x -> x.getIdCustomer() == 
                                                (int)newusers.getIdCustomer() && x.getOrderStatus() == 0)
                                                .findFirst().get();
                                        orderId = od.getOrderId();
                                    } else {
                                        orderId = addOrder(Long.valueOf(newusers.getIdCustomer()), 0);
                                    }
                                    
                                    addOrderDetails(orderId, p.getProductId(), num, p.getPrice());
                                    Orders odNew = getOrders().stream().filter(x -> x.getIdCustomer() == 
                                                (int)newusers.getIdCustomer() && x.getOrderStatus() == 0)
                                            .findFirst().get();
                                    
                                    List<OrderDetails> orderDetails = getOrderDetails();
                                    
                                    Integer quantity = 0;
                                    for (OrderDetails item : orderDetails) {
                                        if (item.getIdOrder() == (long)orderId) {
                                            quantity += item.getCountProduct();
                                        }
                                    }
                                    
                                    answerCallbackQuery(update.getCallbackQuery().getId(), "Заказ добавлен в корзину" 
                                            + "\n" + "Количество позиций в корзине: " + quantity + " шт."
                                            + "\n" + "Сумма позиций в корзине: " + odNew.getOrderPrice() + " грн.");
                                    
                                    newusers.setStatus(4);
                                    editNewUser(newusers);
                                    sendMsgWithMenu(Long.toString(chat_id), "Выберите пункт меню");
                                    
                                } else {
                                    answerCallbackQuery(update.getCallbackQuery().getId(), "Недостаточно товара");
                                }
                            } else {
                                answerCallbackQuery(update.getCallbackQuery().getId(), "Не верный выбор");
                            }
                        }break;
                        case 9: {
                            if (message_text.equals("В меню")) {
                                newusers.setStatus(4);
                                editNewUser(newusers);
                                sendMsgWithMenu(Long.toString(chat_id), "Выберите пункт меню");
                            } else if (message_text.equals("Подтвердить заказ")) {
                                Orders od = getOrders().stream().filter(x -> x.getIdCustomer() == 
                                                (int)newusers.getIdCustomer() && x.getOrderStatus() == 0)
                                        .findFirst().get();
                                od.setOrderStatus(1);
                                editOrder(od);
                                Customers customer = getCustomers().stream().filter(x -> x.getId() == newusers
                                        .getIdCustomer()).findFirst().get();
                                String rec = customer.getEmail();
                                String str = "Подтверждение заказа " + od.getNumberOrder() + "\n"
                                        + "Общая сумма заказа: " + od.getOrderPrice() + " грн. \n\n"
                                        + "Позиции: \n";
                                int c = 1;
                                for (OrderDetailsProduct item : getOrderDetailsByOrder(od.getOrderId())) {
                                    str += c + "." + item.getProducyName() + " Количество: " + item.getCountProduct() 
                                            + " Цена: " + item.getPrice() + " грн.\n";
                                }
                                        
                                SendEmailSMTP.sendEmail(from, pass, rec, "Подтверждение заказа " + od.getNumberOrder(), str);
                                answerCallbackQuery(update.getCallbackQuery().getId(), "Заказ сохранён \nСпасибо");
                                
                                newusers.setStatus(4);
                                editNewUser(newusers);
                                sendMsgWithMenu(Long.toString(chat_id), "Выберите пункт меню");
                            } else {
                                try {
                                    long id = Long.parseLong(message_text);
                                    List<OrderDetails> orderDetails = getOrderDetails();

                                    if (orderDetails.stream().anyMatch(x -> x.getOrderDetailId() == id)) {
                                        delOrderDetails(id);
                                        basket(newusers, chat_id);
                                    } else {
                                        answerCallbackQuery(update.getCallbackQuery().getId(), "Нет такого кода");
                                    }
                                } catch (Exception e) {
                                    answerCallbackQuery(update.getCallbackQuery().getId(), "Не верный выбор");
                                }
                            }
                        }
                        break;
                    }
                } else {
                    addNewUser((int) chat_id, "Нет данных", "Нет данных", 1);
                    sendMsg(Long.toString(chat_id), "Введите ваше ФИО");
                }
            } catch (NullPointerException e) {
                sendMsgWithMenu(Long.toString(chat_id), "Не верный выбор ");
            }
        }
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        sendMessage.setReplyMarkup(replyKeyboardRemove);
        try { 
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            //log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }
    
    public synchronized void sendMsgWithMenu(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        setButtons(sendMessage, Long.parseLong(chatId));
        try { 
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            //log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }
    
    public synchronized void sendContact(String chatId, String s) {
        SendMessage sendMessage = new SendMessage()
                .setChatId(chatId)
                .setText(s);

        // create keyboard
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        // new list
        List<KeyboardRow> keyboard = new ArrayList<>();

        // first keyboard line
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(share_number_emoji + " Share your number >").setRequestContact(true);
        keyboardFirstRow.add(keyboardButton);

        // add array to list
        keyboard.add(keyboardFirstRow);

        // add list to our keyboard
        replyKeyboardMarkup.setKeyboard(keyboard);

        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    
    public synchronized void sendMsgWithInBut(String chatId, String s, InlineKeyboardMarkup in) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        sendMessage.setReplyMarkup(in);
        try { 
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            //log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }
    
    public synchronized void setButtons(SendMessage sendMessage, Long userId) {
        // Создаем клавиуатуру
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // Создаем список строк клавиатуры
        List<KeyboardRow> keyboard = new ArrayList<>();

        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton(card_file_box + " Каталог товаров"));
        keyboardFirstRow.add(new KeyboardButton(money_emoji + " Товары в наличии"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton(briefcase + " Мои заказы"));
        
        List<Orders> orders = getOrders();
        Newusers newusers = getNewusers().stream().filter(x -> x.getBotNumber() == userId.intValue()).findFirst().get();
        
        String str = "";
        
        if (orders.stream().anyMatch(e -> e.getIdCustomer() == (long) newusers.getIdCustomer() && e.getOrderStatus() == 0)) {
            Orders order = orders.stream().filter(e -> e.getIdCustomer() == (long) newusers.getIdCustomer()
                    && e.getOrderStatus() == 0).findFirst().get();
            List<OrderDetailsProduct> orderDetailsProducts = getOrderDetailsByOrder(order.getOrderId());

            int count = 0;

            for (OrderDetailsProduct item : orderDetailsProducts) {
                count += item.getCountProduct();
            }

            switch (count) {
                case 1: {
                    str = one;
                }
                break;
                case 2: {
                    str = two;
                }
                break;
                case 3: {
                    str = three;
                }
                break;
                case 4: {
                    str = four;
                }
                break;
                case 5: {
                    str = five;
                }
                break;
                case 6: {
                    str = six;
                }
                break;
                case 7: {
                    str = seven;
                }
                break;
                case 8: {
                    str = eight;
                }
                break;
                case 9: {
                    str = nine;
                }
                break;
                default: {

                }
                break;
            }
        }
        
        keyboardSecondRow.add(new KeyboardButton(shopping_cart + " Корзина " + str));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }
    
    private InlineKeyboardMarkup setInlineForProduct(Long productId) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        buttons1.add(new InlineKeyboardButton().setText("В корзину").setCallbackData("В корзину&" + productId));
        buttons1.add(new InlineKeyboardButton().setText("В меню").setCallbackData("В меню"));
        buttons.add(buttons1);

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        
        return markupKeyboard;
    }
    
    private InlineKeyboardMarkup setInlineForQuantity(Products p) {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> buttons1 = new ArrayList<>();
        List<InlineKeyboardButton> buttons2 = new ArrayList<>();
        
        for (int i = 1; i < 6; i++) {
            if (p.getProductCount()>=i) {
                buttons1.add(new InlineKeyboardButton().setText(Integer.toString(i)).setCallbackData(Integer.toString(i) + "&" + p.getProductId()));
            }
            else{break;}
        }
        buttons.add(buttons1);
        buttons2.add(new InlineKeyboardButton().setText("В меню").setCallbackData("В меню"));
        buttons.add(buttons2);

        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);
        
        return markupKeyboard;
    }
    
    public synchronized void answerCallbackQuery(String callbackId, String message) {
        AnswerCallbackQuery answer = new AnswerCallbackQuery();
        answer.setCallbackQueryId(callbackId);
        answer.setText(message);
        answer.setShowAlert(true);
        try {
            answerCallbackQuery(answer);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    
    private static List<Customers> getCustomers() throws ExceptionInInitializerError, HibernateException {
        SessionFactory mFctory;
        try {
            mFctory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Couldn't create session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = null;

        session = mFctory.openSession();
        
        Query query = session.createQuery("SELECT c FROM Customers c");
        List<Customers> customers = query.list();
        
        session.close();
        
        return customers;
    }
    
    private static List<Newusers> getNewusers() throws ExceptionInInitializerError, HibernateException {
        List<Newusers> newUsers= null;
        
        session = factory.openSession();

        try {
            String hqltext = "from Newusers";

            Query q = session.createQuery(hqltext);
            newUsers = q.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return newUsers;
    }
    
    private static List<TypesProduct> getTypesProduct() throws ExceptionInInitializerError, HibernateException {
        List<TypesProduct> typesProducts= null;
        
        session = factory.openSession();

        try {
            String hqltext = "from TypesProduct";

            Query q = session.createQuery(hqltext);
            typesProducts = q.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return typesProducts;
    }
    
    private static List<Products> getProducts() throws ExceptionInInitializerError, HibernateException {
        List<Products> products= null;
        
        session = factory.openSession();

        try {
            String hqltext = "from Products";

            Query q = session.createQuery(hqltext);
            products = q.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return products;
    }
    
    private static List<Pictures> getPictures() throws ExceptionInInitializerError, HibernateException {
        List<Pictures> pictures= null;
        
        session = factory.openSession();

        try {
            String hqltext = "from Pictures";

            Query q = session.createQuery(hqltext);
            pictures = q.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return pictures;
    }
    
    private static List<Orders> getOrders() throws ExceptionInInitializerError, HibernateException {
        List<Orders> orders= null;
        
        session = factory.openSession();

        try {
            String hqltext = "from Orders";

            Query q = session.createQuery(hqltext);
            orders = q.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return orders;
    }
    
    private static List<Orders> getOrderByCustomer(Integer id) throws ExceptionInInitializerError, HibernateException {
        List<Orders> orders= null;
        
        session = factory.openSession();

        try {

            String hql = "from Orders o where idCustomer = :id";
            orders = session.createQuery(hql)
                    .setParameter("id", id.longValue())
                    .list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return orders;
    }
    
    private static List<OrderDetailsProduct> getOrderDetailsByOrder(Long id) throws ExceptionInInitializerError, HibernateException {
        List<OrderDetailsProduct> orderDetails = new ArrayList<>();
        Connection connection = null;

        try {

            java.util.Locale locale = java.util.Locale.getDefault();
            java.util.Locale.setDefault(java.util.Locale.ENGLISH);

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system",
                    "111");

            java.util.Locale.setDefault(locale);

        } catch (SQLException ex) {
            System.out.println("Проблема соединения с СУБД. Подробности:" + ex);
        }

        String hqltext = "Select o.ORDERID, od.ORDERDETAILID, pr.PRODUCTID, pr.MODEL, od.COUNTPRODUCT, od.PRICE From SHOP.ORDERS o "
                + "Join SHOP.ORDERDETAILS od On o.ORDERID = od.IDORDER "
                + "Join SHOP.PRODUCTS pr On od.IDPRODUCT = pr.PRODUCTID "
                + "Where o.ORDERID = " + id;

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
            try {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(hqltext);
                while (rs.next()) {
                    int ORDERID = rs.getInt("ORDERID");
                    int ORDERDETAILID = rs.getInt("ORDERDETAILID");
                    int PRODUCTID = rs.getInt("PRODUCTID");
                    String MODEL = rs.getString("MODEL");
                    int COUNTPRODUCT = rs.getInt("COUNTPRODUCT");
                    float PRICE = rs.getFloat("PRICE");
                    orderDetails.add(new OrderDetailsProduct(Long.valueOf(ORDERDETAILID), Long.valueOf(ORDERID), Long.valueOf(PRODUCTID), 
                            COUNTPRODUCT, PRICE, MODEL));
                }
            } catch (SQLException ex) {
                System.out.println("executeQuery Failed! Check output console");
                ex.printStackTrace();
            }
        }

        return orderDetails;
    }
    
    private static List<OrderDetails> getOrderDetails() throws ExceptionInInitializerError, HibernateException {
        List<OrderDetails> orderDetailses= null;
        
        session = factory.openSession();

        try {
            String hqltext = "from OrderDetails";

            Query q = session.createQuery(hqltext);
            orderDetailses = q.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return orderDetailses;
    }
    
    private static InlineKeyboardMarkup searchProducts(String type) throws ExceptionInInitializerError, HibernateException {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<Products> p1 = getProducts();
        List<TypesProduct> tpList = getTypesProduct();
        TypesProduct tp = tpList.stream().filter(x -> x.getName().equals(type)).findFirst().get();
        List<Products> p2 = p1.stream().filter(x -> x.getIdProductType() == tp.getTypesProductId()).collect(Collectors.toList());

        for (Products item : p2) {
            List<InlineKeyboardButton> buttons1 = new ArrayList<>();
            buttons1.add(new InlineKeyboardButton().setText(item.getModel()).setCallbackData(item.getModel()));
            buttons.add(buttons1);
        }
        List<InlineKeyboardButton> buttonBack = new ArrayList<>();
        buttonBack.add(new InlineKeyboardButton().setText("В меню").setCallbackData("В меню"));
        buttons.add(buttonBack);
        InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
        markupKeyboard.setKeyboard(buttons);

        return markupKeyboard;
    }
    
    private static Products getProduct(String model) throws ExceptionInInitializerError, HibernateException {
        List<Products> p1 = getProducts();
        Products product = p1.stream().filter(x -> x.getModel().equals(model)).findFirst().get();

        return product;
    }
    
    private static String getManufacturer(long manufacturerId) throws ExceptionInInitializerError, HibernateException {
        String str = "";
        List<Manufacturers> manufacturers = null;
        
        session = factory.openSession();

        try {
            String hqltext = "from Manufacturers";

            Query q = session.createQuery(hqltext);
            manufacturers = q.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        Manufacturers manufacturer = manufacturers.stream().filter(x -> x.getManufacturerId() == manufacturerId).findFirst().get();
        str = manufacturer.getName();

        return str;
    }
    
    private static void addNewUser(Integer BotNumber, String Name, 
        String PhoneNumber, Integer Status) throws ExceptionInInitializerError, HibernateException {
        
        SessionFactory mFctory;
        try {
            mFctory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Couldn't create session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = null;

        session = mFctory.openSession();

        session.beginTransaction();
        
        Newusers emp = new Newusers();
        //emp.setId(0l);
        emp.setBotNumber(BotNumber);
        emp.setName(Name);
        emp.setPhoneNumber(PhoneNumber);
        emp.setStatus(Status);
        Date now = new Date();
        emp.setCreatedAt(now);
         
        session.save(emp);

        session.getTransaction().commit();
        session.close();
    }
    
    private static void editOrder(Orders _orders) throws ExceptionInInitializerError, HibernateException {
        Orders orders = _orders;
        SessionFactory mFctory;
        try {
          mFctory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Couldn't create session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(orders);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    private static Long addOrder(Long idCustomer, Integer orderStatus) throws ExceptionInInitializerError, HibernateException {
        SessionFactory mFctory;
        try {
            mFctory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Couldn't create session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = null;

        session = mFctory.openSession();

        session.beginTransaction();
        
        Orders order = new Orders();

        Query query = session.createQuery("from Orders order by DATEORDER DESC");
        query.setMaxResults(1);
        Orders last = (Orders) query.uniqueResult();
        
        
        String strNum = "";
        if (last != null) {
            String string = last.getNumberOrder();
            String[] parts = string.split("-");
            String part1 = parts[0];
            String part2 = parts[1];
            String strOut = part2.substring(0,6);
            Integer num = Integer.parseInt(strOut);
            Integer N = num;
            Integer count =0;
            
            while (N > 0) {
                count++;
                N /= 10;
            }
            
            switch (count) {
                case 1: {
                    strNum = "ХВ-00000" + ++num;
                }break;
                case 2: {
                    strNum = "ХВ-0000" + ++num;
                }break;
                case 3: {
                    strNum = "ХВ-000" + ++num;
                }break;
                case 4: {
                    strNum = "ХВ-00" + ++num;
                }break;
                case 5: {
                    strNum = "ХВ-0" + ++num;
                }break;
                case 6: {
                    strNum = "ХВ-" + ++num;
                }break;
            }
        } else {
            strNum = "ХВ-000001";
        }
        
        order.setNumberOrder(strNum);

        Date now = new Date();
        order.setDateOrder(now);
        order.setIdCustomer(idCustomer);
        order.setOrderPrice(0f);
        order.setOrderStatus(orderStatus);
        
        Long id = (Long)session.save(order);

        session.getTransaction().commit();
        session.close();
        
        return id;
    }
    
    private static void addOrderDetails(Long idOrder, Long idProduct, Integer countProduct, Float price) throws ExceptionInInitializerError, HibernateException {
        SessionFactory mFctory;
        try {
            mFctory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Couldn't create session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = null;

        session = mFctory.openSession();

        session.beginTransaction();
        
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setIdOrder(idOrder);
        orderDetails.setIdProduct(idProduct);
        orderDetails.setCountProduct(countProduct);
        orderDetails.setPrice(price);

        session.save(orderDetails);

        session.getTransaction().commit();
        session.close();
    }
    
    private static void editNewUser(Newusers _newusers) throws ExceptionInInitializerError, HibernateException {
        Newusers newusers = _newusers;
        SessionFactory mFctory;
        try {
          mFctory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Couldn't create session factory." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(newusers);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static boolean isFullname(String str) {
        String expression = "^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$";
        return str.matches(expression);
    }
    
    private void basket(Newusers newusers, long chat_id) {
        List<Orders> orders = getOrderByCustomer(newusers.getIdCustomer());
        String str = "";
        
        newusers.setStatus(9);
        editNewUser(newusers);

        if (orders.stream().anyMatch(x -> x.getOrderStatus() == 0)) {
            Orders order = orders.stream().filter(x -> x.getOrderStatus() == 0).findFirst().get();

            List<OrderDetailsProduct> orderDetails = getOrderDetailsByOrder(order.getOrderId());

            int i = 1;

            sendMsg(Long.toString(chat_id), "КОРЗИНА: \n");
            for (OrderDetailsProduct item : orderDetails) {
                List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
                List<InlineKeyboardButton> buttons1 = new ArrayList<>();
                buttons1.add(new InlineKeyboardButton().setText("Удалить").setCallbackData(item.getOrderDetailId().toString()));
                buttons.add(buttons1);
                InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
                markupKeyboard.setKeyboard(buttons);
                sendMsgWithInBut(Long.toString(chat_id), i++ + "." + item.getProducyName() + ". Количество: "
                        + item.getCountProduct() + ". По цене: " + item.getPrice() + " грн. Общая стоимость"
                        + "по позиции: " + item.getPrice() * item.getCountProduct() + " грн.", markupKeyboard);
            }

            List<List<InlineKeyboardButton>> buttons = new ArrayList<>();

            if (orderDetails.size() > 0) {
                str = "Общая стоимость товара: " + order.getOrderPrice() + " грн.";

                List<InlineKeyboardButton> buttons1 = new ArrayList<>();
                buttons1.add(new InlineKeyboardButton().setText("Подтвердить заказ").setCallbackData("Подтвердить заказ"));
                buttons.add(buttons1);
            } else {
                str = "В корзине нет товара";
            }

            List<InlineKeyboardButton> buttons2 = new ArrayList<>();
            buttons2.add(new InlineKeyboardButton().setText("В меню").setCallbackData("В меню"));
            buttons.add(buttons2);
            InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
            markupKeyboard.setKeyboard(buttons);
            sendMsgWithInBut(Long.toString(chat_id), str, markupKeyboard);

        } else {
            str = "В корзине нет товара";

            List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
            List<InlineKeyboardButton> buttons2 = new ArrayList<>();
            buttons2.add(new InlineKeyboardButton().setText("В меню").setCallbackData("В меню"));
            buttons.add(buttons2);
            InlineKeyboardMarkup markupKeyboard = new InlineKeyboardMarkup();
            markupKeyboard.setKeyboard(buttons);
            sendMsgWithInBut(Long.toString(chat_id), str, markupKeyboard);
        }
    }
    
    private static void delOrderDetails(long id) throws ExceptionInInitializerError, HibernateException {
        Connection connection = null;

        try {

            java.util.Locale locale = java.util.Locale.getDefault();
            java.util.Locale.setDefault(java.util.Locale.ENGLISH);

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system",
                    "111");

            java.util.Locale.setDefault(locale);

        } catch (SQLException ex) {
            System.out.println("Проблема соединения с СУБД. Подробности:" + ex);
        }

        String hqltext = "delete from SHOP.ORDERDETAILS where ORDERDETAILID = " + id;

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
            try {
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(hqltext);
                
            } catch (SQLException ex) {
                System.out.println("executeQuery Failed! Check output console");
                ex.printStackTrace();
            }
        }
    }
    
    public static void insertImage(String str, Long id) {
        session = factory.openSession();

        session.beginTransaction();

        //save image into database
        File file = new File(str);
        byte[] bFile = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            //convert file into array of bytes
            fileInputStream.read(bFile);
            fileInputStream.close();

            Pictures pictures = new Pictures();
            pictures.setPhoto(bFile);
            pictures.setIdProduct(id);

            session.save(pictures);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    public void showImage(long chat_id, Long id) {
        Pictures pictures = getPictures().stream().filter(x -> x.getIdProduct() == id).findFirst().get();
        
        session = factory.openSession();
        Pictures picNew = (Pictures) session.get(Pictures.class, (long)pictures.getPicturesId());
        byte[] pic = picNew.getPhoto();

        InputStream targetStream = new ByteArrayInputStream(pic);
        try {
            SendPhoto message = new SendPhoto().setNewPhoto(briefcase, targetStream);
            message.setChatId(Long.toString(chat_id));
            this.sendPhoto(message);
        } catch (TelegramApiException ex) {
            Logger.getLogger(Bot.class.getName()).log(Level.SEVERE, null, ex);
        }

        session.close();
    }
}