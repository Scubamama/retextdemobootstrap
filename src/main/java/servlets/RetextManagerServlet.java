package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import static java.lang.System.out;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.SchoolDAO;
import database.UserInventoryDAO;
import database.UsersDAO;
import model2.AUser;
import model2.DisplayUserInventory;
import model2.School;

/**
 * Servlet implementation class RetextManagerServlet This is the main driver
 * servlet where everything starts
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();

		try {

			if (pathInfo == null || "".equals(pathInfo)) {
				welcome(request, response);
			} else if (pathInfo.equals("/*")) {
				welcome(request, response);
			} else if (pathInfo.equals("/browse")) {
				browseBooks(request, response);
			}

		} finally {
			// compiler is requiring this.
		} // end finally
	} // end doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	} // end doPost

	// pulls up the welcome page as the first page the user sees when the app is
	// opened
	private void welcome(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// just show the welcome page retext-welcome.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextWelcome.jsp");
		dispatcher.forward(request, response);
	} // end welcome

	// pulls up the browse screen so that user can see if the book he/she wants
	// is avaiable
	// at his/her school
	private void browseBooks(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// find some books to buy
//	System.out.println("ManagerServlet - browseBooks");

		String campus = "";
		String nickName = "";
		String school = "";

		HttpSession session = request.getSession(false);

		AUser theUser = new AUser();
		try {
			if (session != null) {
//	System.out.println("session != null");
				if (session.getAttribute("currUserId") != null) { // they are already signed in
//	System.out.println("session.getAttribute(\"currUserId\") != null");					
					// get the user's school info
					UsersDAO aUserDAO = new UsersDAO();
					theUser = aUserDAO.get((int)session.getAttribute("currUserId"));
					school = theUser.getUserSchool();
					campus = theUser.getUserCampus();   // this is null
					// get nickName from school obj
					SchoolDAO schoolDAO = new SchoolDAO();
					School s = new School();
		
					s = schoolDAO.get(school, campus);

					nickName = s.getNickName();
					
				} // end if (session.getAttribute("currUserId") != null)
			} // end if (session == null)

//		System.out.println("school" + school);		
//		System.out.println("campus" + campus);		
//		System.out.println("nickName" + nickName);		

			request.setAttribute("school",school);
			request.setAttribute("campus",campus);
			request.setAttribute("nickName",nickName);
//	System.out.println("after setAttributes");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextBrowse.jsp");
			dispatcher.forward(request, response);

		} // end try

		catch (Exception e) {
			throw new RuntimeException(e);
		}


	} // end browseBooks

} // end RetextManagerServlet
