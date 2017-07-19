package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.UserInventoryDAO;
import database.UsersDAO;
import model2.AUser;
import model2.DisplayUserInventory;

/**
 * Servlet implementation class RetextLoginOutServlet
 */
public class RetextLoginOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RetextLoginOutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();

		if (pathInfo == null || "".equals(pathInfo)) {
			loginForm(request, response); //
		} else if (pathInfo.equals("/logOut")) {
			logout(request, response); //
		} else if (pathInfo.equals("/actions")) {
			userActions(request, response); //
		}

	} // end doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		login(request, response);
	} // end doPost

	// displays screen with some actions that the user can do
	private void userActions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextUserActions.jsp");
			dispatcher.forward(request, response);

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end userActions

	// displays a screen to input user info to login
	private void loginForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String isbn = "";
		if (request.getParameter("isbn") != null) {
			isbn = request.getParameter("isbn");
		}

		try {
			request.setAttribute("isbn", isbn);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextLoginForm.jsp");
			dispatcher.forward(request, response);

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end loginForm

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String warning = "";
		// gather info from form and create a session for user

		String currUserName = request.getParameter("userName");
		String currUserPassword = request.getParameter("password");
		String isbn = "";
		if (request.getParameter("isbn") != null)
			isbn = request.getParameter("isbn");

		UsersDAO aUserDAO = new UsersDAO();
		int currUserId = 0;
		try {
			currUserId = aUserDAO.checkLogin(currUserName, currUserPassword);

			request.setAttribute("currUserId", currUserId);
			if (currUserId == 0) { // invalid login go back to login form page
				warning = "User Name/Password combination is invalid. Please try again.";
				request.setAttribute("warning", warning);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextLoginForm.jsp");
				dispatcher.forward(request, response);
			} else {
				// set up a session to pass the user's db id around
				HttpSession session = request.getSession();
				session.setAttribute("currUserId", currUserId);
				// display user logged in page
				request.setAttribute("theUser", request.getParameter("userName"));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextUserLoggedIn.jsp");
				dispatcher.forward(request, response);
			}
		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end login

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		// log the user out and invalidate the session

		HttpSession session = request.getSession();
		session.invalidate();
		try {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextUserLoggedOut.jsp");
			dispatcher.forward(request, response);
		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
	}// end logout

} // end class
