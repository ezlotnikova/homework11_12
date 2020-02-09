<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.gmail.zlotnikova.util.DateTimeFormatUtil" %>

<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<title>Users</title>
</head>
<body>
<div class="container">
    <br>
    <div class="alert alert-success" role="alert">
        Welcome, <b>${username}</b>! You signed in as ${role}.
    </div>
            <table class="table table-striped table-hover">
            <thead>
            <tr>
                 <th>Username</th>
                 <th>Created</th>
                 <th>Role</th>
                 <th>Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.username}"/></td>
                    <td><c:out value="${DateTimeFormatUtil.formatLongToDateTime(user.createdBy)}"/></td>
                    <td><c:out value="${user.roleName}"/></td>
                    <td><c:out value="${user.description}"/></td>
                </tr>
            </c:forEach>
            </tbody>
            </table>
</div>
<%@include file="/WEB-INF/pages/js.jsp" %>
</body>
<html>