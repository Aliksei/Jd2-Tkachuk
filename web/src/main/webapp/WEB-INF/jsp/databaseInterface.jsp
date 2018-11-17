<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created com IntelliJ IDEA.
  User: Aliksei
  Date: 13.10.2018
  Time: 1:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <p style="font-size: large"> Here you can list of countries in database</p>
</head>
<body>
    <div>
        <p>Here you can see current instance of countries table</p>
    </div>
    <table style="width: 20%">
        <c:forEach items="${countries}" var="country">
            <tr>
                <td>${country.id}</td>
                <td>${country.name}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
