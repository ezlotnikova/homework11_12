<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<title>Registration</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <font color="red">
            <c:out value="${message}"/>
            </font>
            <form action="${pageContext.request.contextPath}/registration" method="post">
                <div class="form-group col-md-3">
                    <label for="username">Username</label>
                    <input type="text" class="form-control" name="username" required maxlength="40" aria-describedby="usernameHelpInline">
                       <small id="passwordHelpInline" class="text-muted">
                       Your username must be no longer then 40 characters and may contain letters, numbers and spaces.
                       </small>
                </div>
                <div class="form-group col-md-3">
                    <label for="password">Password</label>
                    <input type="text" class="form-control" name="password" required maxlength="40" aria-describedby="usernameHelpInline">
                        <small id="passwordHelpInline" class="text-muted">
                        Your password must be 8-40 characters long, contain letters and numbers, and must not contain spaces or special characters.
                        </small>
                </div>
                <div class="form-group col-md-3">
                      <label for="role">Role</label>
                      <select name="role" class="form-control" required aria-describedby="selectRoleInline">
                              <option selected></option>
                              <c:forEach items="${roles}" var="role">
                                    <option>${role.roleName}</option>
                              </c:forEach>
                      </select>
                      <small id="passwordHelpInline" class="text-muted">
                      Please choose your role.
                      </small>
                </div>
                <br>
                <button type="submit" class="btn btn-outline-success">Register</button>
            </form>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/pages/js.jsp" %>
</body>
</html>