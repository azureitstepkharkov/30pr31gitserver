/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.itstep.myjavabot.DatabaseLayer;

import java.sql.DriverManager;//управление версиями драйевров

import java.sql.Connection;//механизмы соединения БД
import java.sql.Statement;//для запросов select (update)
import java.sql.CallableStatement;//для работы с ХП
import java.sql.ResultSet;//хранилище результата выборки
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
                         //select

import java.util.Vector;

public class DataBase 
{
    public Vector<String> getEmplNames()
    {
        Vector<String> names = new Vector<>(1000);
        //
        try {
            //0 драйвер для работы с базой данных
            //1 регистрация класса-драйвера
            Class.forName("org.sqlite.JDBC");
            //2 создание соединения Connection
            Connection conn = null;
            conn = DriverManager.getConnection("jdbc:sqlite:E:\\MyData\\test.db");
            //3 Выполнение команды sql
            //для select, updatem insert но НЕ ГОДИТЬСЯ для ХП
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("SELECT EMPLOYEESID, EMPLOYEES_NAME, IDCODE FROM EMPLOYEES");
                int num = 0;
                while (rs.next())
                {
                    int id = rs.getInt("EMPLOYEESID");
                    String str = rs.getString("EMPLOYEES_NAME");
                    String idCode = rs.getString("IDCODE");
                    String result = "Строка #"+num+" id = "+id+" имя = "+str+" IdCode = "+idCode;
                    System.out.println(result);
                    num++;
                    names.add(str);
                }
               conn.close();//ее место в секции finally
            //
        } catch (ClassNotFoundException ex) {
            System.out.println("не зарегистрирован файл драйвера");
        } catch (SQLException ex) {
             System.out.println("Ошибка в тексте запроса sql");
        }

        //
      
        return names;
    }
    
    public boolean IsExist(int telegram)
    {
        try {
            //0 драйвер для работы с базой данных
            //1 регистрация класса-драйвера
            Class.forName("org.sqlite.JDBC");
            //2 создание соединения Connection
            Connection conn = DriverManager.getConnection("jdbc:sqlite:E:\\MyData\\NovaPoshta.db");
            //3 Выполнение команды sql
            //для select, updatem insert но НЕ ГОДИТЬСЯ для ХП
                Statement stm = conn.createStatement();
                ResultSet rs = stm.executeQuery("Select Count() from Users where fio = 'Hello'");

                while (rs.next())
                {
                    int id = rs.getInt("Exist");
                    if (id != 0) {
                        return true;
                    }
                    else
                        return false;
                }
                    

            //
        } catch (ClassNotFoundException ex) {
            System.out.println("не зарегистрирован файл драйвера");
        } catch (SQLException ex) {
             System.out.println("Ошибка в тексте запроса sql");
        }
        return  false;
    }
    
    public User getUser(int telegramId) {
        
        return new User();
    }
    
    public boolean isUser(int telegramId) {
        
        return true;
    }
    
    public void addUser(User user) {
        
    }

    public ArrayList<Reminder> getListReminder(int telegramId){
        return new ArrayList<Reminder>();
    }
    
    public  Request getRequest(String TTN){
        return new Request();
    }

    
}