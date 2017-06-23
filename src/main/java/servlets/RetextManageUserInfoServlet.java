package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DisplayMessagesDAO;
import database.ManageListingsDAO;
import database.MessagesDAO;
import database.UsersDAO;
import model2.AUser;
import model2.DisplayMessages;
import model2.DisplayUserListings;

/**
 * Servlet implementation class RetextTitleLocatedServlet
 */
public class RetextManageUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetextManageUserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside RetextManageUserInfoServlet - doGet.");
		// this feature is currently unavailable
		
//		featureUnavailable(request, response);
		// code from messages servlet
		String pathInfo = request.getPathInfo();
		System.out.println("pathInfo: " + pathInfo);
		
		if (pathInfo == null || "".equals(pathInfo)) {
			manageUserInfo(request, response); // 
		} else if (pathInfo.equals("/listings")) {
			viewListings(request, response); // 
		}  else if (pathInfo.equals("/profile")) {
			viewProfile(request, response); // 
		}  else if (pathInfo.equals("/updateProfile")) {
			viewProfile(request, response); // 
		}  else if (pathInfo.equals("/deleteProfile")) {
			confirmDeleteProfile(request, response); // 
		} 
	
		
		// edit a listing for this user
//			else if (pathInfo.equals("/editListing")) {
//				editListing(request, response);
//		} else if (pathInfo.equals("/deleteListing")) {
//			//delete a listing for this user
//			confirmDeleteListing(request, response);
//		}
	 		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside RetextManageUserInfoServlet - doPost.");
		//send messages
//		createNewUser(request, response);
		
		System.out.println("Inside RetextManageUserInfoServlet - doPost.");
		String pathInfo = request.getPathInfo();
		System.out.println("pathInfo: " + pathInfo);
		
		if (pathInfo == null || "".equals(pathInfo)) {
			manageUserInfo(request, response); // 
		} else if (pathInfo.equals("/updateProfile")) {
			updateProfile(request, response);
			// "delete" the user profile actually archives it
		} else if (pathInfo.equals("/archiveProfile")) {
			archiveProfile(request, response);
		}
		
//		else if (pathInfo.equals("/updateProfile")) {
			//send messages
