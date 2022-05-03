<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/style.css">
    <title>Add user</title>
</head>
<body>
<div class="form-wrapper">
    <form method="POST" action="${pageContext.request.contextPath}/users">
        <span class="form-header">Add new user</span>
        <input type="hidden" name="action" value="CREATE_USER"/>
        <div class="form-field">
            <label class="input-label" for="firstName">First name</label>
            <input class="input-text" type="text" name="firstName" id="firstName" placeholder="First name"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="lastName">Last name</label>
            <input class="input-text" type="text" name="lastName" id="lastName" placeholder="Last name"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="role">Role</label>
            <select class="input-select" name="role" id="role">
                <option value="CUSTOMER" selected>Customer</option>
                <option value="ADMIN">Admin</option>
            </select>
        </div>
        <button class="button-submit">Add user</button>
    </form>
</div>
</body>
</html>
