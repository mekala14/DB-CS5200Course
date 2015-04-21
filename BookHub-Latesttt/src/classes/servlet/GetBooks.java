package classes.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import classes.dao.*;
import classes.users.*;
/**
 * Servlet implementation class GetBooks
 */
@WebServlet("/GetBooks")
public class GetBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     *//*
    public GetBooks() {
        super();
        // TODO Auto-generated constructor stub
    }
*/
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String u = request.getParameter("bookname");
		 System.out.println("Ur testing is" + u);
		 bookDAO reg = new bookDAO();
		 List<book> l = reg.getSearchBooks(u);
		 
		 //List<book> l = reg.getBooks();
		 for(book x: l)
		 {
		 System.out.println("Books are" + x.getName());
		 }
		if(l.size() == 0){
		// request.setAttribute("bname", u);
		 //categorylist is an arraylist      contains object of class category
		}
		else
		{
			request.setAttribute("listq", l);
		}
		 getServletConfig().getServletContext().getRequestDispatcher("/FindBooks.jsp").forward(request,response);
	
	}

}
