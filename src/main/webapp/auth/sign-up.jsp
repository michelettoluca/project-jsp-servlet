<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:import url="../common/head.jsp"/>
    <title>Sign up</title>
</head>
<body>
<div class="form-wrapper">
    <form method="POST" action="${pageContext.request.contextPath}/auth">
        <button type="button" name="back" onclick="history.back()">Go back</button>
        <span class="form-header">Sign up</span>
        <input type="hidden" name="action" value="SIGN_UP"/>
        <div class="form-field">
            <label class="input-label" for="firstName">First name</label>
            <input class="input-text" type="text" name="firstName" id="firstName" placeholder="First name"
                   value="${param.firstName}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="lastName">Last name</label>
            <input class="input-text" type="text" name="lastName" id="lastName" placeholder="Last name"
                   value="${param.lastName}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="username">Username</label>
            <input class="input-text" type="text" name="username" id="username" placeholder="Username"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="password">Password</label>
            <input class="input-text" type="password" name="password" id="password" placeholder="Password"/>
        </div>
        <button class="button-submit">Sign up</button>
    </form>
</div>
</body>
</html>
