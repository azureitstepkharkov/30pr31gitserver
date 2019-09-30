/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itstep.myjavabot.ControlLayer;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;

import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
/**
 *
 * @author merkyr
 */
public class Keyboards {
    public ReplyKeyboardMarkup GetMainKeyboard() {
        
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        KeyboardRow row = new KeyboardRow();
        row.set(0, "Треккинг");
        row.set(1, "Мои посылки");
        List<KeyboardRow> list = new ArrayList<KeyboardRow>();
        list.add(row);
        keyboardMarkup.setKeyboard(list);
        return keyboardMarkup;
    }
    
    public ReplyKeyboardMarkup GetFio() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        KeyboardRow row = new KeyboardRow();
        KeyboardButton button = new KeyboardButton();
        button.setRequestContact(Boolean.TRUE);
        row.add(button);
        List<KeyboardRow> list = new ArrayList<KeyboardRow>();
        list.add(row);
        keyboardMarkup.setKeyboard(list);
        return keyboardMarkup;
    }
       
    public InlineKeyboardMarkup GetTracking(String doc, int userId, String status) {
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setCallbackData(doc + "|us" + userId + "/us|st:" + status);
        button.setText("Подписаться под изменениями в документе");
        
        List<List<InlineKeyboardButton>> arrayListArray = new ArrayList<List<InlineKeyboardButton>>();
        
        List<InlineKeyboardButton> arrayList = new ArrayList<InlineKeyboardButton>();
        arrayList.add(button);
        arrayListArray.add(arrayList);
        keyboardMarkup.setKeyboard(arrayListArray);
        return  (InlineKeyboardMarkup) arrayListArray;
    }
}
