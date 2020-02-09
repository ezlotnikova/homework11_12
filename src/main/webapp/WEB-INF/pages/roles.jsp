<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<title>Roles</title>
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
                 <th>Role</th>
                 <th>Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${roles}" var="role">
                <tr>
                    <td><c:out value="${role.roleName}"/></td>
                    <td><c:out value="${role.description}"/></td>
                </tr>
            </c:forEach>
            </tbody>
            </table>
</div>
<%@include file="/WEB-INF/pages/js.jsp" %>
</body>
<html>