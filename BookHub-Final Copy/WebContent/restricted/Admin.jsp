<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="classes.users.*" import="classes.dao.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>

<title>Admin-BookHub</title>
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






<div class="container" style="padding-top:100px;">
<%
UserDAO rig = new UserDAO();
List <User> lilly = rig.readAllUsers();
for (User foo : lilly){
	
	%>
	<div class="col-md-4" style="padding-top: 20px;  padding-bottom:20px; padding-left:40px; font-size: 20px; font-family: papyrus">
		<div class= 'well' style='background-color:white;'>
		<a href="UserProfile.jsp?usname=<%=foo.getUsername()%>">User:<%=foo.getUsername()%></a><br>
		<%
		if(foo.getValidate()==1){
			%> <button class="<%=String.valueOf(foo.getUsername())%>" onclick="validate('<%=foo.getValidate()%>','<%=foo.getUsername()%>') "> Disable</button>
		<%	
		}
		else{
		%>
		<button class="<%=String.valueOf(foo.getUsername())%>" onclick="validate('<%=foo.getValidate()%>','<%=foo.getUsername()%>') "> Enable</button>
		
		<%
		}

		
	
%>
</div>
</div>
<%
}

%>
</div>

<script type="text/javascript">

function validate(a,b){                        
                  
	
	$.ajax({
		url: '../ValidateServlet',
		method: 'GET',
		data:{cuname:b, val:a},
		cache: false,
		success:function(responseText){
			
			$("."+b).html(responseText);		
			 
		}  
			
		});
	
       
}

</script>
</body>
</html>