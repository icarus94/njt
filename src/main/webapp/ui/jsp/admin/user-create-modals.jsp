<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="addUserForm" type="njt.myproject.dax.dto.form.AddUserForm"--%>

<!-- Modal for task create-->
<div class="modal fade" id="createUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form:form action="/admin-create-user" method="post" modelAttribute="addUserForm">
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
                        <form:input type="text" path="password" class="form-control due_date"/>
                    </div>
                    <div class="form-group">
                        <label for="role">Role:</label>
                        <form:select class="form-control" path="role">
                            <option value="0">Regular User</option>
                            <option value="1">Admin</option>
                        </form:select>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">Create</button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </form:form>
        </div>
    </div>
</div>

