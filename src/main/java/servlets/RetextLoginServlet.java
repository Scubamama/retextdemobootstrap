package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UserInventoryDAO;
import model2.DisplayUserInventory;

/**
 * Servlet implementation class RetextTitleLocatedServlet
 */
public class RetextLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetextLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside RetextLoginServlet - doGet.");
		loginForm(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside RetextLoginServlet - doPost.");
		login(request, response);
	}

	// displays all of the copies of the requested title available at user's school
	private void loginForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("Inside loginForm.");
//		UserInventoryDAO inventoryDAO = new UserInventoryDAO();
		String test = "test 123";
		
	//	List<DisplayUserInventory> titleList = inventoryDAO.listMyBooks();
		
	//	request.setAttribute("titleList", titleList);
		request.setAttribute("test", test);
		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextLoginForm.jsp");
		dispatcher.forward(request, response);
		
	} // end loginForm


	private void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("Inside login.");
		// gather info from form and create a session for user
		
		// display new page 
		request.setAttribute("theUser",request.getParameter("userName") );
		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextUserLoggedIn.jsp");
		dispatcher.forward(request, response);
	} // end login
	
} // end class
