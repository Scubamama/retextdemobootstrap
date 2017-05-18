package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.UserInventoryDAO;
import model2.DisplayUserInventory;

/**
 * Servlet implementation class RetextManagerServlet
 */
public class RetextManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetextManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 		
		throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		
		try {
			
			if (pathInfo == null || "".equals(pathInfo)) {
				welcome(request, response);
			} else if (pathInfo.equals("/*") ){
				welcome(request, response);
			} else if (pathInfo.equals("/browse") ){
				browseBooks(request, response);
			}
			
		}
		finally {
			
		} // end finally
	} // end doGet


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	} // end doPost

	
	// pulls up the welcome page as the first page the user sees when the app is opened
	private void welcome(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// just show the welcome page retext-welcome.jsp
		 RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextWelcome.jsp");
		 dispatcher.forward(request, response);
	} // end welcome
	 
	// pulls up the browse screen so that user can see if the book he/she wants is avaiable
	// at his/her school
	private void browseBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// find some books to sell
		
		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextBrowse.jsp");
		dispatcher.forward(request, response);
		
	} // end browseBooks
	
	// displays all of the copies of the requested title available at user's school
	private void displayTitle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		UserInventoryDAO inventoryDAO = new UserInventoryDAO();
			
		List<DisplayUserInventory> titleList = inventoryDAO.listMyBooks();
		
		request.setAttribute("titleList", titleList);
		
		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextTitleLocated.jsp");
		dispatcher.forward(request, response);
		
	}

	
} // end RetextManagerServlet
