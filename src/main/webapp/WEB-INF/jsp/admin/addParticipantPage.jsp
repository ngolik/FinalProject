<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 30.12.2020
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="by.golik.finalproject.entity.Position"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.participantID" var="participantID"/>
<fmt:message bundle="${locale}" key="locale.leaveBlank" var="leaveBlankParticipantID"/>
<fmt:message bundle="${locale}" key="locale.name" var="name"/>
<fmt:message bundle="${locale}" key="locale.surname" var="surname"/>
<fmt:message bundle="${locale}" key="locale.secondName" var="secondName"/>
<fmt:message bundle="${locale}" key="locale.position" var="position"/>
<fmt:message bundle="${locale}" key="locale.addParticipant" var="addParticipant"/>
<fmt:message bundle="${locale}" key="locale.updateParticipant" var="updateParticipant"/>
<fmt:message bundle="${locale}" key="locale.submit" var="submit"/>

<c:if test="${sessionScope.get('user') == null && sessionScope.get('user').role ne 'admin'}">
    <c:redirect url="/index.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
    <title>${addParticipant}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style7.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<c:import url="../menu/menu.jsp"/>

<div class="container-fluid text-center wrapper">
    <br class="row content">
        <c:import url="../menu/adminMenu.jsp"/>
        <br class="col-sm-8 text-left mainContent">

        <%=request.getAttribute("successMessage")%>

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
                <label><input type="radio" name="command" value="add-participant" checked/>${addParticipant}</label>
                <label><input type="radio" name="command" value="update-participant"/>${updateParticipant}</label>
            </p>
            <label for="participantID">${participantID}<br></label>
            <input id="participantID" class="form-control" type="text" name="participant-id"
               placeholder="${leaveBlankParticipantID}"/>
            <br/>
            <label for="name">${name}<br></label>
            <input id="name" class="form-control" type="text" name="name" required>
            <br/>
            <label for="surName">${surname}<br></label>
            <input id="surname" class="form-control" type="text" name="surname" required>
            <br/>
            <label for="secondName">${secondName}<br></label>
            <input id="secondName" class="form-control" type="text" name="secondName">
            <br/>
            <br/>

            <label for="position">Choose a Position:</label>
            <select name="position" id="position">
            <c:forEach items="<%=Position.values()%>" var="position">
                <option value ="${position.name()}">${position.toString()}</option>
            </c:forEach>
            </select>
<%--            <label for="secondName">${position}<br></label>--%>
<%--            <input id="position" class="form-control" type="text" name="position">--%>
<%--            <br/>--%>
<%--            <br/>--%>
            <button type="submit" class="btn btn-primary">${submit}</button>
        </form>
    </br>
</div>
</div>
</body>
</html>