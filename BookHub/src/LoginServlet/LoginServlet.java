package LoginServlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.dao.UserDAO;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String userId = request.getParameter("uname");
	        String password = request.getParameter("pwd");
	        //String searchQuery = "select * from users where username='" + userId
	         //       + "' AND password='" + password + "'";
	        
	        try {
	        	   UserDAO reg = new UserDAO();
	               boolean isEmpty = reg.isValidUser(userId, password);
	            if (!isEmpty) {
	                // redirect to error page
	                response.sendRedirect("LoginFailure.jsp");
	            } 
	            else if (isEmpty) {
	                // fetch the session from request, create new session if session
	                // is not present in the request
	                HttpSession session = request.getSession(true); 
	                session.setAttribute("FirstName", reg.readUserByUname("userId").getFirst_name());
	                session.setAttribute("LastName", reg.readUserByUname("userId").getLast_name());
	                // redirect to success page
	                response.sendRedirect("FindBooks.jsp"); 
	            }
	        } 
	        catch (Exception e) {
	            System.out.println("SQLException occured: " + e.getMessage());
	            e.printStackTrace();
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
