<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 26.01.2018
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Owners</title>
</head>
<body>
<h1>List of Owners</h1>
<table border="1">
    <tr>
        <th> id</th>
        <th> Name</th>
        <th colspan="4"> Operation</th>
    </tr>
    <c:forEach var="tempOwner" items="${owners}">
        <tr>
            <td><c:out value="${tempOwner.id}"></c:out></td>
            <td><c:out value="${tempOwner.name}"></c:out></td>
            <td><a href="/OwnerCommit?action=EDIT&id=<c:out value="${tempOwner.id}"></c:out>">Edit</a></td>
            <td><a href="/OwnerCommit?action=DELETE&id=<c:out value="${tempOwner.id}"></c:out>">Delete</a></td>
            <td><a href="/CarCommit?action=LIST_CARS_BY_OWNER_ID&ownerId=<c:out value="${tempOwner.id}"></c:out>">List Cars</a></td>
            <td><a href="/ParkingCardCommit?action=LIST_PARKING_CARDS_BY_OWNER_ID&ownerId=<c:out value="${tempOwner.id}"></c:out>">Parking Cards</a></td>
        </tr>
    </c:forEach>
</table>
<a href="/OwnerCommit?action=CREATE">Create</a>
<br>
<br>
<br>
<a href="parking.jsp">Start page</a>
</body>
</html>
