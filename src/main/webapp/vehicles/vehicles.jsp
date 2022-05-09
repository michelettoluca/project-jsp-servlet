<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <c:import url="../common/head.jsp"/>
    <title>Vehicle list</title>
</head>
<body>
<c:import url="../common/navbar.jsp"/>
<div class="container">
    <b>Vehicles</b>


    <c:choose>
        <c:when test="${userRole == 'ADMIN'}">
            <a href="vehicles?action=ADD_VEHICLE">Add new vehicle</a>
            <c:import url="vehicle-list.jsp"/>
        </c:when>
        <c:when test="${userRole == 'CUSTOMER'}">
            <c:import url="avaliable-vehicle-list.jsp"/>
        </c:when>
    </c:choose>
</div>

</body>
</html>
