<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>

<title>Book Hub- Home</title>
</head>
<body>
<div class="container">
<div class="row">
<h1 class="col-md-8" style="font-size:80px;font-style:italic;">Book Hub</h1>

</div>

<div class="row" style="margin-top:30px;">
<div><span class="label label-primary col-md-6" id="registered" style="font-size:60px;" onclick="login()"> Registered Users </span></div>
<div class="col-md-4" id="loginform" style="border:2px solid black;display:none; float:right;">
<form action="LoginServlet" method="post">
    <div class="form-group">
        <label for="inputUsername">Username</label>
        <input class="form-control" id="inputUsername" type="text" name="uname" >
    </div>
    <div class="form-group">
        <label for="inputPassword">Password</label>
        <input type="password" class="form-control" id="inputPassword" name="pwd" >
    </div>
    <div class="checkbox">
        <label><input type="checkbox"> Remember me</label>
    </div>
    <button type="submit" value="Login" class="btn btn-primary" style="padding-bottom:3px;">Login</button>
</form>
</div>
<div class="col-md-4" id="emailform" style="border:2px solid black;display:none; float:right;">
<form>
    <div class="form-group">
        <label for="inputEmail"> Email</label>
        <input class="form-control" id="inputEmail" >
    </div>
    <a href="FindBooks.jsp" class="label label-warning" style="padding:2px; ">Proceed</a>
</form>
</div>
</div>

<div class="row" style="margin-top:50px;">
<div><span class="label label-warning col-md-6" id="guest" style="font-size:60px;" onclick="email()"> Guest Users</span></div>
</div>
</div>
<script type="text/javascript">
function login(){
	//$("#guest").toggle();
	$("#loginform").show();
	$("#emailform").hide();
}
function email(){
	//$("#registered").toggle();
	$("#loginform").hide();
	$("#emailform").show();
}
</script>



</body>
</html>