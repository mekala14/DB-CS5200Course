<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<title>Registration - Book Hub</title>
</head>
<body>
<form class="form-horizontal">
    <div class="form-group">
        <label for="inputFName" class="control-label col-xs-2">First Name</label>
        <div class="col-xs-4">
            <input type="text" class="form-control" id="inputFName">
        </div>
    </div>
    <div class="form-group">
        <label for="inputLName" class="control-label col-xs-2">Last Name</label>
        <div class="col-xs-4">
            <input type="text" class="form-control" id="inputLName">
        </div>
    </div>
    <div class="form-group">
        <label for="inputUsername" class="control-label col-xs-2">Username</label>
        <div class="col-xs-4">
            <input type="text" class="form-control" id="username">
        </div>
    </div>
    
    <div class="form-group">
        <label for="inputPassword" class="control-label col-xs-2">Password</label>
        <div class="col-xs-4">
            <input type="password" class="form-control" id="inputPassword" >
        </div>
    </div>
    
    <div class="form-group">
        <label for="inputEmail" class="control-label col-xs-2">Email</label>
        <div class="col-xs-4">
            <input type="text" class="form-control" id="inputEmail" >
        </div>
    </div>
    
    <div class="form-group">
        <label for="inputDob" class="control-label col-xs-2">DOB</label>
        <div class="col-xs-4">
            <input type="text" class="form-control" id="inputDob" >
        </div>
    </div>
    
    <div class="form-group">
        <label for="inputContact" class="control-label col-xs-2">Contact</label>
        <div class="col-xs-4">
            <input type="text" class="form-control" id="inputContact" >
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
            <button type="submit" class="btn btn-primary">Login</button>
        </div>
    </div>
</form>
</body>
</html>