<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${user != null}">
    <div class="customer__details">
        <div class="customers__details__header">
            <span class="header__customer__fullname">${user.firstName} ${user.lastName}</span>
            <div class="header__customer__actions">
                <c:url var="editUserUrl" value="users">
                    <c:param name="action" value="EDIT_USER"/>
                    <c:param name="origin" value="admin"/>
                    <c:param name="id" value="${user.id}"/>
                </c:url>
                <a class="customer_action --edit" href="${editUserUrl}">Edit</a>
                <form method="POST" action="users">
                    <input type="hidden" name="action" value="DELETE_USER"/>
                    <input type="hidden" name="origin" value="admin"/>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="Delete">
                </form>
            </div>
        </div>
        <span class="customer__reservation__title">Reservations</span>
        <c:choose>
            <c:when test="${user.reservations.isEmpty()}">
                <i>No reservations found</i>
            </c:when>
            <c:otherwise>
                <div class="reservation__list">
                    <c:forEach var="reservation" items="${user.reservations}">
                        <div class="reservation__item">
                            <div class="reservation__status" data-status="${reservation.status}"></div>
                            <div class="vehicle__info__list">
                                <div class="vehicle__info__item">
                                    <span class="info__label">Vehicle</span>
                                    <span class="info__value">${reservation.vehicle.brand} - ${reservation.vehicle.model}</span>
                                </div>
                                <div class="vehicle__info__item">
                                    <span class="info__label">Plate #</span>
                                    <span class="info__value">${reservation.vehicle.plateNumber}</span>
                                </div>
                                <div class="vehicle__info__item">
                                    <span class="info__label">Period</span>
                                    <span class="info__value">${reservation.beginsAt} - ${reservation.endsAt}</span>
                                </div>
                            </div>
                            <c:if test="${reservation.status == 'PENDING'}">
                                <div class="reservation__actions">
                                    <form method="POST" action="reservations?userId=${user.id}">
                                        <input type="hidden" name="action" value="EDIT_RESERVATION">
                                        <input type="hidden" name="id" value="${reservation.id}">
                                        <input type="hidden" name="status" value="APPROVED">
                                        <input class="reservation__action --approve" type="submit" value="Approve">
                                    </form>
                                    <form method="POST" action="reservations?userId=${user.id}">
                                        <input type="hidden" name="action" value="EDIT_RESERVATION">
                                        <input type="hidden" name="id" value="${reservation.id}">
                                        <input type="hidden" name="status" value="DENIED">
                                        <input class="reservation__action --deny" type="submit" value="Deny">
                                    </form>
                                </div>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</c:if>