<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="editUserForm" type="njt.myproject.dax.dto.form.EditUserForm"--%>

<!-- Modal for task edit-->
<div class="modal fade" id="editUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form:form action="/admin-edit-user" method="post" modelAttribute="editUserForm">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="name">Name:</label>
                        <form:input path="name" type="text" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label for="name">Surname:</label>
                        <form:input path="surname" type="text" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <form:input type="text" path="email" class="form-control due_date"/>
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <form:input type="text" path="password" class="form-control due_date" placeholder="Leave blank to remain the same"/>
                    </div>
                    <div class="form-group">
                        <label for="role">Role:</label>
                        <form:select class="form-control" path="role">
                            <option value="0">Regular User</option>
                            <option value="1">Admin</option>
                        </form:select>
                    </div>
                    <div class="form-group">
                        <label for="role">Active:</label>
                        <form:select class="form-control" path="active">
                            <option value="0">Not active</option>
                            <option value="1">Active</option>
                        </form:select>
                        <small class="form-text text-muted">*virtual delete</small>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success ">Edit</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
                <form:hidden path="id"/>
            </form:form>
        </div>
    </div>
</div>