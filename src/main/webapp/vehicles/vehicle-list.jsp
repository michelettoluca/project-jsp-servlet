<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
            <c:forEach var="availableVehicle" items="${vehicles}">
                <c:url var="editVehicleUrl" value="vehicles">
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
                        <form method="POST" action="vehicles">
                            <input type="hidden" name="action" value="DELETE_VEHICLE">
                            <input type="hidden" name="id" value="${availableVehicle.id}">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                    <td>
                        <a href="${editVehicleUrl}">Edit</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>