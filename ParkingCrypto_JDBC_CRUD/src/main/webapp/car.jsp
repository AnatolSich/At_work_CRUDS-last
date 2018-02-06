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
    <title>Car</title>
</head>
<body>
<h1>Car</h1>
<table border="1">
    <tr>
        <th>Car number</th>
        <th>Owner</th>
        <th colspan="3">Operations</th>
    </tr>
    <tr>
        <td><c:out value="${car.carNumber}"></c:out></td>
        <td><a href="/OwnerCommit?action=OWNER&ownerId=<c:out value="${car.ownerId}"></c:out>">Owner</a></td>
        <td><a href="/CarCommit?action=EDIT&carNumber=<c:out value="${car.carNumber}"></c:out>&ownerId=${car.ownerId}">Edit</a></td>
        <td><a href="/CarCommit?action=DELETE&carNumber=<c:out value="${car.carNumber}"></c:out>&list=CARS">Delete</a></td>
        <td>
            <a href="/ParkingCardCommit?action=LIST_PARKING_CARDS_BY_CAR_NUMBER&carNumber=<c:out value="${car.carNumber}"></c:out>">ParkingCards</a>
        </td>
    </tr>
</table>
<a href="/CarCommit?action=CREATE&ownerId=${car.ownerId}">Create new car</a>
<br>
<br>
<br>
<a href="parking.jsp">Start page</a>
</body>
</html>
