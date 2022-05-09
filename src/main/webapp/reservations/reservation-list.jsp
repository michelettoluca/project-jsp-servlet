<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <c:import url="../common/head.jsp"/>
    <title>Reservations</title>
</head>
<body>
<c:import url="../common/navbar.jsp"/>
<c:if test="${sessionScope.userRole != 'ADMIN'}">
    <div class="container">
        <table>
            <tr>
                <th>Brand</th>
                <th>Model</th>
                <th>From</th>
                <th>To</th>
                <th>Status</th>
            </tr>
            <c:forEach var="reservation" items="${reservations}">
                <c:url var="editReservationUrl" value="reservations">
                    <c:param name="action" value="EDIT_RESERVATION"/>
                    <c:param name="id" value="${reservation.id}"/>
                </c:url>
                <tr>
                    <td>${reservation.vehicle.brand}</td>
                    <td>${reservation.vehicle.model}</td>
                    <td>${reservation.beginsAt}</td>
                    <td>${reservation.endsAt}</td>
                    <td>${reservation.status}</td>
                    <c:if test="${reservation.status != 'APPROVED'}">
                        <td>
                            <a href="${editReservationUrl}">Edit</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</c:if>
</body>
</html>
