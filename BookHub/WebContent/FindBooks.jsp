<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="//code.jquery.com/jquery-1.11.2.min.js"></script>
<title>Find Books - Book Hub</title>
</head>
<body>
   <div class="container">
        
    <div class="row">
    
    Welcome Back!, <%= session.getAttribute("FirstName") + " " + session.getAttribute("LastName") %>
    
    </div>
    
    
        <div class="content" style="font-family:Papyrus; font-size:20px; padding-left:50px;">
        <span class="label label-warning">Enter the name of the Book or Name of the Author</span>
            
             
           <input type="text" class="titlee" />
        <input type="button" class="sub" value="submit" style="border-radius:6px 6px;" />

        <br />
        
    <br />
                 
<div class="x" id="results"  style="width:1000px; border: 2px solid black; border-radius:6px 6px; background-color: lightgray;min-height:700px;   "></div>
</div>

</div>

</body>
</html>