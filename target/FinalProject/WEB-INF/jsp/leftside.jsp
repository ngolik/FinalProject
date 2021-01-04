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
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.addMovie" var="addMovie"/>
<fmt:message bundle="${locale}" key="locale.addParticipant" var="addParticipant"/>
<fmt:message bundle="${locale}" key="locale.viewAllUsers" var="viewAllUsers"/>
<fmt:message bundle="${locale}" key="locale.viewAllBanned" var="viewAllBanned"/>
<fmt:message bundle="${locale}" key="locale.viewAllParticipants" var="viewAllParticipants"/>
<fmt:message bundle="${locale}" key="locale.newMovies" var="newMovies"/>
<div class="col-sm-2 sidenav">
    <c:if test='${sessionScope.get("user").role eq "admin"}'>
        <p><a class="admin" href="DispatcherServlet?command=add-movie">${addMovie}</a></p>
        <p><a class="admin" href="DispatcherServlet?command=add-movie">${addParticipant}</a></p>
        <p><a class="admin" href="DispatcherServlet?command=add-movie">${viewAllUsers}</a></p>
        <p><a class="admin" href="DispatcherServlet?command=add-movie">${viewAllBanned}</a></p>
        <p><a class="admin" href="DispatcherServlet?command=add-movie">${viewAllParticipants}</a></p>
    </c:if>
    <p><a href="DispatcherServlet?command=latest-movies">${newMovies}</a></p>
</div>