//			updateProfile(request, response);
//		} 
//			else if (pathInfo.equals("/deleteListingYes")) {
//			deleteListing(request, response);
//		}
	
	} // end doPost


	// displays all of the copies of the requested title available at user's school
	private void manageUserInfo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// calls up the form that the user fills in their data in (retextCreateUser.jsp)
		System.out.println("\n In RetextManageUserInfoServlet - manageUserInfo");

	//	request.setAttribute("titleList", titleList);
	//	request.setAttribute("test", test);
		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextManageUserInfo.jsp");
		dispatcher.forward(request, response);
		
	} // end featureUnavailable()
	
	// displays all of the copies of the requested title available at user's school
	private void featureUnavailable(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// calls up the form that the user fills in their data in (retextCreateUser.jsp)
		System.out.println("\n In RetextManageUserInfoServlet - featureUnavailable");

	//	request.setAttribute("titleList", titleList);
	//	request.setAttribute("test", test);
		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextFeatureUnavailable.jsp");
		dispatcher.forward(request, response);
		
	} // end featureUnavailable()
	
	// list all of this user's listings
	private void viewListings(HttpServletRequest request, HttpServletResponse response) {

//		System.out.println("\n In retextManageUserInfoServlet - list");
		
		int userId = 1;  // change later when sessions are in place
		
		ManageListingsDAO disListingsDAO = new ManageListingsDAO();
		// get all users messages
		List<DisplayUserListings> listingList = null;
		try {
			listingList = disListingsDAO.listMyBooks();
			request.setAttribute("listingList", listingList);

			RequestDispatcher dispatcher = 
					 request.getRequestDispatcher("/WEB-INF/retextViewListings.jsp");
			dispatcher.forward(request, response);   

		}
		catch (Exception exc) {
	//		e.printStackTrace();
			throw new RuntimeException(exc);

		}

		finally {}

	} // end viewListings()


	private void viewProfile(HttpServletRequest request, HttpServletResponse response) {
		// show the user their current info username, password, if they take cards and their school
		// and allow them to edit all of that except maybe username
		
	System.out.println("\n In RetextManageUserInfoServlet - viewProfile");

			int currUserId = 1;   // until sessions is in place
			UsersDAO aUserDAO = new UsersDAO();
			AUser thisUser = new AUser();
			try {
				thisUser = aUserDAO.get(currUserId);
				// display a page showing the user's current info and let them change it
				request.setAttribute("thisUser", thisUser);
				RequestDispatcher dispatcher = 
						 request.getRequestDispatcher("/WEB-INF/retextViewProfile.jsp");
				dispatcher.forward(request, response);

			}
			catch (Exception exc) {
		//		e.printStackTrace();
				throw new RuntimeException(exc);
			}
						
		} // end viewProfile

	private void updateProfile(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("\n In RetextManageUserInfoServlet - updateProfile");

		
	} // end updateProfile

	private void confirmDeleteProfile(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("\n In RetextManageUserInfoServlet - confirmDeleteProfile");
		
			int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("\n id: " + id);
			UsersDAO aUserDAO = new UsersDAO();
			AUser thisUser = new AUser();

			try {
				request.setAttribute("id", id);

				RequestDispatcher dispatcher = 
						 request.getRequestDispatcher("/WEB-INF/retextConfirmDeleteUser.jsp");

				dispatcher.forward(request, response);

			} //end try
			catch (Exception exc) {
				throw new RuntimeException(exc);
			}
		
	} // end confirmDeleteProfile

	private void archiveProfile(HttpServletRequest request, HttpServletResponse response) {
		//  "deletes" this user
		System.out.println("\n In RetextManageUserInfoServlet - archiveProfile");
		
		int id = Integer.parseInt(request.getParameter("id"));
		
	System.out.println("\n id: " + id);
		try {
			UsersDAO aUserDAO = new UsersDAO();
			AUser thisUser = new AUser();
			thisUser = aUserDAO.get(id);
			// set the archive field to true
			aUserDAO.delete(id); // archives the user
			String curUser = thisUser.getUserName();
	System.out.println("\n curUser: " + curUser);

			request.setAttribute("id", id);
			request.setAttribute("theUser", curUser);
			RequestDispatcher dispatcher = 
					 request.getRequestDispatcher("/WEB-INF/retextUserDeleted.jsp");

			dispatcher.forward(request, response);

		} //end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
	

		
	} // end archiveProfile

	
	private void createNewUser(HttpServletRequest request, HttpServletResponse response) {
		// takes the info from gatherNewUserInfo() and stores it in the database
		
		System.out.println("\n In retextMessagesServlet - createNewUser");
		UsersDAO aUserDAO = new UsersDAO();
		String uCard = "";
		uCard = request.getParameter("takeCards");
	//	AUser newU = new AUser(uEmail,uName,uPass,card,uSchool);
		
		System.out.println(" uCard = " + uCard);
		int card = 0;  // default user does not take cards
		if(uCard.equals("y")) card = 1;
		System.out.println(" card = " + card);
		AUser newU = new AUser(request.getParameter("email"),request.getParameter("userName"),
				request.getParameter("password"),card,request.getParameter("schoolName"));
		aUserDAO.save(newU);
		System.out.println("\n email = " + request.getParameter("email"));
		System.out.println(" userName = " + request.getParameter("userName"));
		System.out.println(" schoolName = " + request.getParameter("schoolName"));
		System.out.println(" schoolNickName = " + request.getParameter("schoolNickName"));
		System.out.println(" takeCards = " + request.getParameter("takeCards"));
		System.out.println(" password = " + request.getParameter("password") + "\n ");
		
		// list users to see if add was ok
		
		// display a page saying that the user has been created 
			request.setAttribute("newUser",request.getParameter("userName") );
		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextUserCreated.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {}
		finally {}
	} // end createNewUser()
	
	
} // end class RetextManageUserInfoServlet
