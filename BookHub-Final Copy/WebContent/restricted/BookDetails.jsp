<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="classes.dao.*"  import="classes.users.*" import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>

<title>Book Details - BookHub</title>
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
 int bid = Integer.parseInt(request.getParameter("bid"));
 //Check Database else
	bookDAO a = new bookDAO();
 	book g = a.getBookByISBN(bid);
 	if(g != null)
 	{
 		%>
 		<div class="container" style="padding-top:90px;">
 		
 		<div class="row">
 		
 		<div class="col-md-4" style="float:left">
 		
 		<img src="<%=g.getPoster()%>"/>
 		
 		</div>
 		<div class="col-md-8">
 		<p><%=g.getName()%> </p> <br>
 		About <br>
 		<p>Likes:<%=g.getLikes() %></p><br>
 		<p id="avrating">Average Rating:<%=g.getAvg_rating() %></p><br>
 		<p>Published Year:<%=g.getPublish_year() %></p><br>
 		<p>Publisher:<%=g.getPublisher() %></p><br>
 		<p>Author:<a href="AuthorDetails.jsp?aid=<%=g.getAuth().getGoodreads_id()%>"><%=g.getAuth().getName()%> </a></p><br>
 		<p>Description:<%=g.getDescription() %></p><br>
 		<form action="../AddBook" method="post">
					<input type="hidden" name="boname" value=<%=g.getIsbn()%>>
					<button type="submit" value="Add to Stack" class="btn btn-primary" >Add to Book Stack</button>
					</form>
 		<div>
 		
 		
 		</div>
 		<div class="container">
 		<div class="row">
 		Rate on this book: <br>
 		<input type="text" id="rate" style="height:20px; width:50px;">
 		<button type="button" class="btn btn-primary" id="rating"> Rate on scale of 10!</button>
 		
 		</div>
 		<br>
 		<div class="rat" id="rat">
 		Ratings on this book!!
 		<%
 		 List <rating> ra = g.getRatings();
 		 for (rating x1 : ra){
 			 User u = x1.getUser();
 			 String c = String.valueOf(x1.getRating());
 			 %>
 			 <div class="row" style="margin-top:2px; border: solid balck 2px;">
 			 <div class="col-md-4">
 			 Rating By:
 			  <a href="UserProfile.jsp?usname=<%=u.getUsername()%>">User:<%=u.getFirst_name() %></a>
 			 <br>
 			
 			 Rating:
 			 <%=c %>
 			 </div>
 			 
 			 </div>
 			 
 			 
 		 <%
 		 }
 		%>
 		</div>
 		
		 
 		</div>
 		
 		<br>
 		<div class="container">
 		<div class="row">
 		Comment on this book: <br>
 		<input type="text" id="comt" style="height:40px; width:500px;">
 		<button type="button" class="btn btn-primary" id="comment"> Comment</button>
 		
 		</div>
 		<br>
 		<div class="com" id="com">
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
 		</div>
 		<div class="row">
 			 
 			 <div class="col-md-6" style="padding-bottom:200px;">
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
 
 
 
 
 <script type="text/javascript">
var tempresp;
$(document).ready(function() {                        
    $('#comment').click(function() {   
    	//alert("HelloJi");
    	var fullcommnet = $("#comt").val();
    	var name = ""+"<%=usename%>"+"";
      
        	$.ajax({
        		url: '../CommentBook',
        		method: 'GET',
        		data:{comm:fullcommnet,id:<%=request.getParameter("bid")%>},
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

$(document).ready(function() {
    $('#rating').click(function() {   
    	//alert("HelloJi");
    	var fullcommnet = $("#rate").val();
    	var name = ""+"<%=usename%>"+"";
      
        	$.ajax({
        		url: '../RatingBook',
        		method: 'GET',
        		data:{ratt:fullcommnet,id:<%=request.getParameter("bid")%>},
        		cache: false,
        		success:function(responseText){
        			$(".rat").append('<div class="row" style="margin-top:2px; border: solid balck 2px; padding-bottom:30px;">' +
             			   '<div class="col-md-4">'+
        			 "Rating By:"+ name +
        			  '<a href="UserProfile.jsp?usname='+name+'">User:'+ name+'</a>'+
        			 "<br>"+
        			 
        			 "Rating:"+ fullcommnet+
        			
        			 "</div>"+
        			 
        			 "</div>"
        			 
        			 
      			  );
        			$("#rate").val('');
        			$("#avrating").html("Average Rating:"+responseText);
        		}
        	});
           //alert(responseText);
           
        	   
        	   
        	 
        });
    
    });




</script>

<nav class="navbar navbar-default navbar-fixed-bottom">

    <p> Copyright @Team-BookHub</p>
 
</nav>
</body>
</html>