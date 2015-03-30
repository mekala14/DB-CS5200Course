<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="cs5200.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Testing Movie</title>
</head>
<body>
<h2> Hello World, Welcome</h2>
<% 
    
 	MovieManager m = new MovieManager();
 /*
    //add user
	Movie mm = new Movie();
	mm.setThemovieid("Movie2");
	mm.setThetitle("Dark Knight");
	mm.setTheposterImage("www.darkknight.com");
	mm.setThereleaseDate("2015-11-22");
	
	m.createMovie(mm);
	*/
	
/*
	//read all user
	List<Movie> m1 = m.readAllMovies();
	
	for(int i=0; i<m1.size(); i++)
	{
		System.out.println(m1.get(i).getThemovieid());
	}
	
*/	
	/*
	//read single user
	Movie m2 = m.readMovie("Movie1");
	System.out.println(m2.getThemovieid());
	*/
	
	/*
	//update user
	Movie uq = new Movie();
	uq.setThemovieid("Movie2");
	uq.setThetitle("Into the wild");
	uq.setTheposterImage("www.intothewild.com");
	uq.setThereleaseDatee("2012-03-03");
	
	m.updateMovie("Movie1", uq);
*/
	
	//delete from user
	m.deleteMovie("Movie2");

%>
</body>
</html>