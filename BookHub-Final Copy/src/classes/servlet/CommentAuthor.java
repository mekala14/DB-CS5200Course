package classes.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.dao.*;

import classes.users.*;


/**
 * Servlet implementation class CommentAuthor
 */
@WebServlet("/CommentAuthor")
public class CommentAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentAuthor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("You are in comment Author servlet");
		HttpSession session = request.getSession(true); 
		 String cuname = request.getParameter("comm");
		 String boid = request.getParameter("id");
		 String ouname = (String)session.getAttribute("uname");
		 
		 UserDAO ud = new UserDAO();
		 User ou = new User();
		// User cu = new User();
		 ou = ud.readUserByUname(ouname);
		// ou = ud.readUserByUname("vishnu");
		 authorDAO sd = new authorDAO();
		 author rl = sd.getAuthorByGoodreadsId(Integer.parseInt(boid));
		 comment_authorDAO cd = new comment_authorDAO();
		 comment_author c = new comment_author(0,String.valueOf(System.currentTimeMillis()),cuname);
		 cd.createComment(c, rl, ou); 
		
		 
		 
		String data = "Done";
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(data);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
