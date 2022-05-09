<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="../common/head.jsp"/>
    <title>Reservations</title>
</head>
<body>
<div class="form-wrapper">
    <form method="POST" action="${pageContext.request.contextPath}/reservations">
        <span class="form-header">Edit reservation</span>
        <input type="hidden" name="action" value="EDIT_RESERVATION"/>
        <input type="hidden" name="id" value="${param.id}"/>
        <input type="hidden" name="status" value="PENDING"/>
        <div class="form-field">
            <label class="input-label">Vehicle</label>
            <span>${reservation.vehicle.brand} - ${reservation.vehicle.model}</span>
        </div>
        <div class="form-field">
            <label class="input-label">Plate #</label>
            <span>${reservation.vehicle.plateNumber}</span>
        </div>
        <hr>
        <div class="form-field">
            <label class="input-label" for="beginsAt">From</label>
            <input class="input-text" type="date" name="beginsAt" id="beginsAt" value="${reservation.beginsAt}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="endsAt">To</label>
            <input class="input-text" type="date" name="endsAt" id="endsAt" value="${reservation.endsAt}"/>
        </div>
        <button class="button-submit">Edit reservation</button>
    </form>
</div>
</body>
</html>
