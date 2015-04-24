<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="classes.dao.*"  import="classes.users.*"  import="classes.client.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<title>Find Books - Book Hub</title>
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
        <li class="active"><a href="Home.jsp">Home <span class="sr-only">(current)</span></a></li>
        <%
        
        if(("admin").equals((String)session.getAttribute("uname")))
        {
        	%>
        	 <li><a href="restricted/Admin.jsp">Admin-Profile</a></li>
        	<%
        }
        else{
        	%>
        	 <li><a href="restricted/UserProfile.jsp?usname=<%=(String)session.getAttribute("uname")%>">Profile</a></li>
       <%
        }
       
        %>
      </ul>
   
      <%
      String f = (String) session.getAttribute("FirstName");
      String l = (String) session.getAttribute("LastName");

      if (f != null && l != null){
     	 %>
     	 <p class="navbar-text"><%= session.getAttribute("FirstName") + " " + session.getAttribute("LastName") %></p>
     	 <br>
     	 <form class="navbar-form navbar-left" action="LogoutServlet" method="post">
        <div class="form-group" >
          
        </div>
        <button type="submit" class="btn btn-default">Logout</button>
      </form>
     <% }
      else{
      	%>
      	 <a href="Home.jsp"><button type="submit">Login</button></a><br>
      	 <a href="Registration.jsp"><button type="submit">Signup!!</button></a>
      <%	  }  %>
      
     
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>


















   <div class="container" style="padding-top:100px; padding-bottom:500px;">
        
    
    
        <div class="content" style="font-family:Papyrus; font-size:20px; padding-left:50px;">
        <span class="label label-warning">Enter the name of the Book or Name of the Author</span>
            
            <form action="GetBooks" method="post"> 
           <input type="text" class="titlee" name="bookname" />
        <button type="submit"  class="btn btn-primary" style="padding-bottom:3px;">Search</button>
        </form>
		<%
		@SuppressWarnings("unchecked")
		List<book> use = (List<book>)request.getAttribute("listq");
		
		if (request.getAttribute("listq")== null)
		{
			
			/*  String name = (String)request.getAttribute("bname");
			System.out.println(name);
			 */
			 
			 
		   String name = request.getParameter("bookname");
			 
			 if(name!=null)
			 {
				 int count = 0;
				 //API call
				 List <author> al = new ArrayList<author> ();
				 client c = new client();
				 al = c.findBookByAB(name);
				 for (author x : al){
					count++;
					System.out.println(count+"This is count valu-e--------" +x.getBooks().size() +"+----------------------------------------------");
					 %>
					 <div class="row" style="margin-top:30px; border:solid black 2px">
					<div class="col-sm-4" style="float:left">
					<img src="<%=x.getBooks().get(0).getPoster()%>" />
					</div>
					<div class="col-sm-6">
					<a href="restricted/BookDetails.jsp?bid=<%=x.getBooks().get(0).getIsbn()%>"> Book Name:<%=x.getBooks().get(0).getName()%></a> <br>
					 <button type="submit"  class="li" id="<%=x.getBooks().get(0).getIsbn()%>"  onclick="like(<%=x.getBooks().get(0).getId()%>,<%=x.getBooks().get(0).getIsbn()%>)"  style="padding-bottom:3px;">Likes: <%=x.getBooks().get(0).getLikes()%></button>
					 
					<a href="restricted/AuthorDetails.jsp?aid=<%=x.getGoodreads_id()%>">Author Name:<%=x.getName()%> </a> <br>
					<form action="AddBook" method="post">
					<input type="hidden" name="boname" value=<%=x.getBooks().get(0).getIsbn()%>>
					<button type="submit" value="Add to Stack" class="btn btn-primary" >Add to Book Stack</button>
					</form>
					<form action="AddAuthor" method="post">
					<input type="hidden" name="aoname" value=<%=x.getGoodreads_id() %>>
					<button type="submit" value="Add to Author Stack" class="btn btn-primary" >Add to Author Stack</button>
					</form>
					<br>
					<button type="submit"  class="btn btn-primary" > Rating:<%=x.getBooks().get(0).getAvg_rating() %> </button>
					</div>
					</div>
					<%
				 }
				 // Insert into Database
			 	/* authorDAO da = new authorDAO();
				bookDAO bd = new bookDAO();
			 	for (author x : al){
			 		author a = da.getAuthorByGoodreadsId(x.getGoodreads_id());
			 		if (a == null){
			 			author a1 = new author(0, x.getName(), "", "", 0, 0, x.getPoster(), x.getGoodreads_id());
			 		    da.createAuthor(a1);
			 			
			 			book b = x.getBooks().get(0);
			 			author a2 = da.getAuthorByGoodreadsId(x.getGoodreads_id());
			 			bd.createBook(a2, b); 
			 			
			 		}
			 		else{
			 			a.setGender(x.getGender());
			 			a.setHometown(x.getHometown());
			 			a.setPoster(x.getPoster());
			 			a.setWork_count(x.getWork_count());
			 			x.getBooks().get(0).setAuth(a);
			 			a.getBooks().addAll(x.getBooks());
			 			da.updateAuthor(a.getId(), a);
			 		}
			 		
			 	} */
			 	//Use Prsing //xml function here
			 	
			 	
			 	
			 
			 }
			
		
			}
			
			else{
				for (book x : use)
				{
					%>
					<div class="row" style="margin-top:30px; border:solid black 2px">
					<div class="col-sm-4" style="float:left">
					<img src="<%=x.getPoster()%>" />
					</div>
					<div class="col-sm-6">
					<a href="restricted/BookDetails.jsp?bid=<%=x.getIsbn()%>"> Book Name:<%=x.getName()%></a> 
					Like:
					 <button type="button"  class="li" id="<%=x.getIsbn()%>"  onclick="like(<%=x.getId()%>,<%=x.getIsbn()%>)"  style="padding-bottom:3px;"> <%=x.getLikes() %></button>
					  <br>
					<a href="restricted/AuthorDetails.jsp?aid=<%=x.getAuth().getGoodreads_id()%>">Author Name:<%=x.getAuth().getName()%> </a>
					<form action="AddBook" method="post">
					<input type="hidden" name="boname" value=<%=x.getIsbn()%>>
					<button type="submit" value="Add to Stack" class="btn btn-primary" >Add to Book Stack</button>
					</form>
					<form action="AddAuthor" method="post">
					<input type="hidden" name="aoname" value=<%=x.getAuth().getGoodreads_id() %>>
					<button type="submit" value="Add to Author Stack" class="btn btn-primary" >Add to Author Stack</button>
					</form>
					<br>
					<button type="submit"  class="btn btn-primary" > Rating:<%=x.getAvg_rating() %> </button>
					</div>
					</div>
			   	<%
				}	
			}	
		 %>
	
		
        <br />
        
    <br />
    
   
<!-- div class="x" id="results"  style="width:1000px; border: 2px solid black; border-radius:6px 6px; background-color: lightgray;min-height:700px;   "></div> -->
</div>

</div>

<nav class="navbar navbar-default navbar-fixed-bottom" >

    <p> Copyright @Team-BookHub</p>
 
</nav>
<script type="text/javascript">
function like(a,b){                        
                  
	
	$.ajax({
		url: 'LikeServlet',
		method: 'GET',
		data:{cuname:a},
		cache: false,
		success:function(responseText){
			$("#"+b).html(responseText);		
			 
		}  
			
		});
	
       
}
 


</script>
</body>
</html>