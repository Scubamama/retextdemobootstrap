package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.BookTitlesDAO;
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
 * Servlet implementation class RetextTitleLocatedServlet Shows the user stuff
 * about them and lets them update it
 */
public class RetextManageUserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RetextManageUserInfoServlet() {
		super();
		//
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();
		HttpSession session = request.getSession(false);

		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int currUserId = (int)tempUserId;
//		int currUserId = (int) session.getAttribute("currUserId");

		if (pathInfo == null || "".equals(pathInfo)) {
			manageUserInfo(request, response); //
		} else if (pathInfo.equals("/listings")) {
			viewListings(request, response); //
		} else if (pathInfo.equals("/profile")) {
			viewProfile(request, response); //
		} else if (pathInfo.equals("/updateProfileForm")) {
			updateProfileForm(request, response); // gets the data to update a
													// user
		} else if (pathInfo.equals("/deleteProfile")) {
			confirmDeleteProfile(request, response); // asks if they really want
														// to del
		} else if (pathInfo.equals("/updateListingForm")) {
			updateListingForm(request, response); // gets the data to update a
													// user
		} else if (pathInfo.equals("/deleteListingConfirm")) {
			confirmDeleteListing(request, response); // asks if they really want
														// to del
		} else if (pathInfo.equals("/addListingForm")) {
			gatherListingInfo(request, response); // gets the data to update a
													// user
		}

	} // end doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();

		if (pathInfo == null || "".equals(pathInfo)) {
			manageUserInfo(request, response); // screen to ask what they want
												// to do
		} else if (pathInfo.equals("/saveUpdateProfile")) {
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
		} else if (pathInfo.equals("/addTitle")) {
			addTitle(request, response);
		} else if (pathInfo.equals("/gatherListingInfo")) {
			gatherListingInfo(request, response);
		} else if (pathInfo.equals("/profile")) {
			viewProfile(request, response); //
//		} else if (pathInfo.equals("/listings")) {
//			viewListings(request, response); //
		}

	} // end doPost

	// asks the user what they want to do: manage listings, profile, more,
	// logout
	private void manageUserInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// asks the user what they want to do: manage listings, profile, more,
		// logout

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextManageUserInfo.jsp");
		dispatcher.forward(request, response);

	} // end manageUserInfo()

	// displays a screen telling user that a feature is not available yet
	// private void featureUnavailable(HttpServletRequest request,
	// HttpServletResponse response)
	// throws ServletException, IOException {
	// // calls up the form that the user fills in their data in
	// (retextCreateUser.jsp)
	// System.out.println("\n In RetextManageUserInfoServlet -
	// featureUnavailable");
	//
	// RequestDispatcher dispatcher =
	// request.getRequestDispatcher("/WEB-INF/retextFeatureUnavailable.jsp");
	// dispatcher.forward(request, response);
	//
	// } // end featureUnavailable()

	// list all of this user's listings
	private void viewListings(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);
		
		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int currUserId = (int)tempUserId;

