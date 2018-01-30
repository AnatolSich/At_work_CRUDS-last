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
    <title>Edit Owner</title>
</head>
<body>
<h1>Edit Owner</h1>
<form method="post" action="/OwnerCommit">
    ID <input type="text" name="id" value=" <c:out value="${owner.id}"/>" readonly>
    Name <input type="text" name="name" value=" <c:out value="${owner.name}"/> ">
    <input type="submit" value="Submit">
</form>
<br>
<br>
<br>
<a href="parking.jsp">Start page</a>
</body>
</html>
