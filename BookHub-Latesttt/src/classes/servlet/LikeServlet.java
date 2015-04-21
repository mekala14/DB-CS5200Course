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
 * Servlet implementation class LikeServlet
 */
@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 int cuname = Integer.parseInt(request.getParameter("cuname"));
		// int nolikes = Integer.parseInt(request.getParameter("lik"));
		 System.out.println(cuname +"thi is the id.........");
		 int nolikes= 0;
		 bookDAO bd = new bookDAO();
		 book b = new book();
		 b = bd.getBookById(cuname);
		 nolikes = b.getLikes();
		 //System.out.println(nolikes+"thi is no of likes.........");
		 nolikes = nolikes + 1;
		 System.out.println(nolikes+"This is after.............");
		 b.setLikes(nolikes);
		 bd.updateBook(cuname, b);
		 
		 
		 
		 
		String data = String.valueOf(nolikes);
		// Set standard HTTP/1.1 no-cache headers.
		//response.setHeader("Cache-Control", "private, no-store, no-cache, must-revalidate");
		 response.setHeader("cache", "no-cache");
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
