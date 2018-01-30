<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 29.12.2017
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of users</title>
</head>
<body>
<table border="1">
    <thead>
    <tr>
        <th> User ID</th>
        <th> First name</th>
        <th> Last name</th>
        <th> Email</th>
        <th> Date of birth</th>
        <th colspan="2"> Actions to commit</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td><c:out value="${user.id}"/></td>
            <td><c:out value="${user.firstName}"/></td>
            <td><c:out value="${user.lastName}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.dob}"/></td>
            <td><a href="userAction?action=edit&userId=<c:out value="${user.id}"/>"> Update </a></td>
            <td><a href="userAction?action=delete&userId=<c:out value="${user.id}"/>"> Delete </a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="userAction?action=create"> Add user </a></p>
</body>
</html>