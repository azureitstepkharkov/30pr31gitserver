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
public class Reminder {
    int Id;
    int UserId;
    String TTN;
    String Status;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int UserId) {
        this.UserId = UserId;
    }

    public String getTTN() {
        return TTN;
    }

    public void setTTN(String TTN) {
        this.TTN = TTN;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
