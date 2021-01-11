<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 11.01.2021
  Time: 09:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <fmt:setLocale value="${sessionScope.language}"/>
    <fmt:setBundle basename="locale" var="locale"/>
    <fmt:message bundle="${locale}" key="locale.footer" var="footer"/>
    <footer class="container-fluid text-center footer">
    <p id="child">${footer}</p>
    </footer>