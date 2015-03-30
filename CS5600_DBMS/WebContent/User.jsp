<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="cs5200.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Testing</title>
</head>
<body>
<h1>Hello World, Welcome</h1>
<% 
    
 	UserManager m = new UserManager();
  /*  
    //add user
	User u = new User();
	u.setTheusername("user2");
	u.setThepassword("12345");
	u.setThefirstName("fname2");
	u.setThelastName("lname2");
	u.setTheemail("email@2.com");
	u.setThedateOfBirth("1990-02-02");
	m.createUser(u);
	*/
	
/*
	//read all user
	List<User> u1 = m.readAllUsers();
	
	for(int i=0; i<u1.size(); i++)
	{
		System.out.println(u1.get(i).getTheemail());
	}
	
*/	
	/*
	//read single user
	user u2 = u.readUser("user2");
	System.out.println(u2.getEmail());
	*/
	
	/*
	//update user
	User uq = new User();
	uq.setTheusername("user3");
	uq.setThepassword("123456");
	uq.setThefirstName("fname3");
	uq.setThelastName("lname2");
	uq.setTheemail("email@2.com");
	uq.setThedateOfBirth("1990-02-02");
	m.updateUser("user2", uq);
*/
	
	//delete from user
	m.deleteUser("user2");
%>
</body>
</html>