<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="classes.dao.*"  import="classes.users.*" import="java.util.List" import="classes.client.*"%>
<!DOCTYPE >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>

<title>Author Details - BookHub</title>
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
 String usename = (String)session.getAttribute("uname");
 String ti = String.valueOf(System.currentTimeMillis());
 int aid = Integer.parseInt(request.getParameter("aid"));
 //Check Database else
	 authorDAO a = new authorDAO();
 	author g = a.getAuthorByGoodreadsId(aid);
 	System.out.println(g.getHometown()+"THis is author home town");
 	if(g.getWork_count() != 0)
 	{
 		%>
 		<div class="container" style="padding-top:100px">
 		
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
 		<form action="../AddAuthor" method="post">
					<input type="hidden" name="aoname" value=<%=g.getGoodreads_id() %>>
					<button type="submit" value="Add to Author Stack" class="btn btn-primary" >Add to Author Stack</button>
					</form>
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
					 <button type="button"  class="li" id="<%=x.getIsbn()%>"  onclick="like(<%=x.getId()%>,<%=x.getIsbn()%>)"  style="padding-bottom:3px;"> <%=x.getLikes() %></button>
					  <br>
					
					</div>
					</div>
 		<%} %>
 		</div>
 		<br>
 		<div class="container">
 		<div class="row">
 		Comment on this Author: <br>
 		<input type="text" id="comt" style="height:40px; width:500px;">
 		<button type="button" class="btn btn-primary" id="comment"> Comment</button>
 		
 		</div>
 		<br>
 		<div class="com" id="com">
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
 		<div class="row">
 			 
 			 <div class="col-md-6">
 			 List of users who have this Author in their Author Stack!22222222: <br>
 			 <%
 			 List <User> sl =g.getFans();
 			 System.out.println(sl.size());
 			 //shelf sfo = new shelf();
 			 for(User r: sl){
 				 %>
 				<a href="UserProfile.jsp?usname=<%=r.getUsername()%>">User:<%=r.getFirst_name() %></a> <br>
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
 	else{
 		client c = new client();
 		author v = new author();
 		authorDAO ty = new authorDAO();
 		v = c.getAuthorDetails(g.getGoodreads_id());
 		g.setGender(v.getGender());
 		g.setFollowers(v.getFollowers());
 		g.setHometown(v.getHometown());
 		g.setWork_count(v.getWork_count());
 		ty.updateAuthor(g.getId(), g);
 		
 		%>
 		<div class="container">
 		
 		<div class="row">
 		
 		<div class="col-md-4" style="float:left">
 		
 		<img src="<%=g.getPoster()%>"/>
 		
 		</div>
 		<div class="col-md-8">
 		<p><%=v.getName()%> </p> <br>
 		About <br>
 		<p>Gender:<%=v.getGender() %></p><br>
 		<p>Followers:<%=v.getFollowers() %></p><br>
 		<p>Hometown:<%=v.getHometown() %></p><br>
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
					 <button type="button"  class="li" id="<%=x.getIsbn()%>"  onclick="like(<%=x.getId()%>,<%=x.getIsbn()%>)"  style="padding-bottom:3px;"> <%=x.getLikes() %></button>
					  <br>
					</div>
					</div>
 		<%} %>
 		</div>
 		<br>
 		<div class="container">
 		<div class="row">
 		Comment on this Author: <br>
 		<input type="text" id="comt" style="height:40px; width:500px;">
 		<button type="button" class="btn btn-primary" id="comment"> Comment</button>
 		
 		</div>
 		<br>
 		<div class="com" id="com">
 		Comments on the Author:
 		<%
 		 List <comment_author> ca = g.getComnt();
 		 for (comment_author x1 : ca){
 			 User u = x1.getUsr();
 			 String p = x1.getComments();
 			 String d = x1.getTime();
 			 %>
 			 <div class="row" style="margin-top:2px; border: solid balck 2px;">
 			 <div class="col-md-4">
 			 Comment By:
 			  <a href="UserProfile.jsp?usname=<%=u.getUsername()%>">User:<%=u.getFirst_name() %></a>
 			 <br>
 			 at: <%=d%> <br>
 			 Comment:
 			 <%=p %>
 			 </div>
 			 
 			 </div>
 		 <%
 		 }
 		%>
 		</div>
 		<div class="row">
 			 
 			 <div class="col-md-6">
 			 List of users who have this Author in their Author Stack!: <br>
 			 <%
 			 List <User> sl =g.getFans();
 			 System.out.println(sl.size());
 			 //shelf sfo = new shelf();
 			 for(User r: sl){
 				 %>
 				<a href="UserProfile.jsp?usname=<%=r.getUsername()%>">User:<%=r.getFirst_name() %></a> <br>
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

 
	
 %>
 
 
 <div class="container" style="padding-bottom:160px;">
 		<h3>More Books by this author:</h3><br>
 		<%
 		
 		client cm = new client();
 		List <book> bm = cm.getAllBooksByAuthor(Integer.parseInt(request.getParameter("aid")));
 		
 		for(book s : bm){
 			%>
 			<div class="col-md-4" style="padding-top: 20px;  padding-bottom:20px; padding-left:40px; font-size: 20px; font-family: papyrus">
		<div class= 'well' style='background-color:white;'>
		<a href="BookDetails.jsp?bid=<%=s.getIsbn()%>">Book:<%=s.getName() %></a><br>
		<a href="AuthorDetails.jsp?aid=<%=s.getAuth().getGoodreads_id() %>">Author:<%=s.getAuth().getName() %></a><br>
		<img src="<%=s.getPoster() %>"  width="200" height="200"><br><br>
		</div>
		</div>
 			<%
 			
 		}
 		%>
 		
 		
 		</div> 
 		
 		<nav class="navbar navbar-default navbar-fixed-bottom" >

    <p> Copyright @Team-BookHub</p>
 
</nav>
 <script type="text/javascript">
 
 var tempresp;
 $(document).ready(function() {                        
     $('#comment').click(function() {   
     	//alert("HelloJi");
     	var fullcommnet = $("#comt").val();
     	var name = ""+"<%=usename%>"+"";
       
         	$.ajax({
         		url: '../CommentAuthor',
         		method: 'GET',
         		data:{comm:fullcommnet,id:<%=request.getParameter("aid")%>},
         		cache: false,
         		success:function(responseText){
         			$(".com").append('<div class="row" style="margin-top:2px; border: solid balck 2px;">' +
              			   '<div class="col-md-4">'+
         			 "Comment By:"+ name +
         			  '<a href="UserProfile.jsp?usname='+name+'">User:'+ name+'</a>'+
         			 "<br>"+
         			 "at:"+<%=ti%> +"<br>"+
         			 "Comment:"+ fullcommnet+
         			
         			 "</div>"+
         			 
         			 "</div>"
         			 
         			 
       			  );
         			$("#comt").val('');
         		}
         	});
            //alert(responseText);
            
         	   
         	   
         	 
         });
     
 });
 
 </script>
 
 
 <script type="text/javascript">
function like(a,b){                        
                  
	
	$.ajax({
		url: '../LikeServlet',
		method: 'GET',
		data:{cuname:a,},
		cache: false,
		success:function(responseText){
			$("#"+b).html(responseText);		
			 
		}  
			
		});
	
       
}
 


</script>
</body>
</html>