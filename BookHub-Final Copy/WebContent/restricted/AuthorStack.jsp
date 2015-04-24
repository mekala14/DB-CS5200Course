<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="classes.dao.*"  import="classes.users.*" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>

<title>Author Stack - BookHub</title>
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
        <li><a href="../FindBooks.jsp">Search Books</a></li>
        <li><a href="UserProfile.jsp?usname=<%=(String)session.getAttribute("uname")%>">Profile</a></li>
       
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
String cuname = request.getParameter("usname");
String ouname = (String)session.getAttribute("uname");
// Retreive all the books along with details given a username from the database
// Display all the books along with author details
// 
if(cuname.equals(ouname))
{
	
	
	
	
	%>
	<div class="container" style="padding-top:100px;">
	
	<%
	UserDAO ud = new UserDAO();
	User u = new User();
	shelfDAO sd = new shelfDAO();
	u = ud.readUserByUname(ouname);
	List <author> bl = u.getAuthors();
	for (author s : bl)
	{
		%>
		<div class="col-md-4" style="border: black solid 1px">
		<img src="<%=s.getPoster() %>">
		
		<a href="AuthorDetails.jsp?aid=<%=s.getGoodreads_id()%>">Author:<%=s.getName() %></a><br>
		<form action="../RemoveAuthor" method="post">
					<input type="hidden" name="aoname" value=<%=s.getGoodreads_id()%>>
					<button type="submit" value="Remove from Stack" class="btn btn-primary" >Remove from Author Stack</button>
					</form>
		</div>
		<%
	}
	
	%>
	
	</div>
	
	
	<%
}
else{
	
	%>
	<div class="container" style="padding-top:100px;">
	
	<%
	UserDAO ud = new UserDAO();
	User u = new User();
	User uo = new User();
	shelfDAO sd = new shelfDAO();
	u = ud.readUserByUname(cuname);
	uo = ud.readUserByUname(ouname);
	List <author> bl = u.getAuthors();
	List <author> bo = uo.getAuthors();
	
	for (author s : bl)
	{
		Boolean toggle = false;
		%>
		<div class="col-md-4" style="border: black solid 1px">
		<img src="<%=s.getPoster() %>">
		
		<a href="AuthorDetails.jsp?aid=<%=s.getGoodreads_id() %>">Author:<%=s.getName() %></a>
		
		<%
		for(author so : bo){
			if(so.getGoodreads_id()== (s.getGoodreads_id())){
				
				 toggle = true;
				
				
			}
			
			
			
		}
		if(!toggle){
			%>
			<form action="../AddAuthor" method="post">
					<input type="hidden" name="aoname" value=<%=s.getGoodreads_id()%>>
					<button type="submit" value="Add to Stack" class="btn btn-primary" >Add to Author Stack</button>
					</form>
			<%
		}
		else{
			
			%>
			<button> In Stack already!</button><br>
			<form action="../RemoveAuthor" method="post">
					<input type="hidden" name="aoname" value=<%=s.getGoodreads_id()%>>
					<button type="submit" value="Remove from Stack" class="btn btn-primary" >Remove from Author Stack</button>
					</form>
			<%
			
		}
		
		%>
		</div>
		<%
	}
	
	%>
	
	</div>
	<%
}
%>

 	<nav class="navbar navbar-default navbar-fixed-bottom" style="padding-top:160px;">

    <p> Copyright @Team-BookHub</p>
 
</nav>

</body>
</html>