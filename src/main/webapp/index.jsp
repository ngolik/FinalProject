<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set scope="session" var="previousQuery" value="index.jsp"/>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.indexTitle" var="indexTitle"/>
<fmt:message bundle="${locale}" key="locale.searchTitle" var="searchTitle"/>
<fmt:message bundle="${locale}" key="locale.searchLabel" var="searchLabel"/>
<fmt:message bundle="${locale}" key="locale.search" var="search"/>
<fmt:message bundle="${locale}" key="locale.movie" var="movie"/>

<!DOCTYPE html>
<html>
<head>
    <title>${indexTitle}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style7.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</head>
<body onload="active()">


<c:import url="WEB-INF/jsp/menu/menu.jsp"/>

<script language="javascript">
    function active() {
        document.getElementById("index-page").className = "active";
    }
</script>
<div class="container-fluid text-center wrapper">
    <div class="row content">

        <c:import url="WEB-INF/jsp/menu/adminMenu.jsp"/>

        <div class="col-sm-8 text-left mainContent">
            <br>
            <form method="get" action="DispatcherServlet" class="form-horizontal">
                <input type="hidden" name="command" value="find-movie-by-title"/>
                <div class="form-group">
                    <label for="search" class="col-sm-2 control-label">${searchLabel}</label>

                    <div class="col-sm-8">
                        <input id="search" title="${searchTitle}" type="text" class="form-control"
                               placeholder="${searchTitle}"
                               name="movieTitle"/>
                        <br/>
                    </div>
                    <div class="col-sm-2">
                        <button type="submit" class="btn btn-success">${search}</button>
                    </div>
                </div>

            </form>
            <hr>
            <div id="slider1_container"
                 style="visibility: hidden; position: relative; margin: 0 auto; width: 1000px; height: 700px; overflow: hidden;">

                <!-- Loading Screen -->
                <div u="loading" style="position: absolute; top: 0px; left: 0px;">
                    <div style="filter: alpha(opacity=70); opacity:0.7; position: absolute; display: block;
                background-color: #a77373; top: 0px; left: 0px;width: 100%; height:100%;">
                    </div>
                    <div style="position: absolute; display: block;
                top: 0px; left: 0px;width: 100%;height:100%;">
                    </div>
                </div>

                <!-- Slides Container -->
                <div u="slides" style="position: absolute; left: 0px; top: 0px; width: 1000px; height: 700px;
            overflow: hidden;">
                    <div>
                        <a href="DispatcherServlet?command=movie-by-id&id=5"><img  data-u="image" src="${pageContext.request.contextPath}/images/main/6.jpg" /></a>
                    </div>
                    <div>
                        <a href="DispatcherServlet?command=movie-by-id&id=5"><img  data-u="image" src="${pageContext.request.contextPath}/images/main/7.jpg" /></a>
                    </div>
                    <div>
                        <a href="DispatcherServlet?command=movie-by-id&id=5"><img  data-u="image" src="${pageContext.request.contextPath}/images/main/8.jpg" /></a>
                    </div>

                </div>
                <div u="navigator" class="jssorb05" style="bottom: 16px; right: 6px;">
                    <div u="prototype"></div>
                </div>
                <span u="arrowleft" class="jssora11l" style="top: 123px; left: 8px;">
            </span>
                <span u="arrowright" class="jssora11r" style="top: 123px; right: 8px;">
            </span>
                <a style="display: none" href="http://www.jssor.com">Bootstrap Carousel</a>
            </div>
            <hr>
        </div>
    </div>
    <c:import url="WEB-INF/jsp/menu/footer.jsp"/>
</div>
</body>
</body>
</html>


