package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.TitleLocatedDAO;
import database.UserInventoryDAO;
import model2.DisplayUserInventory;
import model2.TitleLocated;

/**
 * Servlet implementation class RetextTitleLocatedServlet
 * grabs titles that have the given isbn and displays them
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
		displayTitle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 
		displayTitle(request, response);
	}

	// displays all of the copies of the requested title available at user's school
	private void displayTitle(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		String isbn = request.getParameter("isbn");
		String school = request.getParameter("school");
		
		TitleLocatedDAO titleDAO = new TitleLocatedDAO();
		String title = "";
		
		isbn = request.getParameter("isbn");
		school = request.getParameter("school");
		List<TitleLocated> titleList = null;
		try {
			titleList = titleDAO.findAvailableBooks(isbn);
			title = titleDAO.getTitle(isbn);

			if (titleList.isEmpty()) {  // no titles found
				String message = "A title with that isbn was not found.";
				request.setAttribute("message", message);

				RequestDispatcher dispatcher = 
						 request.getRequestDispatcher("/WEB-INF/retextNotFound.jsp");
				dispatcher.forward(request, response);
	
			}
			request.setAttribute("titleList", titleList);
			request.setAttribute("isbn", isbn);
			request.setAttribute("title", title);
			RequestDispatcher dispatcher = 
					 request.getRequestDispatcher("/WEB-INF/retextTitleLocated.jsp");
			dispatcher.forward(request, response);
		}

		catch (Exception e) {
			throw new RuntimeException(e);
		}

	} // end displayTitle

} // end class RetextTitleLocatedServlet
