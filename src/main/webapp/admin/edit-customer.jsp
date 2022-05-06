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
        <span class="form-header">Edit customer</span>
        <input type="hidden" name="action" value="EDIT_CUSTOMER"/>
        <input type="hidden" name="id" value="${param.id}"/>
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
            <input class="input-text" type="text" name="username" id="username" placeholder="Username"
                   value="${param.username}"/>
        </div>
        <button class="button-submit">Edit customer</button>
    </form>
</div>
</body>
</html>
