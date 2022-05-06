<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <c:import url="../common/head.jsp"/>
</head>
<body>
<c:import url="../common/navbar.jsp"/>
<div class="container">
    <b>Vehicles</b>

    <form class="reservation__date-range" method="GET" action="vehicles">
        <div class="form-field">
            <label class="input-label" for="from">From</label>
            <input class="input-text" type="date" name="from" id="from" value="${param.from}"/>
        </div>
        <div class="form-field">
            <label class="input-label" for="to">To</label>
            <input class="input-text" type="date" name="to" id="to" value="${param.to}"/>
        </div>
        <input type="submit" value="Check">
    </form>

    <c:if test="${userRole == 'ADMIN'}">
        <a href="vehicles?action=CREATE_VEHICLE">Add new vehicle</a>
    </c:if>
    <c:choose>
        <c:when test="${vehicles.isEmpty()}">
            <i>No vehicles found</i>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>id</th>
                    <th>brand</th>
                    <th>model</th>
                    <th>dateOfRegistration</th>
                    <th>plateNumber</th>
                    <th>type</th>
                </tr>
                <c:forEach var="vehicle" items="${vehicles}">
                    <c:url var="updateReservationUrl" value="vehicles">
                        <c:param name="action">EDIT_VEHICLE</c:param>
                        <c:param name="id">${vehicle.id}</c:param>
                        <c:param name="brand">${vehicle.brand}</c:param>
                        <c:param name="model">${vehicle.model}</c:param>
                        <c:param name="dateOfRegistration">${vehicle.dateOfRegistration}</c:param>
                        <c:param name="plateNumber">${vehicle.plateNumber}</c:param>
                        <c:param name="type">${vehicle.type}</c:param>
                    </c:url>
                    <tr>
                        <td>${vehicle.id}</td>
                        <td>${vehicle.brand}</td>
                        <td>${vehicle.model}</td>
                        <td>${vehicle.dateOfRegistration}</td>
                        <td>${vehicle.plateNumber}</td>
                        <td>${vehicle.type}</td>

                        <td>
                            <form method="POST" action="reservations">
                                <input type="hidden" name="action" value="ADD_RESERVATION">
                                <input type="hidden" name="vehicleId" value="${vehicle.id}">
                                <input type="hidden" name="beginsAt" value="${param.from}">
                                <input type="hidden" name="endsAt" value="${param.to}">
                                <input type="submit" value="Book reservation">
                            </form>
                        </td>
                        <c:if test="${userRole == 'ADMIN'}">
                            <td>
                                <form method="POST" action="vehicles">
                                    <input type="hidden" name="action" value="DELETE_VEHICLE">
                                    <input type="hidden" name="id" value="${vehicle.id}">
                                    <input type="submit" value="Delete">
                                </form>
                            </td>
                            <td>
                                <a href="${updateReservationUrl}">Update</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

</div>

</body>
</html>
