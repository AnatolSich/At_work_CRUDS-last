<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Owner</title>
</head>
<body>
<h1>Owner</h1>
<table border="1">
    <tr>
        <th> Name</th>
        <th colspan="4"> Operation</th>
    </tr>
    <tr>
        <td><c:out value="${owner.name}"></c:out></td>
        <td><a href="/OwnerCommit?action=EDIT&id=<c:out value="${owner.id}"></c:out>">Edit</a></td>
        <td><a href="/OwnerCommit?action=DELETE&id=<c:out value="${owner.id}"></c:out>">Delete</a></td>
        <td><a href="/CarCommit?action=LIST_CARS_BY_OWNER_ID&ownerId=<c:out value="${owner.id}"></c:out>">List Cars</a>
        </td>
        <td>
            <a href="/ParkingCardCommit?action=LIST_PARKING_CARDS_BY_OWNER_ID&ownerId=<c:out value="${owner.id}"></c:out>">Parking
                Cards</a></td>
    </tr>
</table>
<a href="/OwnerCommit?action=CREATE">Create new owner</a>
<br>
<br>
<br>
<a href="parking.jsp">Start page</a>
</body>
</html>
