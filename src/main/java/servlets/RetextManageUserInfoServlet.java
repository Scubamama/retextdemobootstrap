package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.DisplayMessagesDAO;
import database.ManageListingsDAO;
import database.MessagesDAO;
import database.TitleLocatedDAO;
import database.UserInventoryDAO;
import database.UsersDAO;
import model2.AUser;
import model2.BookTitles;
import model2.DisplayMessages;
import model2.DisplayUserListings;
import model2.UserInventory;

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

		String pathInfo = request.getPathInfo();
	System.out.println("pathInfo: " + pathInfo);
		HttpSession session = request.getSession(false);
		int currUserId = (int)session.getAttribute("currUserId");
	
	System.out.println("currUserId: " + currUserId);

		if (pathInfo == null || "".equals(pathInfo)) {
			manageUserInfo(request, response); // 
		} else if (pathInfo.equals("/listings")) {
			viewListings(request, response); // 
		}  else if (pathInfo.equals("/profile")) {
			viewProfile(request, response); // 
		}  else if (pathInfo.equals("/updateProfileForm")) {
			updateProfileForm(request, response); // gets the data to update a user
		}  else if (pathInfo.equals("/deleteProfile")) {
			confirmDeleteProfile(request, response); // asks if they really want to del
		}  else if (pathInfo.equals("/updateListingForm")) {
			updateListingForm(request, response); // gets the data to update a user
		}  else if (pathInfo.equals("/deleteListingConfirm")) {
			confirmDeleteListing(request, response); // asks if they really want to del
		}  else if (pathInfo.equals("/addListingForm")) {
			gatherListingInfo(request, response); // gets the data to update a user
		} 
		
	} // end doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside RetextManageUserInfoServlet - doPost.");
		
	System.out.println("Inside RetextManageUserInfoServlet - doPost.");
		String pathInfo = request.getPathInfo();
	System.out.println("pathInfo: " + pathInfo);
		
		if (pathInfo == null || "".equals(pathInfo)) {
			manageUserInfo(request, response); // screen to ask what they want to do
		} else if (pathInfo.equals("/updateProfile")) {
			updateProfile(request, response); // actually updates the db
			// "delete" the user profile actually archives it
		} else if (pathInfo.equals("/archiveProfile")) {
			archiveProfile(request, response);
		} else if (pathInfo.equals("/updateListing")) {
			updateListing(request, response); // actually updates the db
			// "delete" the user profile actually archives it
		} else if (pathInfo.equals("/deleteListing")) {
			deleteListing(request, response);
		} else if (pathInfo.equals("/addListing")) {
			addListing(request, response);
		}
			
	} // end doPost


	// displays all of the copies of the requested title available at user's school
	private void manageUserInfo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// calls up the form that the user fills in their data in (retextCreateUser.jsp)
		System.out.println("\n In RetextManageUserInfoServlet - manageUserInfo");

		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextManageUserInfo.jsp");
		dispatcher.forward(request, response);
		
	} // end featureUnavailable()
	
	// displays a screen telling user that a feature is not available yet
	private void featureUnavailable(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// calls up the form that the user fills in their data in (retextCreateUser.jsp)
		System.out.println("\n In RetextManageUserInfoServlet - featureUnavailable");

		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextFeatureUnavailable.jsp");
		dispatcher.forward(request, response);
		
	} // end featureUnavailable()
	
	// list all of this user's listings
	private void viewListings(HttpServletRequest request, HttpServletResponse response) {

//		System.out.println("\n In retextManageUserInfoServlet - list");
		
//		int userId = 1;  // change later when sessions are in place
		HttpSession session = request.getSession(false);
		int currUserId = (int)session.getAttribute("currUserId");
	
	System.out.println("currUserId: " + currUserId);
		
		ManageListingsDAO dispListingsDAO = new ManageListingsDAO();
		// get all users messages
		List<DisplayUserListings> listingList = null;
		try {
//			listingList = dispListingsDAO.listMyBooks();
			listingList = dispListingsDAO.listMyBooks(currUserId);

			request.setAttribute("listingList", listingList);

			RequestDispatcher dispatcher = 
					 request.getRequestDispatcher("/WEB-INF/retextViewListings.jsp");
			dispatcher.forward(request, response);   

		}
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

		finally {}

	} // end viewListings()


	private void viewProfile(HttpServletRequest request, HttpServletResponse response) {
		// show the user their current info username, password, if they take cards and their school
		// and allow them to edit all of that except maybe username
		
	System.out.println("\n In RetextManageUserInfoServlet - viewProfile");

	//		int currUserId = 1;   // until sessions is in place
			HttpSession session = request.getSession(false);
			int currUserId = (int)session.getAttribute("currUserId");
		
		System.out.println("currUserId: " + currUserId);

			
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

	private void updateProfileForm(HttpServletRequest request, HttpServletResponse response) {
		// gets the data to update a user
		System.out.println("\n In RetextManageUserInfoServlet - updateProfileForm");
		
	//	int id = Integer.parseInt(request.getParameter("id")); // is the user's id
		
		HttpSession session = request.getSession(false);
		int currUserId = (int)session.getAttribute("currUserId");
	
	System.out.println("currUserId: " + currUserId);

		String uCard = "";
//		uCard = request.getParameter("takeCards");
//	System.out.println(" uCard: " + uCard);


//	System.out.println("\n id: " + id);
		try {
			UsersDAO aUserDAO = new UsersDAO();
	System.out.println(" got new UsersDAO " );
//			int card = 0;  // default user does not take cards
//	System.out.println(" set card = 0 " );

//			if(uCard.equals("y")) card = 1;
//	System.out.println(" card = " + card);

			AUser thisUser = new AUser();
//			thisUser = aUserDAO.get(id);
			thisUser = aUserDAO.get(currUserId);

			if (thisUser.getTakeCards() == 0 ) thisUser.setTakeCardsYN("N");
			else thisUser.setTakeCardsYN("Y");
//			thisUser = aUserDAO.get(currUserId);
			// display a page showing the user's current info and let them change it
			request.setAttribute("thisUser", thisUser);
			
			// update the info the user input
	//		aUserDAO.delete(id); // archives the user

			request.setAttribute("currUserId", currUserId);
			request.setAttribute("theUser", thisUser);
			// this screen will display the current info and allow user to change it
			RequestDispatcher dispatcher = 
					 request.getRequestDispatcher("/WEB-INF/retextUpdateUser.jsp");

			dispatcher.forward(request, response);

		} //end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
			
	} // end updateProfileForm
	

	private void updateProfile(HttpServletRequest request, HttpServletResponse response) {
		//  takes the data from retextUpdateProfileForm and updates this user in the db
		System.out.println("\n In RetextManageUserInfoServlet - updateProfile");
		
//		int id = Integer.parseInt(request.getParameter("id"));
			
		HttpSession session = request.getSession(false);
		int currUserId = (int)session.getAttribute("currUserId");
	
	System.out.println("currUserId: " + currUserId);

//		int id = Integer.parseInt(request.getParameter("id"));

		String uCard = request.getParameter("takeCardsYN");

//	System.out.println("\n id: " + id);
		try {
			UsersDAO aUserDAO = new UsersDAO();
	//		AUser thisUser = new AUser();
	//		thisUser = aUserDAO.get(id);
		System.out.println(" uCard = " + uCard);
			int card = 0;  // default user does not take cards and the db field is int
			if(uCard.equals("y") || uCard.equals("Y")) card = 1;
		System.out.println(" card = " + card);

			AUser newU = new AUser(currUserId, request.getParameter("email"),request.getParameter("userName"),
					request.getParameter("password"),card,request.getParameter("schoolName"));
			
		System.out.println("\n email = " + request.getParameter("email"));
		System.out.println(" userName = " + request.getParameter("userName"));
		System.out.println(" schoolName = " + request.getParameter("schoolName"));
		System.out.println(" card = " + card);
		System.out.println(" password = " + request.getParameter("password") + "\n ");
		
			aUserDAO.save(newU);
//			aUserDAO.save(thisUser);

			String curUser = request.getParameter("userName");
			
	System.out.println("\n curUser: " + curUser);
			
			viewProfile(request, response);
		
//			request.setAttribute("userId", id);
//			request.setAttribute("theUser", curUser);
//			RequestDispatcher dispatcher = 
//					 request.getRequestDispatcher("/WEB-INF/retextUserProfileUpdated.jsp");
//
//			dispatcher.forward(request, response);

		} //end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
		
	} // end updateProfile

	private void confirmDeleteProfile(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("\n In RetextManageUserInfoServlet - confirmDeleteProfile");
		
//			int id = Integer.parseInt(request.getParameter("id"));
		
	HttpSession session = request.getSession(false);
	int currUserId = (int)session.getAttribute("currUserId");

System.out.println("currUserId: " + currUserId);

//		System.out.println("\n id: " + id);
			UsersDAO aUserDAO = new UsersDAO();
			AUser thisUser = new AUser();

			try {
				request.setAttribute("currUserId", currUserId);

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
		
//		int id = Integer.parseInt(request.getParameter("id"));
		
//	System.out.println("\n id: " + id);
	
		HttpSession session = request.getSession(false);
		int currUserId = (int)session.getAttribute("currUserId");
		
System.out.println("currUserId: " + currUserId);

	
		try {
			UsersDAO aUserDAO = new UsersDAO();
			AUser thisUser = new AUser();
			thisUser = aUserDAO.get(currUserId);
			// set the archive field to true
			aUserDAO.delete(currUserId); // archives the user
			String curUser = thisUser.getUserName();
	System.out.println("\n curUser: " + curUser);

			request.setAttribute("currUserId", currUserId);
			request.setAttribute("theUser", curUser);
			RequestDispatcher dispatcher = 
					 request.getRequestDispatcher("/WEB-INF/retextUserDeleted.jsp");

			dispatcher.forward(request, response);

		} //end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
	

		
	} // end archiveProfile


	private void updateListingForm(HttpServletRequest request, HttpServletResponse response) {
		// gets the data to update a user
		System.out.println("\n In RetextManageUserInfoServlet - updateProfileForm");
		
	//	int id = Integer.parseInt(request.getParameter("id")); // is the user's id
		
		HttpSession session = request.getSession(false);
		int currUserId = (int)session.getAttribute("currUserId");
	
	System.out.println("currUserId: " + currUserId);

		String uCard = "";
//		uCard = request.getParameter("takeCards");
//	System.out.println(" uCard: " + uCard);


//	System.out.println("\n id: " + id);
		try {
			UsersDAO aUserDAO = new UsersDAO();
	System.out.println(" got new UsersDAO " );
//			int card = 0;  // default user does not take cards
//	System.out.println(" set card = 0 " );

//			if(uCard.equals("y")) card = 1;
//	System.out.println(" card = " + card);

			AUser thisUser = new AUser();
//			thisUser = aUserDAO.get(id);
			thisUser = aUserDAO.get(currUserId);

			if (thisUser.getTakeCards() == 0 ) thisUser.setTakeCardsYN("N");
			else thisUser.setTakeCardsYN("Y");
//			thisUser = aUserDAO.get(currUserId);
			// display a page showing the user's current info and let them change it
			request.setAttribute("thisUser", thisUser);
			
			// update the info the user input
	//		aUserDAO.delete(id); // archives the user

			request.setAttribute("currUserId", currUserId);
			request.setAttribute("theUser", thisUser);
			// this screen will display the current info and allow user to change it
			RequestDispatcher dispatcher = 
					 request.getRequestDispatcher("/WEB-INF/retextUpdateUser.jsp");

			dispatcher.forward(request, response);

		} //end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
	
	} // end updateListingForm


	private void updateListing(HttpServletRequest request, HttpServletResponse response) {
		//  takes the data from retextUpdateProfileForm and updates this user in the db
		System.out.println("\n In RetextManageUserInfoServlet - updateProfile");
		
//		int id = Integer.parseInt(request.getParameter("id"));
			
		HttpSession session = request.getSession(false);
		int currUserId = (int)session.getAttribute("currUserId");
	
	System.out.println("currUserId: " + currUserId);

//		int id = Integer.parseInt(request.getParameter("id"));

		String uCard = request.getParameter("takeCardsYN");

//	System.out.println("\n id: " + id);
		try {
			UsersDAO aUserDAO = new UsersDAO();
	//		AUser thisUser = new AUser();
	//		thisUser = aUserDAO.get(id);
		System.out.println(" uCard = " + uCard);
			int card = 0;  // default user does not take cards and the db field is int
			if(uCard.equals("y") || uCard.equals("Y")) card = 1;
		System.out.println(" card = " + card);

			AUser newU = new AUser(currUserId, request.getParameter("email"),request.getParameter("userName"),
					request.getParameter("password"),card,request.getParameter("schoolName"));
			
		System.out.println("\n email = " + request.getParameter("email"));
		System.out.println(" userName = " + request.getParameter("userName"));
		System.out.println(" schoolName = " + request.getParameter("schoolName"));
		System.out.println(" card = " + card);
		System.out.println(" password = " + request.getParameter("password") + "\n ");
		
			aUserDAO.save(newU);
//			aUserDAO.save(thisUser);

			String curUser = request.getParameter("userName");
			
	System.out.println("\n curUser: " + curUser);
			
			viewProfile(request, response);
		
//			request.setAttribute("userId", id);
//			request.setAttribute("theUser", curUser);
//			RequestDispatcher dispatcher = 
//					 request.getRequestDispatcher("/WEB-INF/retextUserProfileUpdated.jsp");
//
//			dispatcher.forward(request, response);

		} //end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
		
	} // end updateListing

	
	private void confirmDeleteListing(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("\n In RetextManageUserInfoServlet - confirmDeleteProfile");
		
			int listingId = Integer.parseInt(request.getParameter("listingId"));
		
	HttpSession session = request.getSession(false);
	int currUserId = (int)session.getAttribute("currUserId");

System.out.println("currUserId: " + currUserId);

System.out.println("listingId: " + listingId);
//		System.out.println("\n id: " + id);
			UsersDAO aUserDAO = new UsersDAO();
			AUser thisUser = new AUser();

			try {
				request.setAttribute("currUserId", currUserId);
				request.setAttribute("listingId", listingId);

				RequestDispatcher dispatcher = 
						 request.getRequestDispatcher("/WEB-INF/retextConfirmDeleteListing.jsp");

				dispatcher.forward(request, response);

			} //end try
			catch (Exception exc) {
				throw new RuntimeException(exc);
			}
		
	} // end confirmDeleteListing

	
	private void deleteListing(HttpServletRequest request, HttpServletResponse response) {
		//  "deletes" this user
		System.out.println("\n In RetextManageUserInfoServlet - archiveProfile");
		// this is the id of the listing in the user_inventory table
		int listingId = Integer.parseInt(request.getParameter("listingId"));
		
//	System.out.println("\n id: " + id);
	
//		HttpSession session = request.getSession(false);
//		int currUserId = (int)session.getAttribute("currUserId");
		
//System.out.println("currUserId: " + currUserId);
	
		try {
			ManageListingsDAO listingsDAO = new ManageListingsDAO();
			// delete this listing
			listingsDAO.deleteListing(listingId);   // delete it from the db
			// show the user the list again
			viewListings(request, response);
			
//			UsersDAO aUserDAO = new UsersDAO();
//			AUser thisUser = new AUser();
//			thisUser = aUserDAO.get(currUserId);
			
//			thisListing = listingsDAO.get(listingId);
			// set the archive field to true
//			aUserDAO.delete(currUserId); // archives the user
//			String curUser = thisUser.getUserName();
//	System.out.println("\n curUser: " + curUser);

//			request.setAttribute("currUserId", currUserId);
//			request.setAttribute("theUser", curUser);
//			RequestDispatcher dispatcher = 
//					 request.getRequestDispatcher("/WEB-INF/retextListingDeleted.jsp");
//
//			dispatcher.forward(request, response);

		} //end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
	
	} // end deleteListing


	private void gatherListingInfo(HttpServletRequest request, HttpServletResponse response) {
		// gets the data to update a user
		System.out.println("\n In RetextManageUserInfoServlet - gatherListingInfo");
		
	//	int id = Integer.parseInt(request.getParameter("id")); // is the user's id
		
		HttpSession session = request.getSession(false);
		int currUserId = (int)session.getAttribute("currUserId");
	
	System.out.println("currUserId: " + currUserId);

		String uCard = "";

//	System.out.println("\n id: " + id);
		try {
//			UsersDAO aUserDAO = new UsersDAO();
//	System.out.println(" got new UsersDAO " );

			// display a page allowing the user to enter info for a new listing
//			request.setAttribute("thisUser", thisUser);
			
			// update the info the user input
	//		aUserDAO.delete(id); // archives the user

//			request.setAttribute("currUserId", currUserId);
//			request.setAttribute("theUser", thisUser);
			// this screen will display the current info and allow user to change it
			RequestDispatcher dispatcher = 
					 request.getRequestDispatcher("/WEB-INF/retextGatherListingInfo.jsp");

			dispatcher.forward(request, response);

		} //end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
			
	} // end addListingForm
	

	private void addListing(HttpServletRequest request, HttpServletResponse response) {
		//  takes the data from retextUpdateProfileForm and updates this user in the db
		System.out.println("\n In RetextManageUserInfoServlet - addListing");
		
		HttpSession session = request.getSession(false);
		int currUserId = (int)session.getAttribute("currUserId");
		
		String isbn = request.getParameter("isbn");
		String condition = request.getParameter("condition");
		double price = Double.parseDouble(request.getParameter("price")); 
		String school = request.getParameter("school");

	System.out.println("currUserId: " + currUserId);
	
System.out.println(" isbn = " + request.getParameter("isbn"));
System.out.println(" school = " + request.getParameter("school"));
System.out.println(" condition = " + request.getParameter("condition"));
System.out.println(" price = " + request.getParameter("price") + "\n ");

		try {
			UserInventoryDAO userInventoryDAO = new UserInventoryDAO();
			
			//get a book_titles id from isbn
			TitleLocatedDAO titleDAO = new TitleLocatedDAO();
			int bookId = titleDAO.getId(isbn);
	System.out.println(" bookId = " + bookId);
	
			if (bookId != 0) { // we have that title in our system
				// add a user_inventory row from user_id, book_id, price, and condition
	
				UserInventory thisUserInv =  new UserInventory(currUserId, bookId, price, condition);
		System.out.println(" thisUserInv created " );
			
				userInventoryDAO.save(thisUserInv);  // put this in the db	
		System.out.println(" after save " );
				
				viewListings(request, response);
			}
			else {  // that book title is not in our system and we need to add it
				
			}
//			request.setAttribute("userId", id);
//			request.setAttribute("theUser", curUser);
//			RequestDispatcher dispatcher = 
//					 request.getRequestDispatcher("/WEB-INF/retextUserProfileUpdated.jsp");
//
//			dispatcher.forward(request, response);

		} //end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
		
	} // end addListing


	
//	private void createNewUser(HttpServletRequest request, HttpServletResponse response) {
//		// takes the info from gatherNewUserInfo() and stores it in the database
//		
//		System.out.println("\n In retextMessagesServlet - createNewUser");
//		UsersDAO aUserDAO = new UsersDAO();
//		String uCard = "";
//		uCard = request.getParameter("takeCards");
//	//	AUser newU = new AUser(uEmail,uName,uPass,card,uSchool);
//		
//		System.out.println(" uCard = " + uCard);
//		int card = 0;  // default user does not take cards
////		if(uCard.equals("y")) card = 1;
//		System.out.println(" card = " + card);
//		AUser newU = new AUser(request.getParameter("email"),request.getParameter("userName"),
//				request.getParameter("password"),card,request.getParameter("schoolName"));
//		aUserDAO.save(newU);
//		System.out.println("\n email = " + request.getParameter("email"));
//		System.out.println(" userName = " + request.getParameter("userName"));
//		System.out.println(" schoolName = " + request.getParameter("schoolName"));
//		System.out.println(" schoolNickName = " + request.getParameter("schoolNickName"));
//		System.out.println(" takeCards = " + request.getParameter("takeCards"));
//		System.out.println(" password = " + request.getParameter("password") + "\n ");
//		
//		// list users to see if add was ok
//		
//		// display a page saying that the user has been created 
//			request.setAttribute("newUser",request.getParameter("userName") );
//		RequestDispatcher dispatcher = 
//				 request.getRequestDispatcher("/WEB-INF/retextUserCreated.jsp");
//		try {
//			dispatcher.forward(request, response);
//		} catch (Exception e) {}
//		finally {}
//	} // end createNewUser()
//	
	
} // end class RetextManageUserInfoServlet
