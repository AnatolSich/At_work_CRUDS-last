<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 26.01.2018
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Parking Cards by car number</title>
</head>
<body>

<h1>List of All Parking Cards by car number</h1>
<table border="1">
    <tr>
        <th>ID</th>
        <th>CarNumber</th>
        <th>Start</th>
        <th>Finish</th>
        <th>Period</th>
        <th>PayCheck</th>
        <th colspan="3">Operations</th>
    </tr>
    <c:forEach var="tempParkingCard" items="${parkingCards}">
        <tr>
            <td><c:out value="${tempParkingCard.id}"/></td>
            <td><c:out value="${tempParkingCard.carNumber}"/></td>
            <td><fmt:formatDate pattern="${datePattern}" value="${tempParkingCard.start}"/></td>
            <td><fmt:formatDate pattern="${datePattern}" value="${tempParkingCard.finish}"/></td>
            <td><c:out value="${tempParkingCard.period}"/></td>
            <td><c:out value="${tempParkingCard.payCheck}"/></td>
            <td><a href="/ParkingCardCommit?action=EDIT&id=<c:out value="${tempParkingCard.id}"/>">Edit</a></td>
            <td><a href="/ParkingCardCommit?action=DELETE&id=<c:out value="${tempParkingCard.id}"/>">Delete</a></td>
            <td><a href="/ParkingCardCommit?action=CALCULATE&parkingCardId=<c:out value="${tempParkingCard.id}"/>">Calculate</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<table border="1">
    <tr>
        <th>Full payCheck by Car - <c:out value="${carNumber}"/></th>
    </tr>
    <tr>
        <td><c:out value="${fullPayCheck}"/></td>
    </tr>
</table>
<br>
<br>
<br>
<a href="/ParkingCardCommit?action=CREATE"> Create parking card</a>
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
