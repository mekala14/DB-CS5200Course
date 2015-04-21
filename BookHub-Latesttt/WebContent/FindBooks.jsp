<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="classes.dao.*"  import="classes.users.*"  import="classes.client.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<title>Find Books - Book Hub</title>
</head>
<body>
   <div class="container">
        
    <div class="row">
    
    
    <div class="col-md-8">
     <% String f = (String) session.getAttribute("FirstName");
                     String l = (String) session.getAttribute("LastName");
                     if (f != null && l != null){
                    	 %>
                    	 Welcome Back , <%= session.getAttribute("FirstName") + " " + session.getAttribute("LastName") %>
                    	 <br>
                    	 <form action="LogoutServlet" method="post">
                    	 <button type="submit" value="Logout" class="btn btn-primary" style="padding-bottom:3px;">Logout</button>
                    	 
                    	 </form>
                    <% }
                     else{
                     	%>
                     	 <a href="Home.jsp">Login</a>
                     <%	  }  %>
                     
                    <a href="restricted/UserProfile.jsp?usname=<%=(String)session.getAttribute("uname")%>"> Profile</a>
                     </div>
    
    </div>

 
    
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
					 <button type="submit"  class="btn btn-primary" style="padding-bottom:3px;">Likes: <%=x.getBooks().get(0).getLikes()%></button>
					 
					<a href="restricted/AuthorDetails.jsp?aid=<%=x.getGoodreads_id()%>">Author Name:<%=x.getName()%> </a> <br>
					<button type="submit" value="Add to Stack" onclick=<%
							String usname = (String)session.getAttribute("uname");
		        	//System.out.println("Helo");
		        	bookDAO bd = new bookDAO();
		        	book b = new book();
		        	b = bd.getBookByISBN(x.getBooks().get(0).getIsbn());
		        	UserDAO y = new UserDAO();
					User ui = new User();
					ui = y.readUserByUname(usname);
					shelfDAO sd = new shelfDAO();
					shelf s = new shelf(0);
					sd.createShelf(s, ui, b);
					
					%>  class="btn btn-primary"  >Add to Stack</button>
					
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
					<a href="restricted/BookDetails.jsp?bid=<%=x.getIsbn()%>"> Book Name:<%=x.getName()%></a> <br>
					Like:
					 <button type="button"  class="li" id="<%=x.getIsbn()%>"  onclick="like(<%=x.getId()%>,<%=x.getIsbn()%>)"  style="padding-bottom:3px;"> <%=x.getLikes() %></button>
					  <br>
					<a href="restricted/AuthorDetails.jsp?aid=<%=x.getAuth().getGoodreads_id()%>">Author Name:<%=x.getAuth().getName()%> </a>
					<form action="AddBook" method="post">
					<input type="hidden" name="boname" value=<%=x.getIsbn()%>>
					<button type="submit" value="Add to Stack" class="btn btn-primary" >Add to Book Stack</button>
					</form>
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
<script type="text/javascript">
function like(a,b){                        
                  
        $.get("LikeServlet?cuname="+a+"", function(responseText) {
            alert(responseText);
           //alert(a);
            $("#"+b).html(responseText);
        });
}
 


</script>
</body>
</html>