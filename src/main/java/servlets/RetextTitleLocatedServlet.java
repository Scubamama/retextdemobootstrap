package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.SchoolDAO;
import database.TitleLocatedDAO;
import database.UserInventoryDAO;
import database.UsersDAO;
import model2.AUser;
import model2.DisplayUserInventory;
import model2.School;
import model2.TitleLocated;

/**
 * Servlet implementation class RetextTitleLocatedServlet grabs titles that have
 * the given isbn and displays them
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		displayTitle(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		displayTitle(request, response);
	}

	// displays all of the copies of the requested title available at user's
	// school
	private void displayTitle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// read all parameters from jsp
		String isbn = request.getParameter("isbn");
		String school = request.getParameter("school");
		String campus = request.getParameter("campus");
		String nickName = request.getParameter("nickName");
		
		TitleLocatedDAO titleDAO = new TitleLocatedDAO();
		String title = "";

		HttpSession session = request.getSession(false);

		isbn = request.getParameter("isbn");
		school = request.getParameter("school");
		
		List<TitleLocated> titleList = null;
		// see if they are logged in if not leave school box empty
		// if so put school name, campus, and nickname in boxes - get school names from user
		AUser theUser = new AUser();
		try {

			if (session.getAttribute("currUserId") != null) { // they are already signed in
				// get the user's school info
				UsersDAO aUserDAO = new UsersDAO();
				theUser = aUserDAO.get((int)session.getAttribute("currUserId"));
				school = theUser.getUserSchool();
				campus = theUser.getUserCampus();
				// get nickName from school obj
				SchoolDAO schoolDAO = new SchoolDAO();
				School s = new School();
	
				s = schoolDAO.get(school, campus);
				nickName = s.getNickName();
			}
			request.setAttribute("school",school);
			request.setAttribute("campus",campus);
			request.setAttribute("nickName",nickName);

			if (school == null) // they entered a nickname only
				titleList = titleDAO.findAvailableBooks(isbn,school,campus,nickName);
			else 
				titleList = titleDAO.findAvailableBooks(isbn, nickName);
			title = titleDAO.getTitle(isbn);

			if (titleList.isEmpty()) { // no titles found
				// String message = "A title with that isbn was not found.";
				request.setAttribute("message", "A title with that isbn was not found.");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextNotFound.jsp");
				dispatcher.forward(request, response);

			}
			request.setAttribute("titleList", titleList);
			request.setAttribute("isbn", isbn);
			request.setAttribute("title", title);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextTitleLocated.jsp");
			dispatcher.forward(request, response);
		} // end try

		catch (Exception e) {
			throw new RuntimeException(e);
		}

	} // end displayTitle

} // end class RetextTitleLocatedServlet
