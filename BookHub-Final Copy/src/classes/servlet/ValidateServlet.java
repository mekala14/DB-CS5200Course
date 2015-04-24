package classes.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.dao.*;
import classes.users.*;

/**
 * Servlet implementation class ValidateServlet
 */
@WebServlet("/ValidateServlet")
public class ValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String cuname = String.valueOf(request.getParameter("cuname"));
		 String value = String.valueOf(request.getParameter("val"));
		 String data ="";
			 System.out.println(cuname +"thi is the admin..............");
			
			 UserDAO bd = new UserDAO();
			 User b = new User();
			 b = bd.readUserByUname(cuname);
			 if(value.equals("1"))
			 {
				 b.setValidate(0);
				 bd.updateRegUsers(b);
				 data = "Enable";
			 }
			 else
			 {
				 b.setValidate(1);
				 bd.updateRegUsers(b);
				 data = "Disable";
			 }
			 
			 
			 
			 
			
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
