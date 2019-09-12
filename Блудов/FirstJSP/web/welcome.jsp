<%-- 
    Document   : welcome
    Created on : Aug 15, 2019, 6:36:03 PM
    Author     : asp
--%>

<%@page import="javax.faces.component.html.HtmlSelectOneListbox"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String strName = request.getParameter("name");

            if (strName != null && strName.length() > 0)
            {
        %>
            Hello, world! I am <%= strName %>
        <%
            }
        %>
    </body>
</html>
