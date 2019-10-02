<%-- 
    Document   : welcome
    Created on : Sep 26, 2019, 8:07:17 PM
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%
String name=(String)session.getAttribute("name");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
         <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
        <style>
            td{
                padding: 10px;
            }
            
            div{
                width: 50%;
                border: 1px solid black;
                border-radius: 5px;
                background-color: lightslategray;
            }
            
            but{
                margin: 10px;
            }
        </style>
    </head>
    <body>
    <center><h1>Hello, <%=name%></h1></center>
    </br>   
    <center><h2>Sending Email</h2></center>  
    <center>
        <div>
            <form action="SendMailServlet" method="POST">
                <table>
                    <tr>
                        <td>e-mail</td>
                        <td><input type="text" class="form-control" name="email" placeholder="Email"></td>
                    </tr>
                    <tr>
                        <td>phone</td>
                        <td><input type="text" class="form-control" name="phone" placeholder="Phone"></td>
                    </tr>
                    <tr>
                        <td>message text</td>
                        <td><textarea name="message" class="form-control" placeholder="Message"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center"><input class="btn btn-success but" type="submit" value="Отправить"></td>
                    </tr>
                </table>
            </form>
        </div>
    </center>    
</body>
</html>
