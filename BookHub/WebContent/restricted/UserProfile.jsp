<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="classes.dao.*"  import="classes.users.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>

<title>Insert title here</title>
</head>
<body>
<% 
  UserDAO ud = new UserDAO();
  User u = new User();
  u = ud. readUserByUname(session.getAttribute("uname").toString());
  %>
<div class="container">
<div class="row">
  <div class="col-xs-6 col-md-3">
    <a href="#" class="thumbnail">
      <img src="C:\Users\SuperTramp14\Pictures\actors.jpg" alt="...">
    </a>
  </div>
  
  <div class="col-xs-4">
  <button type="button" class="btn btn-primary" onclick="password()">Change Password!</button>
  <div class="row col-xs-6" id="ps" style="display:none;">
  
  <form action="../PasswordChange" method="post">
  <div>
   <label for="inputPassword">Password</label>
  <input type="password" id="inputPassword" name="pwd" >
  </div>
 <button type="submit" value="Change" class="btn btn-primary" style="padding-bottom:3px;">Change</button>
 </form>
  </div>
  </div>
</div>

<div class="col-sm-4">

<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">First Name</h3>
  </div>
  <div class="panel-body">
    <p><%=u.getFirst_name() %>
    <p>
  </div>
</div>


</div>

<div class="col-sm-4">

<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">Last Name</h3>
  </div>
  <div class="panel-body">
    <p><%= u.getLast_name() %>
    <p>
  </div>
</div>


</div>

<div class="col-sm-4">

<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">Email</h3>
  </div>
  <div class="panel-body">
    <p><%=u.getEmail() %>
    <p>
  </div>
</div>


</div>

<div class="col-sm-4">

<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">Contact</h3>
  </div>
  <div class="panel-body">
    <p><%=u.getContact() %>
    <p>
  </div>
</div>


</div>

<div class="col-sm-4">

<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">Date of Birth</h3>
  </div>
  <div class="panel-body">
    <p><%=u.getDob() %>
    <p>
  </div>
</div>


</div>

<div class="col-sm-4">

<div class="panel panel-primary">
  <div class="panel-heading">
    <h3 class="panel-title">Username</h3>
  </div>
  <div class="panel-body">
    <p><%=u.getUsername() %>
    <p>
  </div>
</div>


</div>
</div>
<div class="container">
<div class="row">

<div class="panel panel-primary">
  <div class="panel-body">
    <a href="BookStack.jsp">View your Book Stack - Bookhub </a>
  </div>
</div>
</div>

<div class="row">

<div class="panel panel-primary">
  <div class="panel-body">
    <a href="AuthorStack.jsp">View your Author Stack - Bookhub </a>
  </div>
</div>
</div>
</div>

<script type="text/javascript">
function password(){
	$("#ps").show();
}

function pss(){
	$("#ps").hide();
	
}
</script>

</body>
</html>