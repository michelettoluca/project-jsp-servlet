<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:import url="../common/head.jsp"/>
    <title>Sign in</title>
</head>
<body>
<div class="form-wrapper">
    <form method="POST" action="${pageContext.request.contextPath}/auth">
        <button type="button" name="back" onclick="history.back()">Go back</button>
        <span class="form-header">Sign in</span>
        <input type="hidden" name="action" value="SIGN_IN"/>
        <div class="form-field">
            <label class="input-label" for="username">Username</label>
            <input class="input-text" type="text" name="username" id="username" placeholder="Username"
                   value="${param.username}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="password">Password</label>
            <input class="input-text" type="password" name="password" id="password" placeholder="Password"/>
        </div>
        <button class="button-submit">Sign in</button>
    </form>
</div>
</body>
</html>
