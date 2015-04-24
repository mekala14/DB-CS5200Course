package classes.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.dao.UserDAO;
import classes.users.User;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
		 
	        String f= request.getParameter("fname");
	        String l = request.getParameter("lname");
	        String u= request.getParameter("uname");
	        String p = request.getParameter("pwd");
	        String e= request.getParameter("email");
	        String d = request.getParameter("db");
	        String c= request.getParameter("contact");
	        UserDAO reg = new UserDAO();
	        
	        boolean isEmpty = reg.isUserPresent(u);
	        
	       
	            
	        	if(isEmpty){
	        	
               
                // redirect to success page
                response.sendRedirect("Registration.jsp"); 
	        } else {
	            // redirecting to failure page
	        	
		        User us = new User(u, p,f, l ,e,d,c);
		        us.setRole("user");
		        us.setValidate(1);
		        User z = reg.createUser(us);
	            response.sendRedirect("Home.jsp");
	        }
	}

	
	}


