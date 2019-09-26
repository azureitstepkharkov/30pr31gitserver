
<%@page import="java.util.ArrayList"%>
<%@page import="code.main"%>
<%@page import="code.Addres"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Addres> list=new main().getList();
%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Thing</title>
    </head>
    <body>
        <h1>Hell !</h1>
        <select name="w1" >
            <c:forEach var="R" items="${list}">                  
                <option> <c:out value="${R.toString()} "/> </option>                    
            </c:forEach>
        </select>
    </body>
</html>
