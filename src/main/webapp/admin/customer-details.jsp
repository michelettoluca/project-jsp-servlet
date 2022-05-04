<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${user != null}">
    <div class="customer__details">
        <div class="customers__details__header">
            <span class="header__customer__fullname">${user.firstName} ${user.lastName}</span>
            <div class="header__customer__actions">
                <a class="customer_action --edit" href="admin/edit-customer.jsp">Edit</a>
                <form method="POST" action="admin">
                    <input type="hidden" name="action" value="DELETE_USER"/>
                    <input type="hidden" name="id" value="${user.id}"/>
                    <input type="hidden" name="firstName" value="${user.firstName}"/>
                    <input type="hidden" name="lastName" value="${user.lastName}"/>
                    <input type="hidden" name="role" value="${user.role}"/>
                    <input type="submit" value="Delete">
                </form>
            </div>
        </div>
        <span class="customer__reservation__title">Reservations</span>
        <c:choose>
            <c:when test="${reservations.isEmpty()}">
                <i>No reservations found</i>
            </c:when>
            <c:otherwise>
                <div class="reservation__list">
                    <c:forEach var="reservation" items="${reservations}">
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
                                        <input type="hidden" name="action" value="APPROVE_RESERVATION">
                                        <input type="hidden" name="reservationId" value="${reservation.id}">
                                        <input class="reservation__action --approve" type="submit" value="Approve">
                                    </form>
                                    <form method="POST" action="admin?userId=${user.id}">
                                        <input type="hidden" name="action" value="DENY_RESERVATION">
                                        <input type="hidden" name="reservationId" value="${reservation.id}">
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