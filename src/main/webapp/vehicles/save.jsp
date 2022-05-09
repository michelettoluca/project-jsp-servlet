<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="addOrEditText" value="${param.action == 'ADD_VEHICLE' ? 'Add new' : 'Edit'}"/>

<html>
<head>
    <c:import url="../common/head.jsp"/>
    <title>${addOrEditText} vehicle</title>
</head>
<body>

<div class="form-wrapper">
    <form method="POST" action="${pageContext.request.contextPath}/vehicles">
        <span class="form-header">${addOrEditText} vehicle</span>
        <input type="hidden" name="action" value="${param.action}"/>
        <input type="hidden" name="id" value="${vehicle.id}"/>
        <div class="form-field">
            <label class="input-label" for="brand">Brand</label>
            <input class="input-text" type="text" name="brand" id="brand" placeholder="Brand" value="${vehicle.brand}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="model">Model</label>
            <input class="input-text" type="text" name="model" id="model" placeholder="Model" value="${vehicle.model}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="dateOfRegistration">Date of registration</label>
            <input class="input-text" type="date" name="dateOfRegistration" id="dateOfRegistration"
                   placeholder="Date of registration" value="${vehicle.dateOfRegistration}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="plateNumber">Plate number</label>
            <input class="input-text" type="text" name="plateNumber" id="plateNumber" placeholder="Plate number"
                   value="${vehicle.plateNumber}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="type">Type</label>
            <input class="input-text" type="text" name="type" id="type" placeholder="Type" value="${vehicle.type}"/>
        </div>
        <button class="button-submit">${addOrEditText} vehicle</button>
    </form>
</div>
</body>
</html>
