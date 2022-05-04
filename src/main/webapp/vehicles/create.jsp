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
    <form method="POST" action="${pageContext.request.contextPath}/vehicles">
        <span class="form-header">Add new user</span>
        <input type="hidden" name="action" value="CREATE_VEHICLE"/>
        <div class="form-field">
            <label class="input-label" for="brand">Brand</label>
            <input class="input-text" type="text" name="brand" id="brand" placeholder="Brand"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="model">Model</label>
            <input class="input-text" type="text" name="model" id="model" placeholder="Model"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="dateOfRegistration">Date of registration</label>
            <input class="input-text" type="date" name="dateOfRegistration" id="dateOfRegistration"
                   placeholder="Date of registration"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="plateNumber">Plate number</label>
            <input class="input-text" type="text" name="plateNumber" id="plateNumber" placeholder="Plate number"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="type">Type</label>
            <input class="input-text" type="text" name="type" id="type" placeholder="Type"/>
        </div>
        <button class="button-submit">Create vehicle</button>
    </form>
</div>
</body>
</html>
