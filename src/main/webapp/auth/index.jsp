<%--
  Created by IntelliJ IDEA.
  User: Luca Micheletto
  Date: 05/05/2022
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="?action=SIGN_UP">SIGN UP</a> |
<a href="?action=SIGN_IN">SIGN IN</a> |
<a href="?action=SIGN_OUT">SIGN OUT</a>
<br>

${sessionScope.userId}
${sessionScope.userRole}
${param.error}
</body>
</html>
