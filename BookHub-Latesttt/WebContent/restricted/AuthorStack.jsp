<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="classes.dao.*"  import="classes.users.*" import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Author Stack - BookHub</title>
</head>
<body>
Hey!, <%= session.getAttribute("FirstName") + " " + session.getAttribute("LastName") %>
<%
String uname = request.getParameter("usname");
// Retreive all the authors along with details given a username from the database
// Display all the authors along with books details
// 
%>
</body>
</html>