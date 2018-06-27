<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row" id="task-content">
    <div class="panel-group" id="accordion">
        <!-- closable panels -->

        <%--second one--%>
        <c:forEach var="userTaskItem" items="${userTaskLists}">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="panel-title">
                        <div class="row">
                            <div class="col-xs-12 col-md-8" style="text-align: left">
                                <a data-toggle="collapse"
                                   href="#collapse${userTaskItem.todoList.id}">${userTaskItem.todoList.name}</a>
                            </div>
                            <div class="col-xs-12 col-md-4" style="text-align: right">
                                <button type="button" class="btn btn-warning btn-cursor-pointer edit-task-list"
                                        data-parent-id="${userTaskItem.todoList.id}"
                                        data-name="${userTaskItem.todoList.name}"
                                        data-target="#editTaskCategory">Edit task list &nbsp;
                                    <i class="fas fa-pen-square"></i>
                                </button>
                                <button type="button" class="btn btn-danger btn-cursor-pointer delete-task-list"
                                        data-parent-id="${userTaskItem.todoList.id}"
                                        data-target="#deleteTaskCategory">Delete task list &nbsp;
                                    <i class="fas fa-trash"></i>
                                </button>
                                (${fn:length(userTaskItem.todoList.tasks)})
                            </div>
                        </div>
                    </h4>
                </div>
                <div id="collapse${userTaskItem.todoList.id}" class="panel-collapse collapse">
                        <%--button--%>
                    <div class="row add-task-in-category">
                        <div class="col-xs-12 col-md-4 col-md-offset-4">
                            <button type="button" class="btn btn-info btn-cursor-pointer" data-toggle="modal"
                                    data-parent-id="${userTaskItem.todoList.id}"
                                    data-target="#createTask">Add new task &nbsp;
                                <i class="fas fa-plus"></i>
                            </button>
                        </div>
                    </div>

                    <c:forEach var="task" items="${userTaskItem.todoList.tasks}">

                        <jsp:useBean id="helper" class="njt.myproject.dax.helper.Helper"/>

                        <div class="panel ${helper.getPanelColor(task)} task-item-panel">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-md-9 col-xs-12" style="text-align: left">
                                            ${task.name}
                                    </div>
                                    <div class="col-md-3 col-xs-12" style="text-align: right">
                                        <b>Due date: ${task.dueDate}</b>
                                    </div>
                                </div>
                            </div>

                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-12 col-md-10">
                                            ${task.description}
                                    </div>
                                    <div class="col-xs-12 col-md-2 task-item-options">
                                        <c:choose>
                                            <c:when test="${task.done == 1}">
                                                <input type="checkbox" class="task-check" value="" data-id="${task.id}"
                                                       checked>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="checkbox" class="task-check" value="" data-id="${task.id}">
                                            </c:otherwise>
                                        </c:choose>
                                        <button type="button" class="btn btn-warning editTask"
                                                data-target="#editTask" data-id="${task.id}"
                                                data-description="${task.description}"
                                                data-name="${task.name}" data-done="${task.done}"
                                                data-due_date="${task.dueDate}" data-priority="${task.priority}">
                                            <i class="fas fa-edit"></i>
                                        </button>
                                        <button type="button" class="btn btn-danger deleteTask"
                                                data-target="#deleteTask" data-id="${task.id}">
                                            <i class="fas fa-trash-alt"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
            </div>
        </c:forEach>
        <!-- closable panels end div-->
    </div>
</div>

<jsp:include page="task-modals.jsp"/>

<%--<div class="panel panel-default">--%>
<%--<div class="panel-heading">--%>
<%--<h4 class="panel-title">--%>
<%--<a data-toggle="collapse" href="#collapse1">Task category 1</a>--%>
<%--</h4>--%>
<%--</div>--%>
<%--<div id="collapse1" class="panel-collapse collapse in">--%>
<%--<div class="row add-task-in-category">--%>
<%--<div class="col-xs-12 col-md-4 col-md-offset-4">--%>
<%--<button type="button" class="btn btn-info btn-cursor-pointer" data-toggle="modal"--%>
<%--data-parent-id=""--%>
<%--data-target="#createTask">Add new task &nbsp;--%>
<%--<i class="fas fa-plus"></i>--%>
<%--</button>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="panel panel-default task-item-panel">--%>
<%--<div class="panel-heading">--%>
<%--Second Task heading--%>
<%--</div>--%>
<%--<div class="panel-body">--%>
<%--<div class="row">--%>
<%--<div class="col-xs-9 col-md-10">--%>
<%--First task--%>
<%--</div>--%>
<%--<div class="col-xs-3 col-md-2 task-item-options">--%>
<%--<input type="checkbox" class="task-check" value="" data-id="id">--%>
<%--<button type="button" class="btn btn-warning editTask"--%>
<%--data-target="#editTask" data-id="id">--%>
<%--<i class="fas fa-edit"></i>--%>
<%--</button>--%>
<%--<button type="button" class="btn btn-danger deleteTask"--%>
<%--data-target="#deleteTask" data-id="id">--%>
<%--<i class="fas fa-trash-alt"></i>--%>
<%--</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>


<%--<div class="panel panel-default task-item-panel">--%>
<%--<div class="panel-heading">--%>
<%--Second Task heading--%>
<%--</div>--%>
<%--<div class="panel-body">--%>

<%--<div class="row">--%>
<%--<div class="col-xs-9 col-md-10">--%>
<%--Second task--%>
<%--</div>--%>
<%--<div class="col-xs-3 col-md-2 task-item-options">--%>
<%--<input type="checkbox" class="task-check checkbox-primary" data-id="id">--%>
<%--<button type="button" class="btn btn-warning">--%>
<%--<i class="fas fa-edit"></i>--%>
<%--</button>--%>
<%--<button type="button" class="btn btn-danger">--%>
<%--<i class="fas fa-trash-alt"></i>--%>
<%--</button>--%>
<%--</div>--%>
<%--</div>--%>

<%--</div>--%>
<%--</div>--%>


<%--</div>--%>
<%--</div>--%>