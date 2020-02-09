<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<title>Log in</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <font color="red">
            <c:out value="${message}"/>
            </font>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-group col-md-3">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" name="username" required maxlength="40">
                  </div>
                <div class="form-group col-md-3">
                    <label for="password">Password</label>
                    <input type="text" class="form-control" name="password" required maxlength="40">
                </div>
                <button type="submit" class="btn btn-outline-success">Sign In</button>
            </form>
            <form action="${pageContext.request.contextPath}/registration" method="get">
                <button type="submit" class="btn btn-outline-success">Register</button>
            </form>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/pages/js.jsp" %>
</body>
</html>