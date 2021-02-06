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
<fmt:message bundle="${locale}" key="locale.movies" var="movies"/>
<fmt:message bundle="${locale}" key="locale.title" var="title"/>
<fmt:message bundle="${locale}" key="locale.year" var="year"/>
<fmt:message bundle="${locale}" key="locale.gross" var="gross"/>
<fmt:message bundle="${locale}" key="locale.budget" var="budget"/>
<fmt:message bundle="${locale}" key="locale.runtime" var="runtime"/>
<fmt:message bundle="${locale}" key="locale.next" var="next"/>
<fmt:message bundle="${locale}" key="locale.previous" var="previous"/>
<fmt:message bundle="${locale}" key="locale.yourRating" var="yourRating"/>
<fmt:message bundle="${locale}" key="locale.ratingVotes" var="ratingVotes"/>
<fmt:message bundle="${locale}" key="locale.moviesByGenre" var="moviesByGenre"/>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <title>MOVIES</title>
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
            <h1>${movies}</h1>

            <div class="selectboxes">
                <div class="sortAwaits" style="width: 391px; float: left;margin: 0">
                    <span>${moviesByGenre}</span>
                        <select onchange="MakeSort(this);">
                            <option value="DispatcherServlet?command=all-movies">-</option>
                            <option selected="" value="DispatcherServlet?command=all-movies">${all}</option>
                            <option value="DispatcherServlet?command=show-movies-by-genre&genre=comedy">Comedy</option>
                            <option value="DispatcherServlet?command=show-movies-by-genre&genre=action">Action</option>
                            <option value="DispatcherServlet?command=show-movies-by-genre&genre=documentary">Documentary</option>
                            <option value="DispatcherServlet?command=show-movies-by-genre&genre=drama">Drama</option>
                            <option value="DispatcherServlet?command=show-movies-by-genre&genre=family">Family</option>
                            <option value="DispatcherServlet?command=show-movies-by-genre&genre=horror">Horror</option>
                            <option value="DispatcherServlet?command=show-movies-by-genre&genre=adventure">Adventure</option>
                            <option value="DispatcherServlet?command=show-movies-by-genre&genre=thriller">Thriller</option>
                            <option value="DispatcherServlet?command=show-movies-by-genre&genre=musical">Musical</option>
                            <option value="DispatcherServlet?command=show-movies-by-genre&genre=cartoon">Cartoon</option>
                            <option value="DispatcherServlet?command=show-movies-by-genre&genre=detective">Detective</option>
                            <option value="DispatcherServlet?command=show-movies-by-genre&genre=western">Western</option>
                        </select>
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
                <th>${title}</th>
                <th>${year}</th>
                <th>${runtime}</th>
                <th>${budget}</th>
                <th>${gross}</th>
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

    <style type="text/css">
        div.pagination {
            margin-top: 10px;
            text-align: center;
        }
        .pagination a {
            color: #3b5998;
            text-decoration: none;
            border: 1px #c2d1df solid;
            padding: 2px 5px;
            margin-right: 5px;
        }
        .pagination a:hover {
            color: #3b5998;
            text-decoration: none;
            border: 1px #3b5998 solid;
        }
        a.current {
            background: #c2d6ed;
        }
    </style>
    <div class="pagination">
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
                <a href="DispatcherServlet?command=${param.command}&page=${i}&genre=${param.genre}">${i}</a>
            </li>
            </c:otherwise>
            </c:choose>
            </c:forEach>
            </c:if>
        <%--For displaying Next link --%>
        <c:if test="${requestScope.currentPage lt requestScope.noOfPages}">
            <li>
                <a href="DispatcherServlet?command=${param.command}&page=${requestScope.currentPage + 1}&genre=${param.genre}">${next}</a>
            </li>
        </c:if>

        </ul>
 </div>
    <c:import url="../menu/slider.jsp"/>
    </div>
</div>
<c:import url="../menu/footer.jsp"/>
</body>
</html>
