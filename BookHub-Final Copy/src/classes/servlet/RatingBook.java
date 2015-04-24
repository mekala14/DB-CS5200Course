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
 * Servlet implementation class RatingBook
 */
@WebServlet("/RatingBook")
public class RatingBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatingBook() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("You are in comment book servlet");
		HttpSession session = request.getSession(true); 
		 String cuname = request.getParameter("ratt");
		 String boid = request.getParameter("id");
		 String ouname = (String)session.getAttribute("uname");
		 
		 UserDAO ud = new UserDAO();
		 User ou = new User();
		// User cu = new User();
		 ou = ud.readUserByUname(ouname);
		// ou = ud.readUserByUname("vishnu");
		 bookDAO sd = new bookDAO();
		 book rl = new book();
		  rl = sd.getBookByISBN(Integer.parseInt(boid));
		 ratingDAO cd = new ratingDAO();
		 rating c = new rating(0,Integer.parseInt(cuname));
		 cd.createRating(c, rl, ou); 
		
		 
		 
		String data = String.valueOf(rl.getAvg_rating());
		System.out.println("The new rating is ......"+String.valueOf(rl.getAvg_rating()));
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
