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
//import database.UserInventoryDAO;
import database.UsersDAO;
import model2.AUser;
//import model2.DisplayUserInventory;
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

	private void displayTitle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String isbn = request.getParameter("isbn");
		String school = request.getParameter("school");
		String campus = request.getParameter("campus");

		TitleLocatedDAO titleDAO = new TitleLocatedDAO();
		String title = "";

		HttpSession session = request.getSession(false);

		isbn = request.getParameter("isbn");
		school = request.getParameter("school");

		List<TitleLocated> titleList = null;
		// see if they are logged in if not leave school box empty
		// if so put school name, campus in boxes - get school names from user
		AUser theUser = new AUser();

		try {

			if (session != null) {
				if (session.getAttribute("currUserId") != null) { // they are already signed i
					UsersDAO aUserDAO = new UsersDAO();
					Integer userId = (Integer) session.getAttribute("currUserId");
					theUser = aUserDAO.get((int) userId);

					school = theUser.getUserSchool();
					campus = theUser.getUserCampus();
					// get nickName from school obj
					SchoolDAO schoolDAO = new SchoolDAO();
					School s = new School();

					s = schoolDAO.get(school, campus);
				}
			}

			request.setAttribute("school", school);
			request.setAttribute("campus", campus);

			titleList = titleDAO.findAvailableBooks(isbn, school, campus);
			title = titleDAO.getTitle(isbn);

			if (titleList.isEmpty()) { // no titles found
				request.setAttribute("message", "A title with that isbn was not found at this campus.");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextNotFound.jsp");
				dispatcher.forward(request, response);

			} else {

				request.setAttribute("titleList", titleList);
				request.setAttribute("isbn", isbn);
				request.setAttribute("title", title);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextTitleLocated.jsp");
				dispatcher.forward(request, response);
			}
		} // end try

		catch (Exception e) {
			throw new RuntimeException(e);
		}

	} // end displayTitle

} // end class RetextTitleLocatedServlet
