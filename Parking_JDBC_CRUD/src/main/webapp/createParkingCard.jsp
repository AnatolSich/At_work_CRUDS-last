<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 26.01.2018
  Time: 9:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Parking Card</title>
</head>
<body>
<h1>Create Parking Card</h1>
<form method="post" action="/ParkingCardCommit">
    ID <input type="text" readonly name="id" value="Auto">
    CarNumber <input type="text" name="carNumber">
    Start <input type="date" name="start" value="${datePattern}">
    Finish <input type="date" name="finish" value="${datePattern}">
    Period <input type="text" name="period">
    PayCheck <input type="text" name="payCheck">
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
