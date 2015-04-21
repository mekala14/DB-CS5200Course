<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="classes.dao.*"  import="classes.users.*" import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>

<title>Book Stack - BookHub</title>
</head>
<body>
Hey!, <%= session.getAttribute("FirstName") + " " + session.getAttribute("LastName") %>
<%
String cuname = request.getParameter("usname");
String ouname = (String)session.getAttribute("uname");
// Retreive all the books along with details given a username from the database
// Display all the books along with author details
// 
if(cuname.equals(ouname))
{
	
	
	
	
	%>
	<div class="container">
	
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
		
		</div>
		<%
	}
	
	%>
	
	</div>
	
	
	<%
}
else{
	
	%>
	<div class="container">
	
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
		<div class="col-md-4" style="border: black solid 1px">
		<img src="<%=s.getBoks().getPoster() %>">
		<a href="BookDetails.jsp?bid=<%=s.getBoks().getIsbn()%>">Book:<%=s.getBoks().getName() %></a>
		<a href="AuthorDetails.jsp?aid=<%=s.getBoks().getAuth().getGoodreads_id() %>">Author:<%=s.getBoks().getAuth().getName() %></a>
		<%
		for(shelf so : bo){
			if(so.getBoks().getIsbn()== (s.getBoks().getIsbn())){
				
				 toggle = true;
				
				
			}
			
			
			
		}
		if(!toggle){
			%>
			<button>Add to stack </button>
			<%
		}
		else{
			
			%>
			<button> In Stack already!</button>
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

</body>
</html>