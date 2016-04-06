
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="css/styles.css">
      <link href='https://fonts.googleapis.com/css?family=Montserrat' rel='stylesheet' type='text/css'>
    <script src="//code.jquery.com/jquery-1.12.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"   integrity="sha256-xNjb53/rY+WmG+4L6tTl9m6PpqknWZvRt0rO1SRnJzw="   crossorigin="anonymous"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="js/mainScript.js"></script>
    <title>Profit</title>
  </head>
  <body>
  <table class="table">
    <tr>
      <th>First name</th>
      <th>Last name</th>
      <th>UID</th>
      <th>Date of Birth</th>
      <th>Username</th>
      <th>Password</th>
    </tr>
    <tbody id="users">
    </tbody>
  </table>

  <div id="addUserDiv">
  <button class='add_user btn btn-primary' id="toggle_add_user" data-toggle='modal' data-target='#addUser'>Add User</button>
  </div>
  </body>
  <div class="modal fade" id="editUser" role="dialog">
    <div class="modal-dialog">

      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Edit User</h4>
        </div>
        <div class="modal-body">
            <div class="form-group">
                <div class="error" id="error_edit"></div>
            </div>
            <div class="form-group">
              <label for="uid">UID of the given user</label>
              <input type="text" class="form-control disabled" id="uid" name="uid" placeholder="UID"  required>
            </div>
            <div class="form-group">
              <label for="first_name_edit">First name<span class="required"> *</span></label>
              <input type="text" class="form-control" id="first_name_edit" name="first_name_edit" placeholder="First name" required>
            </div>
            <div class="form-group">
              <label for="last_name_edit">Last name<span class="required"> *</span></label>
              <input type="text" class="form-control" id="last_name_edit" name="last_name_edit" placeholder="Last name" required>
            </div>
            <div class="form-group">
              <label for="last_name_edit">Date of birth<span class="required"> *</span></label>
              <input type="text" class="form-control" id="dob_edit" name="dob_edit" placeholder="Date of birth" required>
            </div>
            <div class="form-group">
              <label for="last_name_edit">Username<span class="required"> *</span></label>
              <input type="text" class="form-control" id="username_edit" name="username_edit" placeholder="Username" required>
            </div>
            <div class="form-group">
              <label for="last_name_edit">Password<span class="required"> *</span></label>
              <input type="password" class="form-control" id="password_edit" name="password_edit" placeholder="Password" required>
            </div>
            <button value="Edit User" id="edit_user" class="btn btn-success">Edit user</button>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>

    </div>
  </div>
  <div class="modal fade" id="addUser" role="dialog">
    <div class="modal-dialog">

      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add user</h4>
        </div>
        <div class="modal-body">
            <div class="form-group">
                <div class="error" id="error_add"></div>
            </div>
            <div class="form-group">
              <label for="first_name_edit">First name<span class="required"> *</span></label>
              <input type="text" class="form-control" id="first_name" name="first_name" placeholder="First name" required>
            </div>
            <div class="form-group">
              <label for="last_name_edit">Last name<span class="required"> *</span></label>
              <input type="text" class="form-control" id="last_name" name="last_name" placeholder="Last name" required>
            </div>
            <div class="form-group">
              <label for="last_name_edit">Date of birth<span class="required"> *</span></label>
              <input type="text" class="form-control" id="dob" name="dob" placeholder="Last name" required>
            </div>
            <div class="form-group">
              <label for="last_name_edit">Username<span class="required"> *</span></label>
              <input type="text" class="form-control" id="username" name="username" placeholder="Username" required>
            </div>
            <div class="form-group">
              <label for="last_name_edit">Password<span class="required"> *</span></label>
              <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
            </div>
            <button value="Add user" id='add_user' class="btn btn-success">Add User</button>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>

    </div>
  </div>
</html>

