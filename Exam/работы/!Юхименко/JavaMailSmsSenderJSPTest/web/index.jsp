<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Mails</title>
    </head>
    <body>
        <form name="Form" action="v3" method="post">
            <h4> e-mail должен  быть  заполнен </h4><br />
             <table>     
                <tr>    
         <%--<jsp:setProperty name="zz1" property="phone" />--%> 

                    <td>e-mail</td>
                    <td> <input type="text" name="mail" /> </td>
                </tr>
                <tr>    
                    <td>phone</td>
                    <td> <input type="text" name="phone" /> </td>
                </tr>
                <tr>    
                    <td>mes</td>
                    <td> <input type="text" name="mes" /> </td>
                </tr>
                  <tr>    
                    <td></td>
                    <td>  <input type="submit" value="Послать" /> </td>
                </tr>
            </table>
        </form>
    </body>
</html>
