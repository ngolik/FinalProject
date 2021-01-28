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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page.css">
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
<div class="container-fluid text-center wrapper"></div>
<div class="row content">
    <c:import url="../menu/adminMenu.jsp"/>
    <div class="col-sm-8 text-left mainContent">
        <c:if test="${sessionScope.get('language') eq 'ru' || sessionScope.get('language')==null}">
            <h1><c:out value="${movie.title}"/></h1>
            <div class="col-sm-9">
                    ${year} <c:out value="${movie.year}"/><br/>
                    ${runtime} <c:out value="${movie.runtime}"/><br/>
                    ${budget} <c:out value="${movie.budget}"/><br/>
                    ${gross} <c:out value="${movie.gross}"/><br/>
            </div>

            <c:if test='${sessionScope.get("user").role eq "admin" || sessionScope.get("user").role eq "user"}'>

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


        <div class="col-sm-12">
            <hr>

            <div class="row">

                <ul class="pagination">
                    <c:if test="${requestScope.currentPage > 1}">
                        <li>
                            <a href="DispatcherServlet?command=${param.command}&page=${requestScope.currentPage - 1}&id=${param.id}">${previous}</a>
                        </li>
                    </c:if>

                    <%--For displaying Page numbers.
                    The when condition does not display a link for the current page--%>
                    <c:if test="${requestScope.noOfPages>1}">
                        <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                            <c:choose>
                                <c:when test="${requestScope.currentPage eq i}">
                                    <li class="active"><a href="#">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li>
                                        <a href="DispatcherServlet?command=${param.command}&page=${i}&id=${param.id}">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:if>

                    <%--For displaying Next link --%>
                    <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
                        <li>
                            <a href="DispatcherServlet?command=${param.command}&page=${requestScope.currentPage + 1}&id=${param.id}">${next}</a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>

    </div>
        <c:import url="../menu/rightside.jsp"/>
    </div>
</div>
</body>

</head>
</html>
