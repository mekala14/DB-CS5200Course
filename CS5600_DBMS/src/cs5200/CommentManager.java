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

import cs5200.Comment;

public class CommentManager {
DataSource das;
	
	
	String createComment = "INSERT INTO comment ( username, movieid, comment, date)"
			+ " VALUES (?,?,?,?);";
	
	String readTable = "select * from comment";
	String readUser = "select * from comment where username = ?";
	String readMovie = "select * from comment where movieid = ?";
	String readId = "select * from comment where id = ?";
	String updateComment = "update comment set username = ?, movieid = ?, comment = ?, date = ? where id = ?";
	String deleteComment = "delete from comment where id = ?";
	
	public CommentManager()
	{
		try {
			Context qwerty = new InitialContext();
			das = (DataSource) qwerty.lookup("java:comp/env/jdbc/dbassign4");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean  createComment(Comment u)
	{
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(createComment);
			statement.setString(1, u.getTheusername());
			statement.setString(2, u.getThemovieid());
			statement.setString(3, u.getThecomment());
			statement.setString(4, u.getThedate());
			statement.execute();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Comment> readAllComments()
	{
		List<Comment> u = new ArrayList<Comment>();
		try
		{
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(readTable);
			ResultSet results = statement.executeQuery();
			while(results.next()) 
			{
				Comment u1 = new Comment (
						results.getInt("id"),
						results.getString("username"),
						results.getString("movieid"),
						results.getString("comment"),
						results.getString("date")
						);
				u.add(u1);
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u;
	}
	
	public List<Comment> readCommentId(int id)
	{
		List<Comment> u1 = new ArrayList<Comment>();
		try{
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(readId);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				Comment u2 = new Comment (
						
						results.getInt("id"),
						results.getString("username"),
						results.getString("movieid"),
						results.getString("comment"),
						results.getString("date")
						);
				u1.add(u2);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u1;
	}

	public List<Comment> readCommentMovie(String id)
	{
		List<Comment> u1 = new ArrayList<Comment>();
		try{
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(readMovie);
			statement.setString(1, id);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				Comment u2 = new Comment (
						
						results.getInt("id"),
						results.getString("username"),
						results.getString("movieid"),
						results.getString("comment"),
						results.getString("date")
						);
				u1.add(u2);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u1;
	}	
	
	public List<Comment> readCommentUser(String name)
	{
		List<Comment> u1 = new ArrayList<Comment>();
		try{
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(readUser);
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			if(results.next())
			{
				Comment u2 = new Comment (
						
						results.getInt("id"),
						results.getString("username"),
						results.getString("movieid"),
						results.getString("comment"),
						results.getString("date")
						);
				u1.add(u2);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return u1;
	}
	
	public void updateComment(int id, Comment u)
	{
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateComment);
			
			statement.setString(1, u.getTheusername());
			statement.setString(2, u.getThemovieid());
			statement.setString(3, u.getThecomment());
			statement.setString(4, u.getThedate());
			statement.setInt(5, id);
			
			
			statement.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteComment(int id){
		try {
			Connection connection = das.getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteComment);
			statement.setInt(1, id);	
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
