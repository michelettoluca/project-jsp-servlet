<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<c:import url="../common/head.jsp"/>
<body>
<c:import url="../common/navbar.jsp"/>
<div class="content">
    <div class="customers">
        <span class="customers__header">Customers</span>
        <form method="GET" action="admin">
            <input class="input-text" type="text" name="query" placeholder="Search customer">
            <input class="--hidden" type="submit"/>
        </form>
        <a href="admin?action=ADD_CUSTOMER" class="button--dashed">
            + Add another customer
        </a>
        <div class="customers__list">
            <c:choose>
                <c:when test="${customers.isEmpty()}">
                    <i>No reservations found</i>
                </c:when>
                <c:otherwise>
                    <c:forEach var="tmpCustomer" items="${customers}">
                        <c:url var="customerUrl" value="/admin">
                            <c:param name="userId" value="${tmpCustomer.id}"/>
                        </c:url>
                        <a class="customers__list__item"
                           href="${customerUrl}"
                           data-active="${user.id == tmpCustomer.id}">
                        <span class=" customer__fullname">
                                ${tmpCustomer.firstName} ${tmpCustomer.lastName}
                            </span>
                            <div class="customer__reservations-status --yellow"></div>
                        </a>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <c:import url="customer-details.jsp"/>
</div>
</body>
</html>
