

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
        var firstname = $('#first_name').val();
        var lastname = $('#last_name').val();
        var dob = $('#dob').val();
        var username = $('#username').val();
        var password = $('#password').val();
        $.post('addcustomer.htm', {
            first_name: firstname,
            last_name: lastname,
            dob: dob,
            username: username,
            password: password
        }, function(response) {
            console.log(response);
            $('#first_name').val("")
            $('#last_name').val("")
            $('#dob').val("");
            $('#username').val("");
            $('#password').val("");
            getAllUsers();
        })
    })
    $('#edit_user').click(function() {
        var firstname = $('#first_name_edit').val();
        var lastname = $('#last_name_edit').val();
        var dob = $('#dob_edit').val();
        var username = $('#username_edit').val();
        var password = $('#password_edit').val();
        var uid = $('#uid').val();
        $.post('/editcustomer.htm', {
            first_name: firstname,
            last_name: lastname,
            dob: dob,
            username: username,
            password: password,
            uid: uid
        }, function(response) {
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
        console.log($(this).parent().parent().find('.uid').html());
        var uid = $(this).parent().parent().find('.uid').html();
        var data = {"uid": uid}
        $.post('deleteuser.htm', {userToDelete: uid}, function(response) {
            getAllUsers();
        })
    })

    $(document).on('click','.edit_user' ,function() {
        var uid = $(this).parent().parent().find('.uid').html();
        var data = {"uid": uid}
        $.get('getuserdata.htm', {userToEdit: uid}, function(response) {
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
    $.get('getallusers.htm', {}, function(response) {
        $('#users').empty();
        var deleteButton = "<button class='delete_user btn btn-danger'>Delete</button>";
        var editButton = "<button class='edit_user btn btn-primary' data-toggle='modal' data-target='#myModal'>Edit</button>";
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