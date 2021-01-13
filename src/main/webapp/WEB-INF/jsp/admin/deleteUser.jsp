<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 14.01.2021
  Time: 00:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.deleteUser" var="deleteUser"/>
<fmt:message bundle="${locale}" key="locale.username" var="username"/>
<fmt:message bundle="${locale}" key="locale.submit" var="submit"/>
<fmt:message bundle="${locale}" key="locale.cancel" var="cancel"/>
<div id="add-participant" class="modal fade" tabindex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">$times;</button>
                <h4 class="modal-title" id="myModalLabel">${deleteUser}</h4>
            </div>
            <div class="modal-body text-center">
                <form class="form-horizontal" method="post" action="DispatcherServlet">
                    <input type="hidden" name="command" value="delete-user"/>
                    <div class="form-group">
                        <label for="inputUserName" class="col-sm-3 control-label">${username}</label>
                        <div class="col-sm-7">
                            <input type="number" class="form-control" id="inputUserName" placeholder="${username}"
                                   name="username" required>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-3 col-sm-7">
                            <button type="submit" class="btn btn-primary">${submit}</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">${cancel}</button>
            </div>
        </div>
    </div>
</div>
