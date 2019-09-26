/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.examtask01;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;





/**
 *
 * @author frost
 */
public class Main {
     public static void main(String[] args) throws ParseException  {
         
             HttpConnect  response =new HttpConnect();
         try {
           String json= response.parseUrl(" http://headers.jsontest.com/");            
           System.out.println("Json \n"+json);   
             
             
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(json);   
            
            String UserAgent = jsonObject.get("User-Agent").toString();
            String Host = jsonObject.get("Host").toString();
            String AcceptLanguage = jsonObject.get("Accept-Language").toString();
            //print
            System.out.println("_________________________________________________________________________");
            System.out.println("User-Agent"+" : "+UserAgent);
            System.out.println("Host"+" : "+Host);
            System.out.println("Accept-Language"+" : "+AcceptLanguage);
 
         
         } catch (IOException ex) {
             Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     
}

    


