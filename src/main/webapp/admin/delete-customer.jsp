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
        <span class="form-header">Delete customer</span>
        <p>Are you sure you want to delete this customer?</p>
        <input type="hidden" name="action" value="DELETE_CUSTOMER"/>
        <input type="hidden" name="id" value="${param.id}"/>
        <button class="button-submit">Delete customer</button>
    </form>
</div>
</body>
</html>
