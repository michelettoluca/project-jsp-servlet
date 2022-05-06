<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <title>Add new reservation</title>
</head>
<body>

<div class="form-wrapper">
    <form method="POST" action="${pageContext.request.contextPath}/reservations">
        <span class="form-header">Edit reservation</span>
        <input type="hidden" name="action" value="UPDATE_RESERVATION"/>
        <input type="hidden" name="id" value="${param.id}"/>
        <div class="form-field">
            <label class="input-label" for="userId">User</label>
            <select class="input-select" name="userId" id="userId">
                <c:forEach var="user" items="${users}">
                    <option value="${user.id}" ${user.id == param.userId ? "selected" : ""}>
                            ${user.firstName} ${user.lastName}
                    </option>
                </c:forEach> class="input-select"
            </select>
        </div>
        <div class="form-field">
            <label class="input-label" for="vehicleId">Vehicle</label>
            <select class="input-select" name="vehicleId" id="vehicleId">
                <c:forEach var="reservation" items="${vehicles}">
                    <option value="${reservation.id}" ${reservation.id == param.vehicleId ? "selected" : ""}>
                            ${reservation.brand} - ${reservation.model}
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="form-field">
            <label class="input-label" for="beginsAt">From</label>
            <input class="input-text" type="date" name="beginsAt" id="beginsAt" value="${param.beginsAt}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="endsAt">To</label>
            <input class="input-text" type="date" name="endsAt" id="endsAt" value="${param.endsAt}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="status">Status</label>
            <select class="input-select" name="status" id="status">
                <option value="PENDING" ${status == param.status ? "selected" : ""}>
                    Pending
                </option>
                <option value="APPROVED" ${status == param.status ? "selected" : ""}>
                    Approved
                </option>
                <option value="DENIED" ${status == param.status ? "selected" : ""}>
                    Denied
                </option>
            </select>
        </div>
        <button class="button-submit">Edit reservation</button>
    </form>
</div>
</body>
</html>
