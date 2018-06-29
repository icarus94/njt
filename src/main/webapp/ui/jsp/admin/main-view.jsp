<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="row_number" value="1" scope="page"/>
<%--@elvariable id="users" type="java.util.List<njt.myproject.dax.models.User>"--%>
<c:set var="row_label" value="" scope="page"/>
<div class="row">
    <div class="col-xs-12" style="text-align: center">
        <button type="button"
                class="btn btn-primary btn-cursor-pointer" data-toggle="modal"
                data-target="#createUser">Add new user &nbsp;
            <i class="fas fa-plus"></i>
        </button>
    </div>
</div>
<div class="row admin-main-view">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Surname</th>
            <th>Email</th>
            <th>Role</th>
            <th>Options</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user_from_list" items="${users}">
            <c:choose>
                <c:when test="${user_from_list.active == 1}">
                    <c:set var="row_label" value="" scope="page"/>
                </c:when>
                <c:otherwise>
                    <c:set var="row_label" value="danger" scope="page"/>
                </c:otherwise>
            </c:choose>
            <tr class="${row_label}">
                <th scope="row">${row_number}</th>
                <td>${user_from_list.name}</td>
                <td>${user_from_list.surname}</td>
                <td>${user_from_list.email}</td>
                <td>
                    <c:choose>
                        <c:when test="${user_from_list.role == 1}">
                            Admin
                        </c:when>
                        <c:otherwise>
                            Regular User
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a type="button" class="btn btn-info btn-cursor-pointer"
                       href="/user-dashboard/${user_from_list.id}">
                        <i class="fas fa-eye"></i>
                    </a>
                    <button type="button"
                            class="btn btn-warning btn-cursor-pointer edit-user-btn"
                            data-id="${user_from_list.id}"
                            data-user_name="${user_from_list.name}"
                            data-user_surname="${user_from_list.surname}"
                            data-user_email="${user_from_list.email}"
                            data-user_active="${user_from_list.active}"
                            data-user_role="${user_from_list.role}"
                            data-target="#editUser">
                        <i class="fas fa-pen-square"></i>
                    </button>
                </td>
            </tr>
            <c:set var="row_number" value="${row_number + 1}" scope="page"/>
        </c:forEach>
        </tbody>
    </table>
</div>