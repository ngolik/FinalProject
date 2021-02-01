<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 29.12.2020
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<jsp:useBean id="movie" class="by.golik.finalproject.entity.Movie" scope="request"/>
<jsp:useBean id="vote" class="by.golik.finalproject.entity.Vote" scope="request"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.year" var="year"/>
<fmt:message bundle="${locale}" key="locale.genre" var="genre"/>
<fmt:message bundle="${locale}" key="locale.budget" var="budget"/>
<fmt:message bundle="${locale}" key="locale.runtime" var="runtime"/>
<fmt:message bundle="${locale}" key="locale.gross" var="gross"/>
<fmt:message bundle="${locale}" key="locale.votes" var="votes"/>
<fmt:message bundle="${locale}" key="locale.banUser" var="banUser"/>
<fmt:message bundle="${locale}" key="locale.next" var="next"/>
<fmt:message bundle="${locale}" key="locale.previous" var="previous"/>
<fmt:message bundle="${locale}" key="locale.submit" var="submit"/>
<fmt:message bundle="${locale}" key="locale.addRating" var="addRating"/>
<fmt:message bundle="${locale}" key="locale.rating" var="rating"/>
<fmt:message bundle="${locale}" key="locale.noRating" var="noRating"/>
<fmt:message bundle="${locale}" key="locale.yourRating" var="yourRating"/>

<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial scale=1">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <title><c:out value="${movie.title}"/></title>
</head>
<!--Событие onload используется как указатель, что веб-страница полностью загружена, включая содержание, изображения, стилевые файлы и внешние скрипты.-->
<body onload="active()">

<c:import url="../menu/menu.jsp"/>
<script language="javascript">
    function active() {
        document.getElementById("movie-page").className = "active";
    }
</script>

<div class="container-fluid text-center wrapper">
<br class="row content">

    <div class="col-sm-8 text-left mainContent">
        <c:if test="${sessionScope.get('language') eq 'ru' || sessionScope.get('language')==null}">
            <h1><c:out value="${movie.title}"/></h1>
            <br>
            <tr class="thead-dark">
                    ${year} <c:out value="${movie.year}"/><br/>
                    ${runtime} <c:out value="${movie.runtime}"/><br/>
                    ${budget} <c:out value="${movie.budget}"/><br/>
                    ${gross} <c:out value="${movie.gross}"/><br/>
                        <c:choose>
                        <c:when test="${vote.score==0}">
                            ${noRating}<br/>
                        </c:when>
                        <c:otherwise>
                    ${rating} <c:out value="${vote.score}"/><br/>
                        </c:otherwise>
                        </c:choose>
            </tr>


                        <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
            </div>

            <c:if test='${sessionScope.get("user").role eq "user"}'>

                <h4> Оцените фильм от 1 до 10 </h4>
                <label>
                    <a href="DispatcherServlet?command=add-rating&movieID=${movie.id}&rating=1">1</a>
                    <a href="DispatcherServlet?command=add-rating&movieID=${movie.id}&rating=2">2</a>
                    <a href="DispatcherServlet?command=add-rating&movieID=${movie.id}&rating=3">3</a>
                    <a href="DispatcherServlet?command=add-rating&movieID=${movie.id}&rating=4">4</a>
                    <a href="DispatcherServlet?command=add-rating&movieID=${movie.id}&rating=5">5</a>
                    <a href="DispatcherServlet?command=add-rating&movieID=${movie.id}&rating=6">6</a>
                    <a href="DispatcherServlet?command=add-rating&movieID=${movie.id}&rating=7">7</a>
                    <a href="DispatcherServlet?command=add-rating&movieID=${movie.id}&rating=8">8</a>
                    <a href="DispatcherServlet?command=add-rating&movieID=${movie.id}&rating=9">9</a>
                    <a href="DispatcherServlet?command=add-rating&movieID=${movie.id}&rating=10">10</a>
                </label>

            </c:if>
        </c:if>

    </div>
        <c:import url="../menu/rightside.jsp"/>
</br>
</div>
<c:import url="../menu/footer.jsp"/>
</body>
</body>
</html>
