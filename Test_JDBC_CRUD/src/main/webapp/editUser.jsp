<%@ page import="model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Toll
  Date: 29.12.2017
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%!
    public Object datePattern(User user) {
        if (user.getDob() == null) return "dd/MM/yyyy";
        else return user.getDob();
    }
%>
<html>
<head>
    <title>Edit user</title>
</head>
<body>
<form method="post" action="/userAction" name="formEdit">
    User id: <input type="text" readonly=readonly name="id" value="<c:out value="${user.id}"/>">
    First name: <input type="text" name="firstName" value="<c:out value="${user.firstName}"/>">
    Last name: <input type="text" name="lastName" value="<c:out value="${user.lastName}"/>">
    Email: <input type="text" name="email" value="<c:out value="${user.email}"/>">
    Date of Birth: <c:choose>
    <c:when test="${user.dob!=null}">
        <input type="text" name="dob" value="  <fmt:formatDate pattern="dd/MM/yyyy" value="${user.dob}"/>">
    </c:when>
    <c:otherwise> <input type="text" name="dob" value=" <c:out value="dd/MM/yyyy"/>"> </c:otherwise>
</c:choose>
    <input type="submit" value="Submit">
</form>

</body>
</html>
