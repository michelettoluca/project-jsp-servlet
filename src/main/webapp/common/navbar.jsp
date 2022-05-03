<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="nav__wrapper">
    <nav class="nav">
        <span class="nav__logo">Rental Car</span>
        <div class="nav__items">
            <a class="nav__item" href="#">Home</a>
            <a class="nav__item" href="#">Parco auto</a>
        </div>

        <div class="nav__auth">
            <c:choose>
                <c:when test="${session.cookie.user}">
                    <a href="#" class="button">Sign Out</a>
                    <a href="#" class="button">Profile</a>
                </c:when>
                <c:otherwise>
                    <a href="#" class="button">Sign In</a>
                    <a href="#" class="button">Sign Up</a>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
</div>
