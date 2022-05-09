<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:import url="../common/head.jsp"/>
    <title>Profile</title>
</head>
<body>
<c:import url="../common/navbar.jsp"/>
<div class="container">
    <h1>${user.firstName} ${user.lastName}</h1>

    <div>
        <c:url var="editUserUrl" value="users">
            <c:param name="action" value="EDIT_USER"/>
            <c:param name="origin" value="profile"/>
            <c:param name="id" value="${user.id}"/>
        </c:url>
        <a href="${editUserUrl}">Edit profile</a>
    </div>
    
</div>
</body>
</html>
