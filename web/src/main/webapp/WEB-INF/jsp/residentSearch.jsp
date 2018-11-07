<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Aliksei
  Date: 07.11.2018
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Result</title>
</head>
<body>

<h1>Show residents</h1>

    <form action="/residents" method = "POST" >

        <input type="hidden" name="currentPage" value="1">

        <div class="form-group col-md-4">
            <br/>
                First Name: <input type = "text" name = "firstName" value="${firstName}"/>
            <br />
            Last Name: <input type = "text" name = "secondName" value="${secondName}"/>
            <br/>
            Gender Name: <input type = "text" name = "gender" value="${gender}"/>
            <br/>
            limit: <input type = "number" name = "limit" value="${limit}"/>
            <br/>
            offset : <input type = "number" name = "offset" value="${offset}"/>
            <br/>
            Row per page :
            <input id="records" name="recordsPerPage" value="${recordsPerPage}"/>
            <button type="submit">Search</button>
        </div>
    </form>

${requestScope.get("firstName")}


<br/>

    <c:if test="${not empty residents}">
    <div class="row col-md-6">
        <table>
            <tr>
                <th>number</th>
                <th>Id</th>
                <th>FirstName</th>
                <th>SecondName</th>
                <th>Gender</th>
                <th>City</th>
                <th>Country</th>

            </tr>

            <c:forEach items="${residents}" var="resident" varStatus="loop">
                <tr>
                    <td>${loop.index + 1}</td>
                    <td>${resident.id}</td>
                    <td>${resident.firstName}</td>
                    <td>${resident.secondName}</td>
                    <td>${resident.gender}</td>
                    <td>${resident.city}</td>
                    <td>${resident.country}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <nav aria-label="Navigation for residents">
        <ul class="pagination">

            <%--<c:set var="firstName" value="${requestScope.firstName}"/>--%>
            <%--<c:set var="secondName" value="${requestScope.secondName}"/>--%>
            <%--<c:set var="" value="${requestScope.firstName}"/>--%>
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="residents?recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
                </li>
            </c:if>



            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <form action="/residents" method = "POST" >

                            <input type="hidden" name="currentPage" value="${i}">
                            <input type="hidden" name="firstName" value="${firstName}">
                            <input type="hidden" name="secondName" value="${secondName}">
                            <input type="hidden" name="gender" value="${gender}">
                            <input type="hidden" name="limit" value="${limit}">
                            <input type="hidden" name="offset" value="${offset}">
                            <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">

                            <button type="submit">${i}</button>
                            </div>
                        </form>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

        </c:if>

    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
</nav>

</body>
</html>
