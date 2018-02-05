<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 26.01.2018
  Time: 9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Parking Card</title>
</head>
<body>
<h1>Edit Parking Card</h1>
<form method="post" action="/ParkingCardCommit">
    ID <input type="text" readonly name="id" value="<c:out value="${parkingCard.id}"/>">
    CarNumber <input type="text" name="carNumber" value="<c:out value="${parkingCard.carNumber}"/>"><br>
    Start <input type="date" name="start" value="<fmt:formatDate pattern="${datePattern}" value="${parkingCard.start}"/>">
    <c:choose>
        <c:when test="${parkingCard.finish==null}">
            Finish <input type="date" name="finish" value="${datePattern}">
        </c:when>
        <c:otherwise>
            Finish <input type="date" name="finish" value="<fmt:formatDate pattern="${datePattern}" value="${parkingCard.finish}"/>">
        </c:otherwise>
    </c:choose>
    Period <input type="text" name="period" value="<c:out value="${parkingCard.period}"/>"><br>
    PayCheck <input type="text" name="payCheck" value="<c:out value="${parkingCard.payCheck}"/>">
    <input type="submit" value="Submit">
</form>
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
