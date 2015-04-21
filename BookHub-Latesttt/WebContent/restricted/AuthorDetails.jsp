<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="classes.dao.*"  import="classes.users.*" import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>

<title>Author Details - BookHub</title>
</head>
<body>
Hey!, <%= session.getAttribute("FirstName") + " " + session.getAttribute("LastName") %>
 <%
 int aid = Integer.parseInt(request.getParameter("aid"));
 //Check Database else
	 authorDAO a = new authorDAO();
 	author g = a.getAuthorByGoodreadsId(aid);
 	if(g != null)
 	{
 		%>
 		<div class="container">
 		
 		<div class="row">
 		
 		<div class="col-md-4" style="float:left">
 		
 		<img src="<%=g.getPoster()%>"/>
 		
 		</div>
 		<div class="col-md-8">
 		<p><%=g.getName()%> </p> <br>
 		About <br>
 		<p>Gender:<%=g.getGender() %></p><br>
 		<p>Followers:<%=g.getFollowers() %></p><br>
 		<p>Hometown:<%=g.getHometown() %></p><br>
 		<p>Description field missing in the database</p><br>
 		<div>
 		The Books written are:
 		<%
 		List <book> b = g.getBooks();
 		for (book x : b){
 		%>
 		<div class="row" style="margin-top:30px; border:solid black 2px">
					<div class="col-sm-4" style="float:left">
					<img src="<%=x.getPoster()%>" />
					</div>
					<div class="col-sm-6">
					<a href="BookDetails.jsp?bid=<%=x.getIsbn()%>"> Book Name:<%=x.getName()%></a> <br>
					 <button type="submit"  class="btn btn-primary" style="padding-bottom:3px;">Likes: <%=x.getLikes() %></button>
					 
					
					</div>
					</div>
 		
 		</div>
 		<br>
 		<div class="container">
 		Comments on the Author:
 		<%
 		 List <comment_author> ca = g.getComnt();
 		 for (comment_author x1 : ca){
 			 User u = x1.getUsr();
 			 String c = x1.getComments();
 			 String d = x1.getTime();
 			 %>
 			 <div class="row" style="margin-top:2px; border: solid balck 2px;">
 			 <div class="col-md-4">
 			 Comment By:
 			  <a href="UserProfile.jsp?usname=<%=u.getUsername()%>">User:<%=u.getFirst_name() %></a>
 			 <br>
 			 at: <%=d%> <br>
 			 Comment:
 			 <%=c %>
 			 </div>
 			 
 			 </div>
 		 <%
 		 }
 		%>
 		</div>
 		</div> 		
 		</div>
 		</div>
 		
 		
 		
 		<%
 		}
 	}
	// API call and also insert in database
	
	// More books by author API call
 %>
</body>
</html>