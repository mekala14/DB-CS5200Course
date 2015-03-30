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

import cs5200.User;

public class UserManager {
DataSource das;
	
	
	String createUser = "INSERT INTO user (username, password, firstName, lastName, email, dateOfBirth)"
			+ " VALUES (?,?,?,?,?,?);";
	
	String readTable = "select * from user";
	String readUser = "select * from user where username = ?";
	String updateUser = "update user set password = ?, firstName = ?, lastName = ?, email = ?, dateOfBirth = ? where username = ?";
	String deleteUser = "delete from user where username = ?";
	
	public UserManager()
	{
		try {
			Context qwerty = new InitialContext();
			das = (DataSource) qwerty.lookup("java:comp/env/jdbc/dbassign4");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean  createUser(User u)
	{
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(createUser);
			statement.setString(1, u.getTheusername());
			statement.setString(2, u.getThepassword());
			statement.setString(3, u.getThefirstName());
			statement.setString(4, u.getThelastName());
			statement.setString(5, u.getTheemail());
			statement.setString(6, u.getThedateOfBirth());
			statement.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<User> readAllUsers()
	{
		List<User> u = new ArrayList<User>();
		try
		{
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(readTable);
			ResultSet results = statement.executeQuery();
			while(results.next()) 
			{
				User u1 = new User (
						
						results.getString("username"),
						results.getString("password"),
						results.getString("firstName"),
						results.getString("lastName"),
						results.getString("email"),
						results.getString("dateOfBirth")
						);
				u.add(u1);
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
	}
	
	public User readUser(String uname)
	{
		User u1 = new User();
		try{
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(readUser);
			statement.setString(1, uname);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				u1 = new User (
						
						results.getString("username"),
						results.getString("password"),
						results.getString("firstName"),
						results.getString("lastName"),
						results.getString("email"),
						results.getString("dateOfBirth")
						);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u1;
	}
	
	public void updateUser(String uname, User u)
	{
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateUser);
			
			statement.setString(1, u.getThepassword());
			statement.setString(2, u.getThefirstName());
			statement.setString(3, u.getThelastName());
			statement.setString(4, u.getTheemail());
			statement.setString(5, u.getThedateOfBirth());
			statement.setString(6, uname);
			
			statement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteUser(String uname){
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteUser);
			statement.setString(1, uname);	
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
