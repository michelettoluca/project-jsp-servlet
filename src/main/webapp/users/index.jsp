<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="content">
    <div class="container">
        <b>Users</b>
        <c:choose>
            <c:when test="${users.isEmpty()}">
                <i>No user found</i>
            </c:when>
            <c:otherwise>
                <table border="1">
                    <tr>
                        <th>id</th>
                        <th>firstName</th>
                        <th>lastName</th>
                        <th>role</th>
                    </tr>
                    <c:forEach var="user" items="${users}">
                        <c:url var="updateUserUrl" value="users">
                            <c:param name="action">UPDATE_USER</c:param>
                            <c:param name="id">${user.id}</c:param>
                            <c:param name="firstName">${user.firstName}</c:param>
                            <c:param name="lastName">${user.lastName}</c:param>
                            <c:param name="role">${user.role}</c:param>
                        </c:url>
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.role}</td>
                            <td>
                                <form method="POST" action="users">
                                    <input type="hidden" name="action" value="DELETE_USER">
                                    <input type="hidden" name="id" value="${user.id}">
                                    <input type="submit" value="Delete">
                                </form>
                            </td>
                            <td>
                                <a href="${updateUserUrl}">Update</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>

        <a href="?action=CREATE_USER">Add new user</a>
    </div>
</div>
</body>
</html>
