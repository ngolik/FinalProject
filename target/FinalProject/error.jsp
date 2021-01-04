<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.erorrPage" var="erorrPage"/>

<!DOCTYPE html>
<html>
<head>
    <title>${erorrPage}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <H1>ErrorPageTest</H1>
</head>
<body>

<c:import url="WEB-INF/jsp/menu.jsp"/>

<div class="container-fluid text-center wrapper">
    <div class="row content">
        <c:import url="WEB-INF/jsp/leftside.jsp"/>
        <div class="col-sm-8 text-left mainContent">
            <c:if test="${requestScope.get('errorMessage') != null}">
                <h3 class="red"><c:out value="${requestScope.get('errorMessage')}"/></h3>
                <c:remove var="errorMessage" scope="request"/>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
