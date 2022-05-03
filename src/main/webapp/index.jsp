<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <title>Test</title>
</head>
<body>

<c:import url="common/navbar.jsp"/>
<div class="container">

    <b>Lista utenti</b>
    <div>
        <c:choose>
            <c:when test="${users.isEmpty()}">
                <i>Nessun utente</i>
            </c:when>
            <c:otherwise>
                <ul class="user__list">
                    <c:forEach var="tmpUser" items="${users}">
                        <c:url var="removeUserUrl" value="index">
                            <c:param name="action">REMOVE_USER</c:param>
                            <c:param name="id">${tmpUser.id}</c:param>
                            <c:param name="firstName">${tmpUser.firstName}</c:param>
                            <c:param name="lastName">${tmpUser.lastName}</c:param>
                            <c:param name="role">${tmpUser.role}</c:param>
                        </c:url>
                        <li class="user__item">
                            <div>

                                <span class="user__fullname">${tmpUser.firstName} ${tmpUser.lastName}</span>
                                <span class="user__role">(${tmpUser.role})</span>
                            </div>
                            <a href="${removeUserUrl}">Remove</a>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
    <a class="button --dark" href="add-user.jsp">Aggiungi nuovo utente</a>
</div>
</body>
</html>
