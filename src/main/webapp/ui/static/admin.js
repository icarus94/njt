$(document).ready(function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $(".edit-user-btn").on('click', function () {

        var id = $(this).data('id');
        var user_name = $(this).data('user_name');
        var user_surname = $(this).data('user_surname');
        var user_email = $(this).data('user_email');
        var user_active = $(this).data('user_active');
        var user_role = $(this).data('user_role');

        var modal = $($(this).data('target'));
        $("#name", modal).val(user_name);
        $("#surname", modal).val(user_surname);
        $("#email", modal).val(user_email);
        $("#role", modal).val(user_role);
        $("#active", modal).val(user_active);
        $("#id", modal).val(id);

        modal.modal('toggle');
    });
});