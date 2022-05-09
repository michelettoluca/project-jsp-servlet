<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:choose>
    <c:when test="${availableVehicles.isEmpty()}">
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
            <c:forEach var="availableVehicle" items="${availableVehicles}">
                <c:url var="updateReservationUrl" value="vehicles">
                    <c:param name="action">EDIT_VEHICLE</c:param>
                    <c:param name="id">${availableVehicle.id}</c:param>
                </c:url>
                <tr>
                    <td>${availableVehicle.id}</td>
                    <td>${availableVehicle.brand}</td>
                    <td>${availableVehicle.model}</td>
                    <td>${availableVehicle.dateOfRegistration}</td>
                    <td>${availableVehicle.plateNumber}</td>
                    <td>${availableVehicle.type}</td>

                    <td>
                        <form method="POST" action="reservations">
                            <input type="hidden" name="action" value="ADD_RESERVATION">
                            <input type="hidden" name="vehicleId" value="${availableVehicle.id}">
                            <input type="hidden" name="beginsAt" value="${param.from}">
                            <input type="hidden" name="endsAt" value="${param.to}">
                            <input type="submit" value="Book reservation">
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>