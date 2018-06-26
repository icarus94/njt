<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


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
                <form:form>
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
            <form:form>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <input type="text" id="name" name="name" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea id="description" class="form-control" rows="5" name="description" required>
                        </textarea>
                    </div>
                    <div class="form-group">
                        <label for="due_date">Due date:</label>
                        <input type="text" id="due_date" name="due_date" class="form-control due_date">
                    </div>
                    <div class="form-group">
                        <label for="priority">Priority:</label>
                        <select class="form-control" id="priority" name="priority">
                            <option value="1">Low</option>
                            <option value="2">Medium</option>
                            <option value="3">High</option>
                        </select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success" name="parent_id" value="">Create</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
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
            <form:form>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name_edit">Name:</label>
                        <form:input type="text" id="name_edit" path="name" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label for="description_edit">Description:</label>
                        <form:textarea id="description_edit" class="form-control" rows="5" path="description">
                        </form:textarea>
                    </div>
                    <div class="form-group">
                        <label for="due_date_edit">Due date:</label>
                        <form:input type="text" id="due_date_edit" path="due_date" class="form-control due_date" />
                    </div>
                    <div class="form-group">
                        <label for="priority_edit">Priority:</label>
                        <form:select class="form-control" id="priority_edit" path="priority">
                            <option value="1">Low</option>
                            <option value="2">Medium</option>
                            <option value="3">High</option>
                        </form:select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success button-submit-id" name="id" value="">Edit</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </form:form>
        </div>
    </div>
</div>