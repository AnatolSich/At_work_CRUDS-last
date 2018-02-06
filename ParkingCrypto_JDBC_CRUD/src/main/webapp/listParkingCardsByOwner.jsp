<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 26.01.2018
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Parking Cards by Owner</title>
</head>
<body>

<h1>List of All Parking Cards by Owner</h1>
<table border="1">
    <tr>
        <th>OwnerName</th>
        <th>CarNumber</th>
        <th>Start</th>
        <th>Finish</th>
        <th>Period</th>
        <th>PayCheck</th>
        <th colspan="3">Operations</th>
    </tr>
    <c:forEach var="tempParkingCard" items="${parkingCards}">
        <tr>
            <td><a href="/OwnerCommit?action=OWNER&ownerId=<c:out value="${owner.id}"></c:out>"><c:out value="${owner.name}"/></a></td>
            <td><a href="/CarCommit?action=CAR&carNumber=<c:out value="${tempParkingCard.carNumber}"></c:out>"><c:out value="${tempParkingCard.carNumber}"/></a></td>
            <td><fmt:formatDate pattern="${datePattern}" value="${tempParkingCard.start}"/></td>
            <td><fmt:formatDate pattern="${datePattern}" value="${tempParkingCard.finish}"/></td>
            <td><c:out value="${tempParkingCard.period}"/></td>
            <td><c:out value="${tempParkingCard.payCheck}"/></td>
            <td><a href="/ParkingCardCommit?action=EDIT&id=<c:out value="${tempParkingCard.id}"/>">Edit</a></td>
            <td> <a href="/ParkingCardCommit?action=DELETE&id=<c:out value="${tempParkingCard.id}"/>">Delete</a> </td>
            <td> <a href="/ParkingCardCommit?action=CALCULATE&list=CARDS_BY_OWNER&parkingCardId=<c:out value="${tempParkingCard.id}"/>&ownerId=<c:out value="${owner.id}"></c:out>">Calculate</a> </td>
        </tr>
    </c:forEach>
</table>
<br>
<table border="1">
    <tr>
        <th>Full payCheck by owner - <c:out value="${owner.name}"/></th>
    </tr>
    <tr>
        <td><c:out value="${fullPayCheck}"/></td>
    </tr>
</table>
<br>
<br>
<br>
<a href="/ParkingCardCommit?action=LIST">All parking cards</a>
<br>
<br>
<br>
<a href="parking.jsp">Start page</a>
</body>
</html>
