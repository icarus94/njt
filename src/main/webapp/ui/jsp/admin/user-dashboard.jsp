<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--@elvariable id="userHasTodoList" type="njt.myproject.dax.models.UserHasTodoList"--%>
<%--@elvariable id="userTarget" type="njt.myproject.dax.models.User"--%>
<%--@elvariable id="tasks" type="java.util.List<njt.myproject.dax.models.Task>"--%>
<c:set var="row_number" value="1" scope="page"/>
<jsp:useBean id="helper" class="njt.myproject.dax.helper.Helper"/>
<div class="row">
    <div class="col-xs-12">
        <h3>Mr.${userTarget.name} ${userTarget.surname} Todo Tasks</h3>
    </div>
</div>
<div class="row" id="task-content">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Description</th>
            <th>Due Date</th>
            <th>Task List Name</th>
            <th>Done</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="task" items="${tasks}">
            <tr class="${helper.getRowColor(task)}">
                <th scope="row">${row_number}</th>
                <td>${task.name}</td>
                <td>${task.description}</td>
                <td>${task.dueDate}</td>
                <td>${task.todoList.name}</td>
                <td>
                    <c:choose>
                        <c:when test="${task.done == 1}">
                            <input type="checkbox" class="task-check" title="Check button" checked
                                   onclick="return false;">
                        </c:when>
                        <c:otherwise>
                            <input type="checkbox" class="task-check" title="Check button" onclick="return false;">
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <c:set var="row_number" value="${row_number + 1}" scope="page"/>
        </c:forEach>
        </tbody>
    </table>
</div>