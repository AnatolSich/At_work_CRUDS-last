<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 26.01.2018
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Car</title>
</head>
<body>
<h1>Edit Car</h1>
<form action="/CarCommit?creation=${oldCarNumber}" method="post">
    Car Number <input type="text" name="carNumber" value="<c:out value="${car.carNumber}"/>">
    OwnerID <input type="text" name="ownerId" value="<c:out value="${car.ownerId}"/>">
    <input type="submit" value="Submit">
</form>
<br>
<br>
<br>
<a href="parking.jsp">Start page</a>
</body>
</html>
