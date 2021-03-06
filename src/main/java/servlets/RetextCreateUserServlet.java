package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.UsersDAO;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import model2.AUser;

/**
 * Servlet implementation class RetextCreateUserServlet
 */
public class RetextCreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RetextCreateUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		gatherNewUserInfo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		createNewUser(request, response);
	}

	// displays all of the copies of the requested title available at user's
	// school
	private void gatherNewUserInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// calls up the form that the user fills in their data in
		// (retextCreateUser.jsp)

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextCreateUser.jsp");
		dispatcher.forward(request, response);

	} // end gatherNewUserInfo()

	private void createNewUser(HttpServletRequest request, HttpServletResponse response) {
		// takes the info from gatherNewUserInfo() and stores it in the database

		try {
			UsersDAO aUserDAO = new UsersDAO();
			String warning = "";
			String uCard = "";
			uCard = request.getParameter("takeCardsYN");

			String currEmail = request.getParameter("email");
			String currUserName = request.getParameter("userName");
			String currSchool = request.getParameter("schoolName");
			String curCampus = request.getParameter("campus");
			if (aUserDAO.checkUserNameUsed(currUserName) != 0){
				warning = "We are sorry, That User Name is already being used. Please try again.";
				request.setAttribute("warning", warning);
				request.setAttribute("email", currEmail);
				request.setAttribute("userName", currUserName);
				request.setAttribute("schoolName", currSchool);
				request.setAttribute("campus", curCampus);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextCreateUser.jsp");
				dispatcher.forward(request, response);

			}
			else if (aUserDAO.checkEmailUsed(currEmail) != 0){
				warning = "We are sorry, That Email is already being used. Please try again.";
				request.setAttribute("warning", warning);
				request.setAttribute("email", currEmail);
				request.setAttribute("userName", currUserName);
				request.setAttribute("schoolName", currSchool);
				request.setAttribute("campus", curCampus);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextCreateUser.jsp");
				dispatcher.forward(request, response);

			}
			else {
				// encrypt password
				// Create instance
				Argon2 argon2 = Argon2Factory.create();
				String hash;

				// Read password from user
				char[] password = request.getParameter("password").toCharArray();

				try {
					hash = argon2.hash(2, 65536, 1, password);
//				System.out.println("hash = " + hash);

				} finally {
					// Wipe confidential data
					argon2.wipeArray(password);
				}

//			String encryptedPassword = new String(password.toString());
				String encryptedPassword = hash;

//			String currUserName = request.getParameter("userName");
				int card = 0; // default user does not take cards
				if (uCard.equals("y"))
					card = 1;
				AUser newU = new AUser(request.getParameter("email"), currUserName, encryptedPassword, card,
						request.getParameter("schoolName"), request.getParameter("campus"));

				aUserDAO.save(newU); // put new user in db
				newU = aUserDAO.get(currUserName); // get the db id from new entry
				int currUserId = newU.getId();
				// create a session

				HttpSession session = request.getSession();
				session.setAttribute("currUserId", currUserId);

				// display a page saying that the user has been created
				request.setAttribute("newUser", request.getParameter("userName"));
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextUserCreated.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception exc) {
			throw new RuntimeException(exc);

		} finally {
		}
	} // end createUser()


	private void verifyNewUserInfo() {

	}


} // end class RetextCreateUserServlet
