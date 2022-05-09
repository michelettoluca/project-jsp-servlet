<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="../common/head.jsp"/>
    <title>Profile</title>
</head>
<body>
<c:import url="../common/navbar.jsp"/>
<div class="container">
    <h1>${user.firstName} ${user.lastName}</h1>

    <div>
        <c:url var="editUserUrl" value="users">
            <c:param name="action" value="EDIT_USER"/>
            <c:param name="origin" value="profile"/>
            <c:param name="id" value="${user.id}"/>
        </c:url>
        <a href="${editUserUrl}">Edit profile</a>
    </div>


    <c:if test="${sessionScope.userRole != 'ADMIN'}">
        <table>
            <tr>
                <th>Brand</th>
                <th>Model</th>
                <th>From</th>
                <th>To</th>
                <th>Status</th>
            </tr>
            <c:forEach var="reservation" items="${user.reservations}">
                <tr>
                    <td>${reservation.vehicle.brand}</td>
                    <td>${reservation.vehicle.model}</td>
                    <td>${reservation.beginsAt}</td>
                    <td>${reservation.endsAt}</td>
                    <td>${reservation.status}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
</body>
</html>
