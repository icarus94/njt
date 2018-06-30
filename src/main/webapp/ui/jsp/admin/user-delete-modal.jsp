<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="modal fade" id="deleteUser" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are you sure you want to delete this user<b id="user_name"></b>?
            </div>
            <div class="modal-footer">
                <form:form action="/admin-delete-user" method="post">
                    <input type="text" name="id" id="user_id" hidden>
                    <input type="submit" class="btn btn-danger btn-cursor-pointer" value="Delete">
                    <button type="button" class="btn btn-secondary btn-cursor-pointer" data-dismiss="modal">Close
                    </button>
                </form:form>
            </div>
        </div>
    </div>
</div>