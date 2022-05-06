<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="addOrEditText" value="${param.action == 'ADD_USER' ? 'Add new' : 'Edit'}"/>

<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <title>${addOrEditText} customer</title>
</head>
<body>
<div class="form-wrapper">
    <form method="POST" action="${pageContext.request.contextPath}/users">
        <span class="form-header">${addOrEditText} customer</span>
        <input type="hidden" name="origin" value="${param.origin}"/>
        <input type="hidden" name="action" value="${param.action}"/>
        <input type="hidden" name="id" value="${param.id}"/>
        <div class="form-field">
            <label class="input-label" for="firstName">First name</label>
            <input class="input-text" type="text" name="firstName" id="firstName" placeholder="First name"
                   value="${user.firstName}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="lastName">Last name</label>
            <input class="input-text" type="text" name="lastName" id="lastName" placeholder="Last name"
                   value="${user.lastName}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="username">Username</label>
            <input class="input-text" type="text" name="username" id="username" placeholder="Username"
                   value="${user.username}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="password">Password</label>
            <input class="input-text" type="password" name="password" id="password" placeholder="Password"
                   value="${user.password}"/>
        </div>
        <button class="button-submit">${addOrEditText} customer</button>
    </form>
</div>
</body>
</html>
