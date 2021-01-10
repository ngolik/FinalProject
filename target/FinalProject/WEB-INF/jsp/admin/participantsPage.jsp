<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 10.01.2021
  Time: 03:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.allParticipantsList" var="allParticipantsList"/>
<fmt:message bundle="${locale}" key="locale.name" var="name"/>
<fmt:message bundle="${locale}" key="locale.surName" var="surname"/>
<fmt:message bundle="${locale}" key="locale.secondName" var="second_name"/>
<!DOCTYPE html>
<html>
<head>
    <title>${allParticipantsList}</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/main/7.jpg">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body onload="active()">

<c:import url="../menu/menu.jsp"/>
<script language="javascript">
    function active() {
        document.getElementById("movies-page").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="../menu/leftside.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <h1>${allParticipantsList}</h1>
            <br>
            <table class="table">
                <thead>
                <tr>
                    <th>id</th>
                    <th>${name}</th>
                    <th>${surname}</th>
                    <th>${second_name}</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="participant" items="${requestScope.all_participants}">
                    <tr>
                        <td><a href="DispatcherServlet?command=view-actor&actor-id=${participant.id}">
                            <c:out value="${participant.id}"/></a></td>

                        <td><c:out value="${participant.name}"/></td>
                        <td><c:out value="${participant.surname}"/></td>
                        <td><c:out value="${participant.secondName}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <c:import url="../menu/rightside.jsp"/>

    </div>
</div>


</body>
</html>
