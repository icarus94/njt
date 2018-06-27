<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="row" id="add-task-category">
    <div class="col-xs-12 col-md-4 col-md-offset-4">
        <button type="button" class="btn btn-success btn-cursor-pointer" data-toggle="modal"
                data-target="#addNewTaskCategory">Add new task category &nbsp;
            <i class="fas fa-plus"></i>
        </button>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="addNewTaskCategory" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close btn-cursor-pointer" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form:form action="/add-new-task-list" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label>Task category name</label>
                        <input class="form-control" id="task_name"
                               name="task_name"
                               placeholder="examples: Job tasks, Home tasks, Event tasks" required>
                        <small class="form-text text-muted">Name your categories with unique names
                        </small>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary btn-cursor-pointer" data-dismiss="modal">Close
                    </button>
                    <input type="submit" class="btn btn-primary btn-cursor-pointer" value="Create">
                </div>
            </form:form>
        </div>
    </div>
</div>


<!-- Modal -->
<div class="modal fade" id="editTaskCategory" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close btn-cursor-pointer" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form:form action="/edit-task-list" method="post" modelAttribute="editTaskListForm">
                <div class="modal-body">
                    <div class="form-group">
                        <label>Task category name</label>
                        <form:input path="name" cssClass="form-control task_list_name"/>
                        <form:hidden path="id" cssClass="task_list_id"/>
                        <small id="emailHelp" class="form-text text-muted">Name your categories with unique names
                        </small>
                    </div>
                </div>

                <div class="modal-footer">
                    <input type="submit" class="btn btn-primary btn-cursor-pointer" value="Edit">
                    <button type="button" class="btn btn-secondary btn-cursor-pointer" data-dismiss="modal">Close
                    </button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<!-- Modal for task list delete-->
<div class="modal fade" id="deleteTaskCategory" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this task list?
            </div>
            <div class="modal-footer">
                <form:form action="/delete-task-list" method="post">
                    <input type="text" name="id" class="task_list_id" hidden>
                    <input type="submit" class="btn btn-danger btn-cursor-pointer" value="Delete">
                    <button type="button" class="btn btn-secondary btn-cursor-pointer" data-dismiss="modal">Close</button>
                </form:form>
            </div>
        </div>
    </div>
</div>