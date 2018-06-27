$(document).ready(function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $(".due_date").datepicker({
        dateFormat: 'yy-mm-dd',
        minDate: 0
    });

    $(".task-check").on('click', function () {
        var id = $(this).data('id');
        if ($(this).is(":checked")) {
            console.log("Checked:" + id);
        } else {
            console.log("Unchecked:" + id);
        }
    });

    $(".editTask").on('click', function () {

        var id = $(this).data('id');
        var name = $(this).data('name');
        var description = $(this).data('description');
        var priority = $(this).data('priority');
        var dueDate = $(this).data('due_date');
        var done = $(this).data('done');

        var modal = $($(this).data('target'));
        $("#button-submit-id", modal).val(id);
        $("#name_edit", modal).val(name);
        $("#description_edit", modal).val(description);
        $("#due_date_edit", modal).val(dueDate);
        $("#priority_edit", modal).val(priority);

        modal.modal('toggle');
    });

    $(".deleteTask").on('click', function () {
        var id = $(this).data('id');
        var modal = $($(this).data('target'));
        $(".button-submit-id", modal).val(id);
        modal.modal('toggle');
    });

    $("input:checkbox").on('click', function () {
        var id = $(this).data('id');
        var checked = null;
        if (this.checked) {
            alert("checked "+id);
            checked = 1;
        } else {
            alert("unchecked "+id);
            checked = 0;
        }
        $.ajax({
            type: "POST",
            url: "/check-task",
            data: {
                id: id,
                checked: checked
            },
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function(msg){
                alert( "Data Saved:  ");
                console.log(msg);
            },
            error: function(XMLHttpRequest, textStatus, errorThrown) {
                alert("some error");
                event.preventDefault();
            }
        });
    });


    $(".edit-task-list").on('click', function () {
        var id = $(this).data('parent-id');
        var name = $(this).data('name');

        var modal = $($(this).data('target'));
        $(".task_list_id", modal).val(id);
        $(".task_list_name", modal).val(name);

        modal.modal('toggle');
    });

    $(".delete-task-list").on('click', function () {
        var id = $(this).data('parent-id');
        console.log(id);
        var modal = $($(this).data('target'));
        console.log($(".task_list_id", modal));
        $(".task_list_id", modal).val(id);
        modal.modal('toggle');
    });
});