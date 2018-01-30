<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 26.01.2018
  Time: 9:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Cars</title>
</head>
<body>
<h1>List of Cars</h1>
<table border="1">
    <tr>
        <th>Car number</th>
        <th>Owner ID</th>
        <th colspan="3">Operations</th>
    </tr>

        <c:forEach var="tempCar" items="${cars}">
        <tr>
            <td><c:out value="${tempCar.carNumber}"></c:out></td>
            <td><c:out value="${tempCar.ownerId}"></c:out></td>
            <td><a href="/CarCommit?action=EDIT&carNumber=<c:out value="${tempCar.carNumber}"></c:out>">Edit</a></td>
            <td><a href="/CarCommit?action=DELETE&carNumber=<c:out value="${tempCar.carNumber}"></c:out>">Delete</a></td>
            <td><a href="/ParkingCardCommit?action=LIST_PARKING_CARDS_BY_CAR_NUMBER&carNumber=<c:out value="${tempCar.carNumber}"></c:out>">ParkingCards</a></td>
        </tr>
    </c:forEach>


</table>
<a href="/CarCommit?action=CREATE">Create</a>
<br>
<br>
<br>
<a href="parking.jsp">Start page</a>
</body>
</html>
