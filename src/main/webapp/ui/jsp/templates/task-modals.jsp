<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="addTaskForm" type="njt.myproject.dax.dto.form.AddTaskForm"--%>
<%--@elvariable id="editTaskForm" type="njt.myproject.dax.dto.form.EditTaskForm"--%>

<!-- Modal for task delete-->
<div class="modal fade" id="deleteTask" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this task?
            </div>
            <div class="modal-footer">
                <form:form action="/delete-task" method="post">
                    <button type="submit" class="btn btn-danger button-submit-id" name="id" value="">Delete</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </form:form>
            </div>
        </div>
    </div>
</div>


<!-- Modal for task create-->
<div class="modal fade" id="createTask" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form:form action="/add-task" method="post" modelAttribute="addTaskForm">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <form:input path="name_n" type="text" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <form:textarea class="form-control" rows="5" path="description_n" required="required"/>
                    </div>
                    <div class="form-group">
                        <label for="dueDate_n">Due date:</label>
                        <form:input type="text" path="dueDate_n" class="form-control due_date"/>
                    </div>
                    <div class="form-group">
                        <label for="priority">Priority:</label>
                        <form:select class="form-control" path="priority_n">
                            <option value="1">Low</option>
                            <option value="2">Medium</option>
                            <option value="3">High</option>
                        </form:select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success" value="">Create</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
                <form:hidden path="parent_id_n"/>
            </form:form>
        </div>
    </div>
</div>


<!-- Modal for task edit-->
<div class="modal fade" id="editTask" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form:form action="/edit-task" method="post" modelAttribute="editTaskForm">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name_edit">Name:</label>
                        <form:input type="text" id="name_edit" path="name" class="form-control" required="required"/>
                    </div>
                    <div class="form-group">
                        <label for="description_edit">Description:</label>
                        <form:textarea id="description_edit" class="form-control" rows="5" path="description"
                                       required="required"/>
                    </div>
                    <div class="form-group">
                        <label for="due_date_edit">Due date:</label>
                        <form:input type="text" id="due_date_edit" path="dueDate" class="form-control due_date"
                                    required="required"/>
                    </div>
                    <div class="form-group">
                        <label for="priority_edit">Priority:</label>
                        <form:select class="form-control" id="priority_edit" path="priority">
                            <option value="1">Low</option>
                            <option value="2">Medium</option>
                            <option value="3">High</option>
                        </form:select>
                        <form:hidden id="button-submit-id" path="id" cssClass="form-control"/>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success ">Edit</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </form:form>
        </div>
    </div>
</div>