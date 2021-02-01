
<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 27.12.2020
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.movieID" var="movieID"/>
<fmt:message bundle="${locale}" key="locale.title" var="title"/>
<fmt:message bundle="${locale}" key="locale.year" var="year"/>
<fmt:message bundle="${locale}" key="locale.runtime" var="runtime"/>
<fmt:message bundle="${locale}" key="locale.budget" var="budget"/>
<fmt:message bundle="${locale}" key="locale.gross" var="gross"/>
<fmt:message bundle="${locale}" key="locale.addMovie" var="addMovie"/>
<fmt:message bundle="${locale}" key="locale.updateMovie" var="updateMovie"/>
<fmt:message bundle="${locale}" key="locale.submit" var="submit"/>

<c:if test="${sessionScope.get('user') == null && sessionScope.get('user').type ne 'admin'}">
    <c:redirect url="/index.jsp"/>
</c:if>
<!DOCTYPE html>
<head>
    <title>${addMovie}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</head>
<body>

<c:import url="../menu/menu.jsp"/>

<div class="container-fluid text-center wrapper">
    <br class="row content">

        <c:import url="../menu/adminMenu.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <c:if test="${requestScope.get('errorMessage')!=null}">
                <h3 class="red"><c:out value="${requestScope.get('errorMessage')}"/></h3>
                <c:remove var="errorMessage" scope="request"/>
            </c:if>
            <c:if test="${requestScope.get('successMessage')!=null}">
                <h3 class="green"><c:out value="${requestScope.get('successMessage')}"/></h3>
                <c:remove var="successMessage" scope="request"/>
            </c:if>
            <br>
            <form action="DispatcherServlet" method="post">
                <p>
                    <label><input type="radio" name="command" value="add-movie" checked/>${addMovie}</label>
                    <label><input type="radio" name="command" value="update-movie"/>${updateMovie}</label>
                </p>
                <label for="movieID">${movieID}<br></label>
                <input id="movieID" class="form-control" type="text" name="id"/>
                <br/>
                <label for="title">${title}<br></label>
                <input id="title" class="form-control" type="text" name="title" required/>
                <br/>
                <label for="year">${year}<br></label>
                <input id="year" class="form-control" type="number" name="year" required/>
                <br/>
                <label for="runtime">${runtime}<br></label>
                <input id="runtime" class="form-control" type="number" name="runtime"/>
                <br/>
                <label for="budget">${budget}<br></label>
                <input id="budget" class="form-control" type="number" name="budget"/>
                <br/>
                <label for="gross">${gross}<br></label>
                <input id="gross" class="form-control" type="number" name="gross"/>
                <br/>
                <button type="submit" class="btn btn-primary">${submit}</button>
            </form>
        </div>

        <c:import url="../menu/rightside.jsp"/>

    </br>
</div>
<c:import url="../menu/footer.jsp"/>
</body>
