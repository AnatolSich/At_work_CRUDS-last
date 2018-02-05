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
    <title>Create Car</title>
</head>
<body>
<h1>Create Car</h1>
<form action="/CarCommit?creation=new" method="post">
    Car Number <input type="text" name="carNumber">
    OwnerID <input type="text" name="ownerId">
    <input type="submit" value="Submit">
</form>
<br>
<br>
<br>
<a href="parking.jsp">Start page</a>
</body>
</html>
