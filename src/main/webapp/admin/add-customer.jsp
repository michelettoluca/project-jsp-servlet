<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <title>Add user</title>
</head>
<body>
<div class="form-wrapper">
    <form method="POST" action="${pageContext.request.contextPath}/admin">
        <span class="form-header">Add new customer</span>
        <input type="hidden" name="action" value="ADD_CUSTOMER"/>
        <div class="form-field">
            <label class="input-label" for="firstName">First name</label>
            <input class="input-text" type="text" name="firstName" id="firstName" placeholder="First name"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="lastName">Last name</label>
            <input class="input-text" type="text" name="lastName" id="lastName" placeholder="Last name"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="username">Username</label>
            <input class="input-text" type="text" name="username" id="username" placeholder="Username"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="password">Password</label>
            <input class="input-text" type="password" name="password" id="password" placeholder="password"/>
        </div>
        <button class="button-submit">Add user</button>
    </form>
</div>
</body>
</html>