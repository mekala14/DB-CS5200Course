package cs5200;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import cs5200.Movie;

public class MovieManager {
	
DataSource das;
	
	
	String createMovie = "INSERT INTO Movie (movieid, title, posterImage, releaseDate)"
			+ " VALUES (?,?,?,?);";
	
	String readTable = "select * from movie";
	String readMovie = "select * from movie where movieid = ?";
	String updateMovie = "update movie set title = ?, posterImage = ?, releaseDate = ? where movieid = ?";
	String deleteMovie = "delete from movie where movieid = ?";
	
	public MovieManager()
	{
		try {
			Context qwerty = new InitialContext();
			das = (DataSource) qwerty.lookup("java:comp/env/jdbc/dbassign4");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean  createMovie(Movie m)
	{
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(createMovie);
			statement.setString(1, m.getThemovieid());
			statement.setString(2, m.getThetitle());
			statement.setString(3, m.getTheposterImage());
			statement.setString(4, m.getThereleaseDate());
			statement.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Movie> readAllMovies()
	{
		List<Movie> m = new ArrayList<Movie>();
		try
		{
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(readTable);
			ResultSet results = statement.executeQuery();
			while(results.next()) 
			{
				Movie m1 = new Movie (
						
						results.getString("movieid"),
						results.getString("title"),
						results.getString("posterImage"),
						results.getString("releaseDate")
						);
				m.add(m1);
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}
	
	public Movie readMovie(String movieid)
	{
		Movie m1 = new Movie();
		try{
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(readMovie);
			statement.setString(1, movieid);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				m1 = new Movie (
						
						results.getString("movieid"),
						results.getString("title"),
						results.getString("posterImage"),
						results.getString("releaseDate")
						);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m1;
	}
	
	public void updateMovie(String movieid, Movie m)
	{
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateMovie);
			
			
			statement.setString(1, m.getThetitle());
			statement.setString(2, m.getTheposterImage());
			statement.setString(3, m.getThereleaseDate());
			statement.setString(4, movieid);
			
			statement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteMovie(String movieid){
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteMovie);
			statement.setString(1, movieid);	
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
