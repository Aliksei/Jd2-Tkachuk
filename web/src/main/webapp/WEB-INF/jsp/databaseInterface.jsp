<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Aliksei
  Date: 13.10.2018
  Time: 1:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <p style="font-size: large"> Here you can make actions with users table in database</p>
    <div>
        <p>Getting user by id</p>
    </div>

    <div>
        <p>Creating new user</p>
    </div>

    <div>
        <p>Deleting user</p>
    </div>

    <div>
        <p>Updating user</p>
    </div>
</head>
<body>
    <div>
        <p>Here you can see current instance of clients table</p>
    </div>
    <table style="width: 20%">
        <c:forEach items="${clients}" var="client">
            <tr>
                <td>${client.id}</td>
                <td>${client.firstName}</td>
                <td>${client.secondName}</td>
                <td>${client.age}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
