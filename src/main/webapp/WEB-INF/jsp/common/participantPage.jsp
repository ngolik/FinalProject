<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 19.01.2021
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="participant" class="by.golik.finalproject.entity.Participant" scope="request"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.name" var="name"/>
<fmt:message bundle="${locale}" key="locale.surname" var="surname"/>
<fmt:message bundle="${locale}" key="locale.secondName" var="secondname"/>
<fmt:message bundle="${locale}" key="locale.position" var="position"/>
<fmt:message bundle="${locale}" key="locale.next" var="next"/>
<fmt:message bundle="${locale}" key="locale.previous" var="previous"/>
<fmt:message bundle="${locale}" key="locale.submit" var="submit"/>

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

    <title><c:out value="${participant.name}"/></title>
</head>
<!--Событие onload используется как указатель, что веб-страница полностью загружена, включая содержание, изображения, стилевые файлы и внешние скрипты.-->
<body onload="active()">
<body style="background-image:url(/images/main/9.jpg)">
<c:import url="../menu/menu.jsp"/>
<div class="container-fluid text-center wrapper"></div>
<div class="row content">
    <c:import url="../menu/adminMenu.jsp"/>
    <div class="col-sm-8 text-left mainContent">
        <c:if test="${sessionScope.get('language') eq 'ru' || sessionScope.get('language')==null}">
            <h1><c:out value="${participant.name}"/></h1>
            <br>
                <tr class="thead-dark">
                        ${name} <c:out value="${participant.name}"/><br/>
                        ${surname} <c:out value="${participant.surname}"/><br/>
                        ${secondname} <c:out value="${participant.secondName}"/><br/>
                        ${position} <c:out value="${participant.position}"/><br/>
                </tr>
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
</body>
</html>