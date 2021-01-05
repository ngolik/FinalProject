<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 29.12.2020
  Time: 00:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.usersPage" var="usersPage"/>
<fmt:message bundle="${locale}" key="locale.ban" var="ban"/>
<fmt:message bundle="${locale}" key="locale.unban" var="unban"/>

<!DOCTYPE html>
<html>
<head>
    <title>${usersPage}</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</head>

<body>
<c:import url="menu.jsp"/>
<div class="container-fluid text-center wrapper">
     <div class="row content">
         <c:import url="leftside.jsp"/>
         <div class="col-sm-8 text-left mainContent">
             <br>
             <table class="table">
                 <c:forEach var="user" items="${requestScope.all_users}">
                     <tr>
                         <td><a href="DispatcherServlet?command=view-user&username=${user.userName}"><c:out value="${user.userName}"/> </a></td>
                         <td>${user.email}</td>
                         <td>${user.role}</td>
                         <td>${user.registrationDate}</td>
                         <c:if test="${sessionScope.get('user').role eq 'admin'}">
                             <td><a href="DispatcherServlet?command=ban-user&userName=${user.userName}">${ban}</a> </td>
                             <td><a href="DispatcherServlet?command=ban-user&userName=${user.userName}">${unban}</a> </td>
                         </c:if>
                     </tr>
                 </c:forEach>
             </table>
         </div>
         <c:import url="rightside.jsp"/>
     </div>
</div>
</body>
</html>
