<%@page import="java.util.Properties"%>
<%@ page import = "java.io.*,java.util.*,javax.mail.*"%>
<%@ page import = "javax.mail.internet.*,javax.activation.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>

<%
   String result;
   
   String to = request.getParameter("to");

   String from = request.getParameter("from");
   
   String text = request.getParameter("text");
   
   String subj = request.getParameter("subj");
   
   String host = "localhost";
   
   if(to != "" && from != "" && host != "" && text != ""){
        // Get system properties object
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session mailSession = Session.getDefaultInstance(properties);

        try {
           // Create a default MimeMessage object.
           MimeMessage message = new MimeMessage(mailSession);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));

           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO,
                                    new InternetAddress(to));
           // Set Subject: header field
           message.setSubject(subj);

           // Now set the actual message
           message.setText(text);

           // Send message
           Transport.send(message);
           result = "Sent message successfully....";
        } catch (MessagingException mex) {
           mex.printStackTrace();
           result = "Error: unable to send message....";
        }
   }
   else 
        result = "Please, fill all fields";
%>
<!DOCTYPE html> 
<html> 
    <head> 
        <title>Emailer</title> 
        <meta charset="UTF-8"> 
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    </head> 
    <body> 
        <h1>Send email message</h1> 
        <p> 
            <% out.println("Result: " + result + "\n"); %> 
        </p> 
        <form method="post" action="index.jsp"> 
            <label>From: <input type="email" name="from" id="from" placeholder="example@domen.com"></label> 
            <label>To: <input type="email" name="to" id="to" placeholder="example@domen.com"></label> 
            <label>Subject: <input type="text" name="subj" id="subj" placeholder="Subject"></label> 
            <label>Text: <input type="text" name="text" id="text" placeholder="Message"></label> 
            <input type="submit">
        </form> 
    </body> 
</html>