<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 31.12.2020
  Time: 00:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="user" class="by.golik.finalproject.entity.User" scope="request"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="locale" var="locale"/>

<fmt:message bundle="${locale}" key="locale.banUser" var="banUser"/>
<fmt:message bundle="${locale}" key="locale.submit" var="submit"/>
<fmt:message bundle="${locale}" key="locale.username" var="userName"/>
<fmt:message bundle="${locale}" key="locale.registrationDate" var="registrationdate"/>
<fmt:message bundle="${locale}" key="locale.email" var="email"/>
<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial scale=1">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <title>${user.userName} profile</title>

</head>
<body>

<c:import url="menu.jsp"/>

<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="leftside.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <div>
                <h1><c:out value="${user.userName}"/> profile</h1>
                <div class="col-sm-3">
                    <c:if test="${not empty param.errorMessage}">
                        <h4 class="red"><c:out value="${param.errorMessage}"/></h4>
                    </c:if>
                </div>
                <div class="col-sm-9">
                    ${userName} <c:out value="${user.userName}"/><br/>
                    ${email} <c:out value="${user.email}"/><br/>
                    ${registrationdate} <c:out value="${user.registrationDate}"/><br/>
                </div>
            </div>

        </div>

        <c:import url=""/>

    </div>
</div>

<c:import url=""/>

</body>
</html>