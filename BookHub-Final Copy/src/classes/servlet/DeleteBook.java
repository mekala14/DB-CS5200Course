package classes.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.dao.UserDAO;
import classes.dao.bookDAO;
import classes.dao.shelfDAO;
import classes.users.User;
import classes.users.author;
import classes.users.book;
import classes.users.shelf;

/**
 * Servlet implementation class DeleteBook
 */
@WebServlet("/DeleteBook")
public class DeleteBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBook() {
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
		
		
			List <shelf> f = ui.getShlf();
			int count = 0;
			for (shelf e : f){
				if(e.getBoks().getId() == b.getId()){
					break;
				}
				else{
					count++;
				}
			}
			f.remove(count);
			ui.setShlf(f);
			y.updateRegUsers(ui);
			response.sendRedirect("restricted/BookStack.jsp?usname="+ui.getUsername()+"");
	}
		
	}

