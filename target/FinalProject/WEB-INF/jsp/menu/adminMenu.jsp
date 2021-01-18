<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 30.12.2020
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.addMovie" var="addMovie"/>
<fmt:message bundle="${locale}" key="locale.addParticipant" var="addParticipant"/>
<fmt:message bundle="${locale}" key="locale.viewAllUsers" var="viewAllUsers"/>
<fmt:message bundle="${locale}" key="locale.viewAllBanned" var="viewAllBanned"/>
<fmt:message bundle="${locale}" key="locale.addGenreForMovie" var="addGenreForMovie"/>
<fmt:message bundle="${locale}" key="locale.deleteUser" var="deleteUser"/>
<div class="col-sm-2 sidenav">
    <c:if test='${sessionScope.get("user").role eq "admin"}'>
        <p><a class="admin" href="DispatcherServlet?command=add-movie">${addMovie}</a></p>
        <p><a class="admin" href="DispatcherServlet?command=add-participant">${addParticipant}</a></p>
        <p><a class="admin" href="DispatcherServlet?command=view-all-users">${viewAllUsers}</a></p>
        <p><a class="admin" href="DispatcherServlet?command=view-all-banned">${viewAllBanned}</a></p>
        <p><a class="admin" href="DispatcherServlet?command=add-genre-for-movie">${addGenreForMovie}</a></p>
        <p><a class="admin" href="DispatcherServlet?command=delete-user">${deleteUser}</a></p>
    </c:if>

</div>

