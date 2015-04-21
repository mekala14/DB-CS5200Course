<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<title>Registration - Book Hub</title>
</head>
<body>
<form class="form-horizontal" action="RegisterServlet" method="post">
    <div class="form-group">
        <label  class="control-label col-xs-2">First Name</label>
        <div class="col-xs-4">
            <input type="text" class="form-control"  name="fname">
        </div>
    </div>
    <div class="form-group">
        <label  class="control-label col-xs-2">Last Name</label>
        <div class="col-xs-4">
            <input type="text" class="form-control"  name="lname">
        </div>
    </div>
    <div class="form-group">
        <label for="inputUsername" class="control-label col-xs-2">Username</label>
        <div class="col-xs-4">
            <input type="text" class="form-control" id="inputUsername" name="uname">
        </div>
    </div>
    
    <div class="form-group">
        <label for="inputPassword" class="control-label col-xs-2">Password</label>
        <div class="col-xs-4">
            <input type="password" class="form-control" id="inputPassword" name="pwd">
        </div>
    </div>
    
    <div class="form-group">
        <label for="inputEmail" class="control-label col-xs-2">Email</label>
        <div class="col-xs-4">
            <input type="text" class="form-control" id="inputEmail" name="email" value="xxxx@xxx.com">
        </div>
    </div>
    
    <div class="form-group">
        <label for="inputDob" class="control-label col-xs-2">DOB</label>
        <div class="col-xs-4">
            <input type="text" class="form-control" id="inputDob" name="db" value="yyyy-mm-dd" >
        </div>
    </div>
    
    <div class="form-group">
        <label for="inputContact" class="control-label col-xs-2">Contact</label>
        <div class="col-xs-4">
            <input type="text" class="form-control" id="inputContact" name="contact" value="xxx-xxx-xxxx">
        </div>
    </div>
    
    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-10">
            <div class="checkbox">
                <label><input type="checkbox"> Remember me</label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-xs-offset-2 col-xs-10">
            <button type="submit" class="btn btn-primary" value="submit"> Register</button>
        </div>
    </div>
</form>
</body>
</html>