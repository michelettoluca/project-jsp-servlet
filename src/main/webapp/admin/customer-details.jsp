<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${user != null}">
    <div class="customer__details">
        <div class="customers__details__header">
            <span class="header__customer__fullname">${user.firstName} ${user.lastName}</span>
            <div class="header__customer__actions">
                <c:url var="updateUserUrl" value="admin">
                    <c:param name="action">EDIT_CUSTOMER</c:param>
                    <c:param name="id">${user.id}</c:param>
                    <c:param name="firstName">${user.firstName}</c:param>
                    <c:param name="lastName">${user.lastName}</c:param>
                    <c:param name="username">${user.username}</c:param>
                </c:url>
                <a class="customer_action --edit" href=${updateUserUrl}>Edit</a>
                <form method="POST" action="admin">
                    <input type="hidden" name="action" value="DELETE_CUSTOMER"/>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="submit" value="Delete">
                </form>
            </div>
        </div>
        <span class="customer__reservation__title">Reservations</span>
        <c:choose>
            <c:when test="${userReservations.isEmpty()}">
                <i>No reservations found</i>
            </c:when>
            <c:otherwise>
                <div class="reservation__list">
                    <c:forEach var="reservation" items="${userReservations}">
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
                                    <form method="POST" action="admin?userId=${user.id}">
                                        <input type="hidden" name="action" value="UPDATE_RESERVATION_STATUS">
                                        <input type="hidden" name="reservationId" value="${reservation.id}">
                                        <input type="hidden" name="reservationStatus" value="APPROVED">
                                        <input class="reservation__action --approve" type="submit" value="Approve">
                                    </form>
                                    <form method="POST" action="admin?userId=${user.id}">
                                        <input type="hidden" name="action" value="UPDATE_RESERVATION_STATUS">
                                        <input type="hidden" name="reservationId" value="${reservation.id}">
                                        <input type="hidden" name="reservationStatus" value="DENIED">
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