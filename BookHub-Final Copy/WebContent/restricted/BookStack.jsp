<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="classes.dao.*"  import="classes.users.*" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>

<title>Book Stack - BookHub</title>
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
	List <shelf> bl = u.getShlf();
	for (shelf s : bl)
	{
		%>
		<div class="col-md-4" style="border: black solid 1px">
		<img src="<%=s.getBoks().getPoster() %>">
		<a href="BookDetails.jsp?bid=<%=s.getBoks().getIsbn()%>">Book:<%=s.getBoks().getName() %></a>
		<a href="AuthorDetails.jsp?aid=<%=s.getBoks().getAuth().getGoodreads_id() %>">Author:<%=s.getBoks().getAuth().getName() %></a>
		<br><form action="../DeleteBook" method="post">
					<input type="hidden" name="boname" value=<%=s.getBoks().getIsbn()%>>
					<button type="submit" value="Delete from Stack" class="btn btn-primary" >Delete from Book Stack</button>
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
	<div class="container" style="padding-bottom:160px;">
	
	<%
	UserDAO ud = new UserDAO();
	User u = new User();
	User uo = new User();
	shelfDAO sd = new shelfDAO();
	u = ud.readUserByUname(cuname);
	uo = ud.readUserByUname(ouname);
	List <shelf> bl = u.getShlf();
	List <shelf> bo = uo.getShlf();
	
	for (shelf s : bl)
	{
		Boolean toggle = false;
		%>
		<div class="col-md-4" style="padding-top: 20px;  padding-bottom:20px; padding-left:40px; font-size: 20px; font-family: papyrus">
		<div class= 'well' style='background-color:white;'>
		<a href="BookDetails.jsp?bid=<%=s.getBoks().getIsbn()%>">Book:<%=s.getBoks().getName() %></a><br>
		<a href="AuthorDetails.jsp?aid=<%=s.getBoks().getAuth().getGoodreads_id() %>">Author:<%=s.getBoks().getAuth().getName() %></a><br>
		<img src="<%=s.getBoks().getPoster() %>"  width="200" height="200"><br><br>
		
		
		<%
		for(shelf so : bo){
			if(so.getBoks().getIsbn()== (s.getBoks().getIsbn())){
				
				 toggle = true;
				
				
			}
			
			
			
		}
		if(!toggle){
			%>
			<form action="../AddBook" method="post">
					<input type="hidden" name="boname" value=<%=s.getBoks().getIsbn()%>>
					<button type="submit" value="Add to Stack" class="btn btn-primary" >Add to Book Stack</button>
					</form>
			<%
		}
		else{
			
			%>
			<button class="btn btn-primary"> In Stack already!</button><br>
			
			<%
			
		}
		
		%>
		</div></div>
		<%
	}
	
	%>
	
	</div>
	<%
}
%>

<nav class="navbar navbar-default navbar-fixed-bottom" >

    <p> Copyright @Team-BookHub</p>
 
</nav>

</body>
</html>