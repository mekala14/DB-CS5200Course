<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="classes.dao.*"  import="classes.users.*"  import="java.util.*"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>

<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">BookHub</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="../Home.jsp">Home <span class="sr-only">(current)</span></a></li>
        <li><a href="../FindBooks.jsp>">Search Books</a></li>
     
       
      </ul>
   
      
     	 <p class="navbar-text"><%= session.getAttribute("FirstName") + " " + session.getAttribute("LastName") %></p>
     	 <br>
     	 <form class="navbar-form navbar-left" action="../LogoutServlet" method="post">
        <div class="form-group" >
          
        </div>
        <button type="submit" class="btn btn-default">Logout</button>
      </form>
     
      
     
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

<%
String cuname= request.getParameter("usname");
String ouname = session.getAttribute("uname").toString();
%>
<%
if(cuname == ouname)
{
	%>
	Hey!, <%=cuname%>
	<%
}
%>
<a href="BookStack.jsp?usname=<%=cuname%>"> Profile</a>
<% 
  UserDAO ud = new UserDAO();
  User u = new User();
  User uo = new User();
  u = ud. readUserByUname(cuname);
  uo = ud.readUserByUname(ouname);
  %>
<div class="container" style="padding-top:100px;">
<div class="row">
  <div class="col-xs-6 col-md-3">
    <a href="#" class="thumbnail">
      <img src="C:\Users\SuperTramp14\Pictures\actors.jpg" alt="...">
    </a>
  </div>
  <% 
  
  if(cuname.equals(ouname)){
	  
  %>
  
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
  
  <%
  }
  
  else{
	//Following or Follow logic.
	List <User> fl = u.getFollowers();
	int size = fl.size();
	System.out.println(size +"vvvvvvvvvvvvvvvvvvvvvvv");
	Boolean value = false;
	for (User fp : fl){
		if(fp.getUsername().contains(ouname)){
			value = true;
		}
		
	}
	
	if(value){
	%>
	  
	  <div class="col-xs-4">
	  <button type="button" class="btn btn-primary" onclick="password()"> Following</button>
	  
	  </div>
<%
	}
	else{
		%>
		<button type="button" class="btn btn-primary" id="fol" > Follow </button>
		<%
	}
  }
  
  %>
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
    <%//retrieve following from the database%>
  </div>
</div>

</div>

<div class="row">

<div class="panel panel-primary">
  <div class="panel-body">
    <div class="row">
    Followers:
    <br>
    <%
    String ufname =  request.getParameter("usname");
    UserDAO la = new UserDAO();
    User sj = new User();
    sj = la.readUserByUname(ufname);
    List <User> det = sj.getFollowers();
    for (User ny : det){
    	%>
    	<a href="UserProfile.jsp?usname=<%=ny.getUsername()%>">User:<%=ny.getFirst_name() %></a> <br>
    	<%
    }
    		 %>
    </div>
    
    <div class="row">
    Following:
    <br>
    <%
    String gname =  request.getParameter("usname");
    UserDAO lao = new UserDAO();
    User sjo = new User();
    sjo = lao.readUserByUname(ufname);
    List <User> deto = sj.getFollowing();
    for (User ny : deto){
    	%>
    	<a href="UserProfile.jsp?usname=<%=ny.getUsername()%>">User:<%=ny.getFirst_name() %></a> <br>
    	<%
    }
    		 %>
    </div>
  </div>
</div>
</div>

<div class="row">

<div class="panel panel-primary">
  <div class="panel-body">
    <a href="BookStack.jsp?usname=<%=cuname%>">View The Book Stack - Bookhub </a>
  </div>
</div>
</div>

<div class="row" style="padding-bottom:160px;">

<div class="panel panel-primary">
  <div class="panel-body">
    <a href="AuthorStack.jsp?usname=<%=cuname%>">View The Author Stack - Bookhub </a>
  </div>
</div>
</div>
</div>
<nav class="navbar navbar-default navbar-fixed-bottom" >

    <p> Copyright @Team-BookHub</p>
 
</nav>
<script>
var tempresp;
$(document).ready(function() {                        
    $('#fol').click(function() {               
        $.get('../FollowServlet?cuname=<%=cuname%>', function(responseText) {
           // alert(responseText);
            $("#fol").html(responseText);
        });
    });
});



</script>

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