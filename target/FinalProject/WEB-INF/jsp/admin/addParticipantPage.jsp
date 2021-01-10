<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 30.12.2020
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.participantID" var="participantID"/>
<fmt:message bundle="${locale}" key="locale.leaveBlank" var="leaveBlankParticipantID"/>
<fmt:message bundle="${locale}" key="locale.name" var="name"/>
<fmt:message bundle="${locale}" key="locale.surName" var="surName"/>
<fmt:message bundle="${locale}" key="locale.secondName" var="secondName"/>

<fmt:message bundle="${locale}" key="locale.year" var="year"/>
<fmt:message bundle="${locale}" key="locale.budget" var="budget"/>
<fmt:message bundle="${locale}" key="locale.gross" var="gross"/>
<fmt:message bundle="${locale}" key="locale.addParticipant" var="addParticipant"/>
<fmt:message bundle="${locale}" key="locale.updateParticipant" var="updateParticipant"/>
<fmt:message bundle="${locale}" key="locale.submit" var="submit"/>
<c:if test="${sessionScope.get('user') == null && sessionScope.get('user').role ne 'admin'}">
    <c:redirect url="/index.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <title>{addParticipant}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<c:import url="../menu/menu.jsp"/>

<div class="container-fluid text-center wrapper">
    <div class="row content">
        <c:import url="../menu/leftside.jsp"/>
        <br class="col-sm-8 text-left mainContent">
            <c:if test="${requestScope.get('errorMessage')!=null}">
                <h3 class="red"><c:out value="${requestScope.get('errorMessage')}"/></h3>
                <c:remove var="errorMessage" scope="request"/>
            </c:if>
            <c:if test="${requestScope.get('successMessage')!=null}">
                <h3 class="green"><c:out value="${requestScope.get('successMessage')}"/></h3>
                <c:remove var="successMessage" scope="request"/>
            </c:if>
            <br>
            <br action="DispatcherServlet" method="post">
                <p>
                    <label><input type="radio" name="command" value="add-participant" checked/>${addParticipant}</label>
                    <label><input type="radio" name="command" value="update-participant"></label>
                </p>
                <label for="movieID">${participantID}<br></label>
                <input id="movieID" class="form-control" type="text" name="participant-id"
                    placeholder="${leaveBlankParticipantID}"/>
                <br/>
                <label for="title">${name}<br></label>
                <input id="title" class="form-control" type="text" name="name" required>
                <br/>
                <label for="title">${surName}<br></label>
                <input id="title" class="form-control" type="text" name="surName" required>
                <br/>
                <label for="title">${secondName}<br></label>
                <input id="title" class="form-control" type="text" name="secondName">
                <br/>
                <br/>
                <button type="submit" class="btn btn-primary">${submit}</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
