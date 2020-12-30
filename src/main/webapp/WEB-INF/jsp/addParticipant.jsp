<%--
  Created by IntelliJ IDEA.
  User: Nikitos
  Date: 26.12.2020
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="locale" var="locale"/>
<fmt:message bundle="${locale}" key="locale.addParticipantForMovie" var="addParticipantForMovie"/>
<fmt:message bundle="${locale}" key="locale.participantID" var="participantID"/>
<fmt:message bundle="${locale}" key="locale.submit" var="submit"/>
<fmt:message bundle="${locale}" key="locale.cancel" var="cancel"/>
<div id="add-participant" class="modal fade" tabindex="1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">$times;</button>
            <h4 class="modal-title" id="myModalLabel">${addParticipantForMovie}</h4>
        </div>
        <div class="modal-body text-center">
            <form class="form-horizontal" method="post" action="DispatcherServlet">
                <input type="hidden" name="command" value="add-participant-for-movie"/>
                <input type="hidden" name="id" value="movie.id"/>
                <div class="form-group">
                    <label for="inputUserName" class="col-sm-3 control-label">${participantID}</label>
                    <div class="col-sm-7">
                        <input type="number" class="form-control" id="inputUserName" placeholder="${participantID}"
                        name="participant-id" required>
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
            <button type="button" class="btn btn-default" data-dismiss="modal">${cancel}</button>
        </div>
    </div>
</div>
</div>
