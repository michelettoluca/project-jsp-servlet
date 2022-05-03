<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${user != null}">
    <div>
        <span class="user__fullname">${user.firstName} ${user.lastName}</span>
        <span class="user__role">(${user.role})</span>
    </div>
</c:if>
