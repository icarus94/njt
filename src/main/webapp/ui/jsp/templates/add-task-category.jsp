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
            <form:form action="/create-new-category-task" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label>Task category name</label>
                        <input class="form-control" id="task_name"
                               name="task_name"
                               placeholder="examples: Job tasks, Home tasks, Event tasks" required>
                        <small id="emailHelp" class="form-text text-muted">Name your categories with unique names
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