

$(document).ready(function() {


    $("#dob").datepicker({
        dateFormat: "dd/mm/yy",
        maxDate: new Date(),
    });
    $("#dob_edit").datepicker({
        dateFormat: "dd/mm/yy",
        maxDate: new Date(),
    });
    $('#add_user').click(function() {
        $('#error_add').html("");
        var firstname = $('#first_name').val();
        if (firstname.length < 3) {
            $('#error_add').append('<div>First name should be longer than 3 digits</div>');
        }
        var lastname = $('#last_name').val();
        if (lastname.length < 3) {
            $('#error_add').append('<div>Last name should be longer than 3 digits</div>');
        }
        var dob = $('#dob').val();
        if (dob.lenght < 0) {
            $('#error_add').append('<div>DOB not given</div>');
        }
        var username = $('#username').val();
        if (username.length < 3) {
            $('#error_add').append('<div>Username should be longer than 3 digits</div>');
        }
        var password = $('#password').val();
        if (password.length < 6) {
            $('#error_add').append('<div>Password should be longer than 6 digits</div>');
        }
        if ($('#error_add').children().length > 0) {
            return;
        }
        $.post('customer/add', {
            first_name: firstname,
            last_name: lastname,
            dob: dob,
            username: username,
            password: password
        }, function(response) {
            $('#addUser').modal('hide');
            $('#first_name').val("")
            $('#last_name').val("")
            $('#dob').val("");
            $('#username').val("");
            $('#password').val("");
            getAllUsers();
        })
    })
    $('#edit_user').click(function() {
        $('#error_edit').html("");
        var firstname = $('#first_name_edit').val();
        if (firstname.length < 3) {
            $('#error_edit').append('<div>First name should be longer than 3 digits</div>');
        }
        var lastname = $('#last_name_edit').val();
        if (lastname.length < 3) {
            $('#error_edit').append('<div>Last name should be longer than 3 digits</div>');
        }
        var dob = $('#dob_edit').val();
        if (dob.lenght < 0) {
            $('#error_edit').append('<div>DOB not given</div>');
        }
        var username = $('#username_edit').val();
        if (username.length < 3) {
            $('#error_edit').append('<div>Username should be longer than 3 digits</div>');
        }
        var password = $('#password_edit').val();
        if (password.length < 6) {
            $('#error_edit').append('<div>Password should be longer than 6 digits</div>');
        }
        if ($('#error_edit').children().length > 0) {
            return;
        }
        var uid = $('#uid').val();
        $.post('customer/edit', {
            first_name: firstname,
            last_name: lastname,
            dob: dob,
            username: username,
            password: password,
            uid: uid
        }, function(response) {
            $('#editUser').modal('hide');
            $('#first_name_edit').val("")
            $('#last_name_edit').val("")
            $('#dob_edit').val("");
            $('#username_edit').val("");
            $('#password_edit').val("");
            getAllUsers();
        })
    })
    getAllUsers();
    $(document).on('click','.delete_user' ,function() {
        var uid = $(this).parent().parent().find('.uid').html();
        var data = {"uid": uid}
        $.post('customer/delete', {userToDelete: uid}, function(response) {
            getAllUsers();
        })
    })

    $(document).on('click','.edit_user' ,function() {
        $('#error_edit').html("");

        var uid = $(this).parent().parent().find('.uid').html();
        var data = {"uid": uid}
        $.get('customer/getcustomer', {userToEdit: uid}, function(response) {
            var response = JSON.parse(response);
            $('#first_name_edit').val(response.firstname);
            $('#last_name_edit').val(response.lastname);
            $('#password_edit').val(response.password);
            $('#dob_edit').val(response.dob);
            $('#username_edit').val(response.username);
            $('#uid').val(response.uid);
            $('#uid').prop('readonly', true);
        })
    })

})

function getAllUsers() {
    $.get('customer/getall', {}, function(response) {
        $('#users').empty();
        var deleteButton = "<button class='delete_user btn btn-danger'>Delete</button>";
        var editButton = "<button class='edit_user btn btn-primary' data-toggle='modal' data-target='#editUser'>Edit</button>";
        var json = JSON.parse(response);
        $.each(json, function(key, value) {
            $('#users').append("<tr><td>"+ value.firstName + "</td><td>" + value.lastName + "</td><td class='uid'>" + value.id + "</td>" +
                    "<td>" + value.dob + "</td>"+
                    "<td>" + value.username + "</td>" +
                    "<td>" + value.password + "</td>" +
                "<td>" + deleteButton +
                "</td><td>" + editButton + "</td></tr>")
        })
    })
}
