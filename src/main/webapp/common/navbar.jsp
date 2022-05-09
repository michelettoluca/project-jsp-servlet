<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="nav__wrapper">
    <nav class="nav">
        <span class="nav__logo">Rental Car</span>
        <div class="nav__items">
            <a class="nav__item" href="${pageContext.request.contextPath}">Home</a>
            <c:if test="${sessionScope.userRole != null}">
                <a class="nav__item" href="vehicles">Vehicles</a>
            </c:if>
            <c:if test="${sessionScope.userRole == 'ADMIN'}">
                <a class="nav__item" href="admin">Admin</a>
            </c:if>
        </div>

        <div class="nav__auth">
            <c:choose>
                <c:when test="${sessionScope.userId != null}">
                    <a href="profile" class="button">Profile</a>
                    <a href="auth?action=SIGN_OUT" class="button">Sign out</a>
                </c:when>
                <c:otherwise>
                    <a href="auth?action=SIGN_IN" class="button">Sign in</a>
                    <a href="auth?action=SIGN_UP" class="button">Sign up</a>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
</div>
