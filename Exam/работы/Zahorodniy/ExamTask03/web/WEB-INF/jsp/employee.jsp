<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Employee list:</h1>
        <table>
            <tr>
                <th>ID</th><th>Employee Name</th><th>IdCode</th>
            </tr>
            <c:forEach var="user" items="${model}">
                <tr>
                    <td>
                        <c:out value="${user.getId()}"></c:out>
                    </td>
                    <td>
                         <c:out value="${user.getEmployees_Name()}"></c:out>
                    </td>
                    <td>
                         <c:out value="${user.getIdCode()}"></c:out>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
