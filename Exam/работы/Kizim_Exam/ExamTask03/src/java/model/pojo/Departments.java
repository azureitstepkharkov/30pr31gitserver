/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.pojo;


public class Departments {

    public Departments() {
    }


    public char getName() {
        return name;
    }



    public void setName(char Name) {
        this.name = Name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    private long id = 1L;
    private char name;
}
