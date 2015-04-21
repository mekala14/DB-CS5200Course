package classes.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.dao.*;
import classes.users.*;
/**
 * Servlet implementation class AddBook
 */
@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		HttpSession session = request.getSession(true);
		bookDAO bd = new bookDAO();
    	book b = new book();
    	b = bd.getBookByISBN(Integer.parseInt(request.getParameter("boname")));
    	UserDAO y = new UserDAO();
		User ui = new User();
		ui = y.readUserByUname((String)session.getAttribute("uname"));
		shelfDAO sd = new shelfDAO();
		shelf s = new shelf();
		List <shelf> su = ui.getShlf();
		Boolean toggle = false;
		for (shelf temp: su){
			if(temp.getBoks().getId() != (b.getId())){
				
				
			}
			else{
				toggle = true;
			}
		}
			if(toggle)
			{
				response.sendRedirect("restricted/BookStack.jsp?usname="+ui.getUsername()+""); 
			}
		
			else{
				sd.createShelf(s, ui, b);
			
				response.sendRedirect("restricted/BookStack.jsp?usname="+ui.getUsername()+"");
			
		}
			
		
		
	}

}
