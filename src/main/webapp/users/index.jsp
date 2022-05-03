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

<c:import url="../common/navbar.jsp"/>
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
                        <li class="user__item">
                            <div>
                                <span class="user__fullname">${tmpUser.firstName} ${tmpUser.lastName}</span>
                                <span class="user__role">(${tmpUser.role})</span>
                            </div>
                            <form method="POST" action="${pageContext.request.contextPath}/users">
                                <input type="hidden" name="action" value="DELETE_USER"/>
                                <input type="hidden" name="id" value="${tmpUser.id}"/>
                                <input type="hidden" name="firstName" value="${tmpUser.firstName}"/>
                                <input type="hidden" name="lastName" value="${tmpUser.lastName}"/>
                                <input type="hidden" name="role" value="${tmpUser.role}"/>

                                <button type="submit">Delete</button>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
    <a class="button --dark" href="${pageContext.request.contextPath}/users/create.jsp">Aggiungi nuovo utente</a>

    <c:import url="user.jsp"/>
</div>
</body>
</html>