//		int currUserId = (int) session.getAttribute("currUserId");

		ManageListingsDAO dispListingsDAO = new ManageListingsDAO();
		// get all users messages
		List<DisplayUserListings> listingList = null;
		try {
			listingList = dispListingsDAO.listMyBooks(currUserId);
			if (listingList.isEmpty()) { // no listings found
				// String message = "You have no listings.";
				request.setAttribute("message", "You have no listings.");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextNotFound.jsp");
				dispatcher.forward(request, response);

			}

			request.setAttribute("listingList", listingList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextViewListings.jsp");
			dispatcher.forward(request, response);

		} catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end viewListings()

	private void viewProfile(HttpServletRequest request, HttpServletResponse response) {
		// show the user their current info username, password, if they take
		// cards and their school
		// and allow them to edit all of that except maybe username

		HttpSession session = request.getSession(false);
		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int currUserId = (int)tempUserId;

//		int currUserId = (int) session.getAttribute("currUserId");

		UsersDAO aUserDAO = new UsersDAO();
		AUser thisUser = new AUser();
		try {
			thisUser = aUserDAO.get(currUserId);
			// display a page showing the user's current info and let them
			// change it
			// takeCards is 0 or 1 in the db
			if (thisUser.getTakeCards() == 0)
				thisUser.setTakeCardsYN("N");
			else
				thisUser.setTakeCardsYN("Y");

			request.setAttribute("thisUser", thisUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextViewProfile.jsp");
			dispatcher.forward(request, response);

		} catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end viewProfile

	private void updateProfileForm(HttpServletRequest request, HttpServletResponse response) {
		// gets the data to update a user

		HttpSession session = request.getSession(false);
		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int currUserId = (int)tempUserId;

//		int currUserId = (int) session.getAttribute("currUserId");

		try {
			UsersDAO aUserDAO = new UsersDAO();

			AUser thisUser = new AUser();
			thisUser = aUserDAO.get(currUserId);

			// takeCards is 0 or 1 in the db
			if (thisUser.getTakeCards() == 0)
				thisUser.setTakeCardsYN("N");
			else
				thisUser.setTakeCardsYN("Y");
			// display a page showing the user's current info and let them
			// change it
			request.setAttribute("thisUser", thisUser);

			// update the info the user input

			request.setAttribute("currUserId", currUserId);
			request.setAttribute("theUser", thisUser);
			// this screen will display the current info and allow user to
			// change it
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("/WEB-INF/retextUpdateUser.jsp");

			dispatcher.forward(request, response);

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end updateProfileForm

	private void updateProfile(HttpServletRequest request, HttpServletResponse response) {
		// takes the data from retextUpdateProfileForm and updates this user in
		// the db

		HttpSession session = request.getSession(false);
		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int currUserId = (int)tempUserId;

//		int currUserId = (int) session.getAttribute("currUserId");

		String uCard = request.getParameter("takeCardsYN");

		try {
			UsersDAO aUserDAO = new UsersDAO();
			int card = 0; // default user does not take cards and the db field
							// is int
			if (uCard.equals("y") || uCard.equals("Y"))
				card = 1;

			AUser newU = new AUser(currUserId, request.getParameter("email"), request.getParameter("userName"),
					request.getParameter("password"), card, request.getParameter("schoolName"), request.getParameter("campus"));

			aUserDAO.save(newU);

			String curUser = request.getParameter("userName");

			// viewProfile(request, response);

			response.sendRedirect("profile");
			
//			RequestDispatcher dispatcher = 
//					request.getRequestDispatcher("/manageUsers/profile");
//
//			dispatcher.forward(request, response);

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end updateProfile

	private void confirmDeleteProfile(HttpServletRequest request, HttpServletResponse response) {
		//

		HttpSession session = request.getSession(false);
		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int currUserId = (int)tempUserId;

//		int currUserId = (int) session.getAttribute("currUserId");

		UsersDAO aUserDAO = new UsersDAO();
		AUser thisUser = new AUser();

		try {
			request.setAttribute("currUserId", currUserId);
//			session = request.getSession();
//			session.invalidate();

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextConfirmDeleteUser.jsp");

			dispatcher.forward(request, response);

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end confirmDeleteProfile

	private void archiveProfile(HttpServletRequest request, HttpServletResponse response) {
		// "deletes" this user

		HttpSession session = request.getSession(false);
		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int currUserId = (int)tempUserId;

//		int currUserId = (int) session.getAttribute("currUserId");

		try {
			UsersDAO aUserDAO = new UsersDAO();
			AUser thisUser = new AUser();
			thisUser = aUserDAO.get(currUserId);
			// set the archive field to true
			aUserDAO.delete(currUserId); // archives the user
			String curUser = thisUser.getUserName();

			request.setAttribute("currUserId", currUserId);
			request.setAttribute("theUser", curUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextUserDeleted.jsp");

			dispatcher.forward(request, response);

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end archiveProfile

	private void updateListingForm(HttpServletRequest request, HttpServletResponse response) {
		// gets the data to update a listing

		HttpSession session = request.getSession(false);
		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int currUserId = (int)tempUserId;

//		int currUserId = (int) session.getAttribute("currUserId");
		int listingId = Integer.parseInt(request.getParameter("listingId"));

		try {
			// pull info from user_inventory table from given listingID
			// put pulled info into an object
			// send the object to retextUpdateListing.jsp
			ManageListingsDAO listingDAO = new ManageListingsDAO();

			UserInventory thisListing = new UserInventory();
			// get returns db id, userId, bookId, price, bookCondition, sold
			// from the db table
			thisListing = listingDAO.get(listingId); // get the data from the
														// specific listing

			int bookId = thisListing.getBookId();

			// display a page showing the listing
			request.setAttribute("thisListing", thisListing);

			// update the info the listing input

			request.setAttribute("currUserId", currUserId);
			// this screen will display the current info and allow user to
			// change it
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextUpdateListing.jsp");

			dispatcher.forward(request, response);

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end updateListingForm

	private void updateListing(HttpServletRequest request, HttpServletResponse response) {
		// takes the data from retextUpdateProfileForm and updates this user in
		// the db

		HttpSession session = request.getSession(false);
		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int currUserId = (int)tempUserId;

//		int currUserId = (int) session.getAttribute("currUserId");

		try {
			ManageListingsDAO listingDAO = new ManageListingsDAO();

			// jsp returns db id, userId, bookId, price, bookCondition, sold

			// read in what the user may have changed

			String condition = request.getParameter("bookCondition");

			double price = Double.parseDouble(request.getParameter("price"));

			int listingId = Integer.parseInt(request.getParameter("listingId"));

			UserInventory thisListing = listingDAO.get(listingId);

			thisListing.setCondition(request.getParameter("bookCondition"));
			thisListing.setPrice(price);
			listingDAO.save(thisListing);

			String curUser = request.getParameter("userName");

			// viewListings(request, response);

			// request.setAttribute("userId", id);
			// request.setAttribute("theUser", curUser);
//			response.sendRedirect("/retextdemo/manageUsers/listings");
			response.sendRedirect(request.getContextPath() + "/manageUsers/listings");

//			RequestDispatcher dispatcher = request.getRequestDispatcher("/manageUsers/listings");
//
//			dispatcher.forward(request, response);

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end updateListing

	private void confirmDeleteListing(HttpServletRequest request, HttpServletResponse response) {

		int listingId = Integer.parseInt(request.getParameter("listingId"));

		HttpSession session = request.getSession(false);
		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int currUserId = (int)tempUserId;

//		int currUserId = (int) session.getAttribute("currUserId");

		try {
			request.setAttribute("currUserId", currUserId);
			request.setAttribute("listingId", listingId);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextConfirmDeleteListing.jsp");

			dispatcher.forward(request, response);

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end confirmDeleteListing

	private void deleteListing(HttpServletRequest request, HttpServletResponse response) {
		// "deletes" this user
		// this is the id of the listing in the user_inventory table
		int listingId = Integer.parseInt(request.getParameter("listingId"));

		try {
			ManageListingsDAO listingsDAO = new ManageListingsDAO();
			// delete this listing
			listingsDAO.deleteListing(listingId); // delete it from the db
			// show the user the list again

			// viewListings(request, response);
			
//			response.sendRedirect("/retextdemo/manageUsers/listings");
			response.sendRedirect(request.getContextPath() + "/manageUsers/listings");
			
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/manageUsers/listings");
//
//			dispatcher.forward(request, response);

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end deleteListing

	private void gatherListingInfo(HttpServletRequest request, HttpServletResponse response) {
		// gets the data to update a user

		String isbn = request.getParameter("isbn");
		String condition = request.getParameter("condition");

		HttpSession session = request.getSession(false);
		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int currUserId = (int)tempUserId;

//		int currUserId = (int) session.getAttribute("currUserId");

		if (isbn == null)
			isbn = "";
		if (condition == null)
			condition = "";

		try {

			request.setAttribute("isbn", isbn);
			request.setAttribute("condition", condition);

			// this screen will display the current info and allow user to
			// change it
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextGatherListingInfo.jsp");

			dispatcher.forward(request, response);

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end gatherListingInfo

	private void addListing(HttpServletRequest request, HttpServletResponse response) {
		// takes the data from retextUpdateProfileForm and updates this user in
		// the db

		HttpSession session = request.getSession(false);
		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int currUserId = (int)tempUserId;

//		int currUserId = (int) session.getAttribute("currUserId");

		String isbn = request.getParameter("isbn");
		String condition = request.getParameter("condition");
		double price = Double.parseDouble(request.getParameter("price"));
		String school = request.getParameter("school");

		try {
			UserInventoryDAO userInventoryDAO = new UserInventoryDAO();

			// get a book_titles id from isbn
			TitleLocatedDAO titleDAO = new TitleLocatedDAO();
			int bookId = titleDAO.getId(isbn);

			if (bookId != 0) { // we have that title in our system
				// add a user_inventory row from user_id, book_id, price, and
				// condition

				UserInventory thisUserInv = new UserInventory(currUserId, bookId, price, condition);

				userInventoryDAO.save(thisUserInv); // put this in the db

				// viewListings(request, response);
				
//				response.sendRedirect("/retextdemo/manageUsers/listings");
				response.sendRedirect(request.getContextPath() + "/manageUsers/listings");

//				RequestDispatcher dispatcher = request.getRequestDispatcher("/manageUsers/listings");
//
//				dispatcher.forward(request, response);

			} else { // that book title is not in our system and we need to add
						// it
				request.setAttribute("condition", condition);
				request.setAttribute("price", price);
				request.setAttribute("isbn", isbn);

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextAddTitle.jsp");

				dispatcher.forward(request, response);

			}

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end addListing

	private void addTitle(HttpServletRequest request, HttpServletResponse response) {
		// adds a title to the book_titles table if it doesn't currently exist
		// in the db

		HttpSession session = request.getSession(false);
		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int currUserId = (int)tempUserId;

//		int currUserId = (int) session.getAttribute("currUserId");
		String isbn = request.getParameter("isbn");
		double price = Double.parseDouble(request.getParameter("price"));
		String condition = request.getParameter("condition");

		try {
			// BookTitles: title, author, edition, isbn

			BookTitlesDAO titleDAO = new BookTitlesDAO();

			BookTitles newTitle = new BookTitles(request.getParameter("title"), request.getParameter("author"),
					request.getParameter("edition"), request.getParameter("isbn"));

			titleDAO.save(newTitle); // save it in the db

			String curUser = request.getParameter("userName");

			// go back to gather listing info page with isbn, price, and
			// condition

			request.setAttribute("isbn", isbn);
			request.setAttribute("price", price);
			request.setAttribute("condition", condition);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextGatherListingInfo.jsp");

			dispatcher.forward(request, response);

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}

	} // end addTitle

} // end class RetextManageUserInfoServlet
