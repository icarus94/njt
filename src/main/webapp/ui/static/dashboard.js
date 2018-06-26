$(document).ready(function () {

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
        console.log(dueDate);
        var modal = $($(this).data('target'));
        $("#button-submit-id", modal).val(id);
        $("#name_edit",modal).val(name);
        $("#description_edit",modal).val(description);
        $("#due_date_edit",modal).val(dueDate);
        $("#priority_edit",modal).val(priority);

        modal.modal('toggle');
    });

    $(".deleteTask").on('click', function () {
        var id = $(this).data('id');
        var modal = $($(this).data('target'));
        $(".button-submit-id", modal).val(id);
        modal.modal('toggle');
    });
});