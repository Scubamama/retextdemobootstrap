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
public class RetextTitleLocatedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetextTitleLocatedServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside RetextTitleLocatedServlet - doGet.");
		displayTitle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

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

	
	
}
