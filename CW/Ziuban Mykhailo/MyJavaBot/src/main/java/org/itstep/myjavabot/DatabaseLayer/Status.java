/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itstep.myjavabot.DatabaseLayer;

import java.util.Objects;

/**
 *
 * @author merkyr
 */
public class Status {

    public int getId() {
        return Id;
    }

    public Status(int Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }

    @Override
    public String toString() {
        return "Status{" + "Id=" + Id + ", Name=" + Name + '}';
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.Id;
        hash = 17 * hash + Objects.hashCode(this.Name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Status other = (Status) obj;
        if (this.Id != other.Id) {
            return false;
        }
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        return true;
    }
    int Id;
    String Name;
}
