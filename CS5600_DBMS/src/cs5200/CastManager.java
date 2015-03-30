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
import cs5200.Cast;
public class CastManager {
DataSource das;
	
	
	String createCast = "INSERT INTO cast (actorid, movieid, characterName)"
			+ " VALUES (?,?,?,?,?,?);";
	
	String readTable = "select * from cast";
	String readActor = "select * from cast where actorid = ?;";
	String readMovie = "select * from cast where movieid = ?;";
	String readId = "select * from cast where castid = ?;";
	String updateCast = "update cast set actorid = ?, movieid = ?, characterName = ? where castid = ?";
	String deleteCast= "delete from cast where castid = ?";
	
	public CastManager()
	{
		try {
			Context qwerty = new InitialContext();
			das = (DataSource) qwerty.lookup("java:comp/env/jdbc/dbassign4");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean  createCast(Cast u)
	{
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(createCast);
			statement.setString(1, u.getTheactorid());
			statement.setString(2, u.getThemovieid());
			statement.setString(3, u.getThecharacterName());
			statement.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Cast> readAllCasts()
	{
		List<Cast> u = new ArrayList<Cast>();
		try
		{
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(readTable);
			ResultSet results = statement.executeQuery();
			while(results.next()) 
			{
				Cast u1 = new Cast (
						
						results.getInt("castid"),
						results.getString("movieid"),
						results.getString("actorid"),
						results.getString("characterName")
						);
				u.add(u1);
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
	}
	
	public List<Cast> readCastId(int uname)
	{
		List<Cast> u1 = new ArrayList<Cast>();
		try{
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(readId);
			statement.setInt(1, uname);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				Cast t = new Cast (
						
						results.getInt("actorid"),
						results.getString("movieid"),
						results.getString("actorid"),
						results.getString("characterName")
						);
			u1.add(t);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u1;
	}
	
	public List<Cast> readCastMovie(String uname)
	{
		List<Cast> u1 = new ArrayList<Cast>();
		try{
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(readMovie);
			statement.setString(1, uname);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				Cast t = new Cast (
						
						results.getInt("actorid"),
						results.getString("movieid"),
						results.getString("actorid"),
						results.getString("characterName")
						);
			u1.add(t);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u1;
	}
	
	public List<Cast> readCastActor(String uname)
	{
		List<Cast> u1 = new ArrayList<Cast>();
		try{
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(readActor);
			statement.setString(1, uname);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				Cast t = new Cast (
						
						results.getInt("actorid"),
						results.getString("movieid"),
						results.getString("actorid"),
						results.getString("characterName")
						);
			u1.add(t);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u1;
	}
	
	
	public void updateCast(int uname, Cast u)
	{
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateCast);
			
			statement.setString(1, u.getThemovieid());
			statement.setString(2, u.getTheactorid());
			statement.setString(3, u.getThecharacterName());
			statement.setInt(4, uname);
			
			statement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteCast(int uname){
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteCast);
			statement.setInt(1, uname);	
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
