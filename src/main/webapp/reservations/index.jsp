<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="content">
    <div class="container">
        <b>Reservations</b>
        <c:choose>
            <c:when test="${reservations.isEmpty()}">
                <i>No reservation found</i>
            </c:when>
            <c:otherwise>
                <table border="1">
                    <tr>
                        <th>id</th>
                        <th>user</th>
                        <th>vehicle</th>
                        <th>beginsAt</th>
                        <th>endsAt</th>
                        <th>type</th>
                        <th>status</th>
                    </tr>
                    <c:forEach var="reservation" items="${reservations}">
                        <c:url var="updateReservationUrl" value="reservations">
                            <c:param name="action">UPDATE_RESERVATION</c:param>
                            <c:param name="id">${reservation.id}</c:param>
                            <c:param name="userId">${reservation.user.id}</c:param>
                            <c:param name="vehicleId">${reservation.vehicle.id}</c:param>
                            <c:param name="beginsAt">${reservation.beginsAt}</c:param>
                            <c:param name="endsAt">${reservation.endsAt}</c:param>
                            <c:param name="status">${reservation.status}</c:param>
                        </c:url>
                        <tr>
                            <td>${reservation.id}</td>
                            <td>${reservation.user}</td>
                            <td>${reservation.vehicle}</td>
                            <td>${reservation.beginsAt}</td>
                            <td>${reservation.endsAt}</td>
                            <td>${reservation.status}</td>
                            <td>
                                <form method="POST" action="reservations">
                                    <input type="hidden" name="action" value="DELETE_RESERVATION">
                                    <input type="hidden" name="id" value="${reservation.id}">
                                    <input type="submit" value="Delete">
                                </form>
                            </td>
                            <td>
                                <a href="${updateReservationUrl}">Update</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>

        <a class="button --dark" href="?action=CREATE_RESERVATION">Add new reservation</a>
    </div>
</div>
</body>
</html>
