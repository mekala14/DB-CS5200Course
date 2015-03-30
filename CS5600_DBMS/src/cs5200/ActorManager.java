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

import cs5200.Actor;

public class ActorManager {
DataSource das;
	
	
	String createActor = "INSERT INTO actor (actorid,firstName, lastName,dateOfBirth)"
			+ " VALUES (?,?,?,?);";
	
	String readTable = "select * from actor";
	String readActor = "select * from actor where actorid = ?";
	String updateActor = "update actor set firstName = ?, lastName = ?,dateOfBirth = ? where actorid = ?";
	String deleteActor = "delete from actor where actorid = ?";
	
	public ActorManager()
	{
		try {
			Context qwerty = new InitialContext();
			das = (DataSource) qwerty.lookup("java:comp/env/jdbc/dbassign4");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean  createActor(Actor u)
	{
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(createActor);
			statement.setString(1, u.getTheactorid());
			statement.setString(2, u.getThefirstName());
			statement.setString(3, u.getThelastName());
			statement.setString(4, u.getThedateOfBirth());
			statement.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Actor> readAllActors()
	{
		List<Actor> r = new ArrayList<Actor>();
		try
		{
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(readTable);
			ResultSet results = statement.executeQuery();
			while(results.next()) 
			{
				Actor u1 = new Actor (
						
						results.getString("actorid"),
						results.getString("firstName"),
						results.getString("lastName"),
						results.getString("dateOfBirth")
						);
				r.add(u1);
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return r;
	}
	
	public Actor readActor(String uname)
	{
		Actor u1 = new Actor();
		try{
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(readActor);
			statement.setString(1, uname);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				u1 = new Actor (
						
						results.getString("actorid"),
						
						results.getString("firstName"),
						results.getString("lastName"),
						
						results.getString("dateOfBirth")
						);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u1;
	}
	
	public void updateActor(String uname, Actor u)
	{
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateActor);
			
			statement.setString(1, u.getThefirstName());
			
			statement.setString(2, u.getThelastName());
			
			statement.setString(3, u.getThedateOfBirth());
			
			statement.setString(4, uname);
			
			statement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteActor(String uname){
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteActor);
			statement.setString(1, uname);	
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
