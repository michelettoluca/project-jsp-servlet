<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="../common/head.jsp"/>
    <title>Title</title>
</head>
<body>
<c:import url="../common/navbar.jsp"/>
<div class="container">
    <h1>${user.firstName} ${user.lastName}</h1>

    <div>
        <c:url var="editProfileUrl" value="">
            <c:param name="action" value="EDIT_PROFILE"/>
            <c:param name="firstName" value="${user.firstName}"/>
            <c:param name="lastName" value="${user.lastName}"/>
            <c:param name="username" value="${user.username}"/>
            <c:param name="password" value="${user.password}"/>
        </c:url>
        <a href="${editProfileUrl}">Edit profile</a>
    </div>

    <table>
        <tr>
            <th>Brand</th>
            <th>Model</th>
            <th>From</th>
            <th>To</th>
            <th>Status</th>
        </tr>
        <c:forEach var="reservation" items="${reservations}">
            <tr>
                <td>${reservation.vehicle.brand}</td>
                <td>${reservation.vehicle.model}</td>
                <td>${reservation.beginsAt}</td>
                <td>${reservation.endsAt}</td>
                <td>${reservation.status}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>