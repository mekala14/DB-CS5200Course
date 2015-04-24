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
 * Servlet implementation class AddAuthor
 */
@WebServlet("/AddAuthor")
public class AddAuthor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAuthor() {
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
		authorDAO bd = new authorDAO();
    	author b = new author();
    	b = bd.getAuthorByGoodreadsId(Integer.parseInt(request.getParameter("aoname")));
    	UserDAO y = new UserDAO();
		User ui = new User();
		ui = y.readUserByUname((String)session.getAttribute("uname"));
		
		List <author>  su  = ui.getAuthors();
		
		Boolean toggle = false;
		for (author temp: su){
			if(temp.getId() != (b.getId())){
				
				
			}
			else{
				toggle = true;
			}
		}
			if(toggle)
			{
				response.sendRedirect("restricted/AuthorStack.jsp?usname="+ui.getUsername()+""); 
			}
		
			else{
				su.add(b);
				ui.setAuthors(su);
				y.updateRegUsers(ui);
				response.sendRedirect("restricted/AuthorStack.jsp?usname="+ui.getUsername()+"");
			
		}
			
		
		
	}

	
	}


