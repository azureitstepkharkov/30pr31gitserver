<%-- 
    Document   : index
    Created on : Oct 2, 2019, 11:24:44 PM
    Author     : merkyr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Email sender</title>
    </head>
    <body>
        <form action="MailServlet" method="POST">
            E-mail
            <input type="text" class="form-control" name="email" placeholder="Email">
            <br>
            Phone
            <input type="text" class="form-control" name="phone" placeholder="Phone">
            <br>
            Text
            <textarea name="message" class="form-control" placeholder="Message"></textarea>
            <br>
            <input type="submit" value="Отправить">
        </form>
    </body>
</html>
