<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 26.01.2018
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parking</title>
</head>
<body>
<h1>Parking</h1>

<table border="1">
    <tr>
        <th><a href="/OwnerCommit?action=LIST">Owners</a></th>
        <th><a href="/CarCommit?action=LIST">Cars</a></th>
        <th><a href="/ParkingCardCommit?action=LIST">Parking Cards</a></th>
    </tr>
</table>
<br>
<br>
<br>
<a href="/ParkingCardCommit?action=CALCULATE">Calculate all payChecks</a>
</body>
</html>
