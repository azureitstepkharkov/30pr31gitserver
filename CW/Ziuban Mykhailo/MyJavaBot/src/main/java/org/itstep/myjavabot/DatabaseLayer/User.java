/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itstep.myjavabot.DatabaseLayer;

/**
 *
 * @author merkyr
 */
public class User {

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getTelegram() {
        return Telegram;
    }

    public void setTelegram(String Telegram) {
        this.Telegram = Telegram;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
    int Id;
    String Name;
    String Telegram;
    int Status;

    public User(String Telegram) {
        this.Telegram = Telegram;
        this.Status = 1;
    }

    public User() {
    }
}
