<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 29.12.2020
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.all" var="all"/>
<fmt:message bundle="${locale}" key="locale.movie" var="movie"/>
<fmt:message bundle="${locale}" key="locale.next" var="next"/>
<fmt:message bundle="${locale}" key="locale.previous" var="previous"/>
<fmt:message bundle="${locale}" key="locale.yourRating" var="yourRating"/>
<fmt:message bundle="${locale}" key="locale.ratingVotes" var="ratingVotes"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body onload="active()">

<c:import url="../menu/menu.jsp"/>

<script language="JavaScript">
    function active() {
        document.getElementById("movies-page").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">

    <br class="row content">
        <c:import url="../menu/adminMenu.jsp"/>
        <div class="col-sm-8 text-left mainContent">
            <h1>MOVIES</h1>

            <div class="selectboxes">
                <div class="sortAwaits" style="width: 391px; float: left;margin: 0">
                    <label>
                        <select onchange="MakeSort(this);">
                            <option value="DispatcherServlet?command=all-movies">-</option>
                            <option selected="" value="DispatcherServlet?command=all-movies">${all}</option>
                        </select>
                    </label>
                </div>
                <div class="clear"></div>
            </div>
            <div class="clear"></div>
        </div>
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
                <th>${movie}</th>
                <th>Year of issue</th>
                <th>Runtime</th>
                <th>Budget</th>
                <th>Gross</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="movie" items="${requestScope.all_movies}">
                <tr>
                    <td>
                        <a href="DispatcherServlet?command=movie-by-id&id=${movie.id}">
                            <c:out value="${movie.title}"/></a></td>
                    <td>
                        <a href="DispatcherServlet?command=movie-by-id&id=${movie.id}">
                            <c:out value="${movie.year}"/>
                        </a>
                    </td>
                    <td>
                        <a href="DispatcherServlet?command=movie-by-id&id=${movie.id}">
                            <c:out value="${movie.runtime}"/>
                    </td>
                    <td>
                        <a href="DispatcherServlet?command=movie-by-id&id=${movie.id}">
                            <c:out value="${movie.budget}"/>
                        </a>
                    </td>
                    <td>
                        <a href="DispatcherServlet?command=movie-by-id&id=${movie.id}">
                            <c:out value="${movie.gross}"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>

    <%--For displaying Previous link except for the 1st page --%>
    <ul class="pagination">
        <c:if test="${requestScope.currentPage > 1}">
        <li>
            <a href="DispatcherServlet?command=${param.command}&page=${requestScope.currentPage - 1}">${previous}</a>
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
                <a href="DispatcherServlet?command=${param.command}&page=${i}">${i}</a>
            </li>
            </c:otherwise>
            </c:choose>
            </c:forEach>
            </c:if>

        </ul>
    <c:import url="../menu/rightside.jsp"/>
    </div>
</div>
<c:import url="../menu/footer.jsp"/>
</body>
</html>
