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
		System.out.println("in RetextManagerServlet, doGet pathInfo = " + pathInfo);
		try {
	// this will need to be changed eventually to an if-else statement
			
			if (pathInfo == null || "".equals(pathInfo)) {
				System.out.println("going to welcome()");
				welcome(request, response);
			} else if (pathInfo.equals("/*") ){
				System.out.println("going to welcome()");
				welcome(request, response);
			} else if (pathInfo.equals("/browse") ){
				System.out.println("going to browseBooks()");
				browseBooks(request, response);
			}
	//		else if (pathInfo.equals("/titleLocated") ){
	//			System.out.println("going to displayTitle()");
	//			displayTitle(request, response);
	//		}
			
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
				 request.getRequestDispatcher("/WEB-INF/retext-welcome.jsp");
		 dispatcher.forward(request, response);
	} // end welcome
	 
	// pulls up the browse screen so that user can see if the book he/she wants is avaiable
	// at his/her school
	private void browseBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// find some books to sell
		System.out.println("Inside browse.");
		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retext-browse.jsp");
		dispatcher.forward(request, response);
		
	} // end browseBooks
	
	// displays all of the copies of the requested title available at user's school
	private void displayTitle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("Inside displayTitle.");
		UserInventoryDAO inventoryDAO = new UserInventoryDAO();
		String test = "test 123";
		
		List<DisplayUserInventory> titleList = inventoryDAO.listMyBooks();
		
		System.out.println("\n In retextManagerServlet.displayTitle");
		for (DisplayUserInventory i: titleList) {
			
			System.out.println("id = " + i.getId() + " Title = " + i.getTitle() );
			System.out.println("author = " + i.getAuthor() + " edition = " + i.getEdition() );
			System.out.println("isbn = " + i.getIsbn() + " price = " + i.getPrice() );
		}
		request.setAttribute("titleList", titleList);
		request.setAttribute("test", test);
		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retext-title-located.jsp");
		dispatcher.forward(request, response);
		
	}

	
} // end RetextManagerServlet
