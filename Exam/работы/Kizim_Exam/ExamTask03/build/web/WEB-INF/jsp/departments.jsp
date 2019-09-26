
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
                <th>ID</th><th>Departments Name</th>
            </tr>
            <c:forEach var="user" items="${departments}">
                <tr>
                    <td>
                        <c:out value="${user.getId()}"></c:out>
                    </td>
                    <td>
                         <c:out value="${user.getName()}"></c:out>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
