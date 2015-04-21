<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="classes.dao.*"  import="classes.users.*" import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>

<title>Book Details - BookHub</title>
</head>
<body>

 Hey!, <%= session.getAttribute("FirstName") + " " + session.getAttribute("LastName") %>

 <%
 int bid = Integer.parseInt(request.getParameter("bid"));
 //Check Database else
	bookDAO a = new bookDAO();
 	book g = a.getBookByISBN(bid);
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
 		<p>Likes:<%=g.getLikes() %></p><br>
 		<p>Average Rating:<%=g.getAvg_rating() %></p><br>
 		<p>Published Year:<%=g.getPublish_year() %></p><br>
 		<p>Publisher:<%=g.getPublisher() %></p><br>
 		<p>Author:<a href="AuthorDetails.jsp?aid=<%=g.getAuth().getId()%>"><%=g.getAuth().getName()%> </a></p><br>
 		<p>Description:<%=g.getDescription() %></p><br>
 		<div>
 		
 		
 		</div>
 		<br>
 		<div class="container">
 		Comments on the Book:
 		<%
 		 List <comment> ca = g.getComnt();
 		 for (comment x1 : ca){
 			 User u = x1.getUsers();
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
 		
 		<div class="row">
 			 
 			 <div class="col-md-6">
 			 List of users who have this book in their Stack!: <br>
 			 <%
 			 List <shelf> sl =g.getShlf();
 			 System.out.println(sl.size());
 			 //shelf sfo = new shelf();
 			 for(shelf r: sl){
 				 %>
 				<a href="UserProfile.jsp?usname=<%=r.getUsrs().getUsername()%>">User:<%=r.getUsrs().getFirst_name() %></a> <br>
 			<%
 			 }
 			 
 			 %>
 			 
 			 </div>
 			 
 			 </div>
 		</div>
 		</div> 		
 		</div>
 		</div>
 		
 		
 		
 		<%
 		}
 	
	// API call and also insert in database
	
	// More books by author API call
 %>
</body>
</html>