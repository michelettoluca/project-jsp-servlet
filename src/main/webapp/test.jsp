<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
<ul>
    <c:forEach var="tmpUser" items="${users}">
        <li>
            <b>${tmpUser.firstName} ${tmpUser.lastName}</b>
            <p>Role: ${tmpUser.role}</p>
        </li>
    </c:forEach>
</ul>
</body>
</html>
