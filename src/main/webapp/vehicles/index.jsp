<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <%--    <c:import url="../common/head.jsp"/>--%>
</head>
<body>
<%--<c:import url="../common/navbar.jsp"/>--%>
<div class="content">
    <div class="container">
        <b>Vehicles</b>
        <c:choose>
            <c:when test="${vehicles.isEmpty()}">
                <i>No vehicles found</i>
            </c:when>
            <c:otherwise>
                <table border="1">
                    <tr>
                        <th>id</th>
                        <th>brand</th>
                        <th>model</th>
                        <th>dateOfRegistration</th>
                        <th>plateNumber</th>
                        <th>type</th>
                    </tr>
                    <c:forEach var="reservation" items="${vehicles}">
                        <c:url var="updateReservationUrl" value="vehicles">
                            <c:param name="action">UPDATE_VEHICLE</c:param>
                            <c:param name="id">${reservation.id}</c:param>
                            <c:param name="brand">${reservation.brand}</c:param>
                            <c:param name="model">${reservation.model}</c:param>
                            <c:param name="dateOfRegistration">${reservation.dateOfRegistration}</c:param>
                            <c:param name="plateNumber">${reservation.plateNumber}</c:param>
                            <c:param name="type">${reservation.type}</c:param>
                        </c:url>
                        <tr>
                            <td>${reservation.id}</td>
                            <td>${reservation.brand}</td>
                            <td>${reservation.model}</td>
                            <td>${reservation.dateOfRegistration}</td>
                            <td>${reservation.plateNumber}</td>
                            <td>${reservation.type}</td>
                            <td>
                                <form method="POST" action="vehicles">
                                    <input type="hidden" name="action" value="DELETE_VEHICLE">
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

        <a class="button --dark" href="?action=CREATE_VEHICLE">Add new vehicle</a>
    </div>
</div>

</body>
</html>
