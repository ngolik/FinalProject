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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <title><c:out value="${participant.name}"/></title>
</head>
<!--Событие onload используется как указатель, что веб-страница полностью загружена, включая содержание, изображения, стилевые файлы и внешние скрипты.-->
<body onload="active()">

<c:import url="../menu/menu.jsp"/>

<script language="JavaScript">
    function active() {
        document.getElementById("movies-page").className = "active";
    }
</script>

<div class="container-fluid text-center wrapper"></div>
<br class="row content">
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

<script type="text/javascript">
    function MakeSort(element) {
        var selected = $('option:selected', element),
            href = selected.val();
        if (/*selected.text() === '-' || */!href) {
            return false;
        }
        document.location = href;
    }
</script>
        <br>
        <table class="table table-stripped">
            <thead>
            <tr class="thead-dark">
                <th>Movies with ${participant.name} ${participant.surname}</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="movie" items="${requestScope.all_movies}">
                <tr>
                    <td>

                            <c:out value="${movie.title}"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
<br>

    </div>

</br>
</body>
<c:import url="../menu/footer.jsp"/>
</body>
</html>
