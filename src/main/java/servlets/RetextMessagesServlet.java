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
import database.MessagesDAO;
import database.TitleLocatedDAO;
import database.UsersDAO;
import model2.AUser;
import model2.DisplayMessages;
import model2.Messages;
import model2.TitleLocated;

/**
 * Servlet implementation class RetextTitleLocatedServlet Allows users to
 * message each other in the app
 */
public class RetextMessagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RetextMessagesServlet() {
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
			list(request, response); // list messages to this user
		} else if (pathInfo.equals("/view")) {
			list(request, response); // list messages to this user

		} else if (pathInfo.equals("/send")) {
			sendMessageToSeller(request, response);
		} else if (pathInfo.equals("/delete")) {
			// delete message
			confirmDeleteMessage(request, response);
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
			list(request, response); // list messages to this user
		} else if (pathInfo.equals("/send")) {
			// send messages
			createNewMessage(request, response);
		} else if (pathInfo.equals("/deleteYes")) {
			deleteMessage(request, response);
		}

	} // end doPost

	// list all of this user's messages
	private void list(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);
		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int currUserId = (int)tempUserId;

//		int currUserId = (int) session.getAttribute("currUserId");

		DisplayMessagesDAO dispMessDAO = new DisplayMessagesDAO();
		// get all users messages
		List<DisplayMessages> messageList = null;
		try {
			messageList = dispMessDAO.listMyMessages(currUserId);

			if (messageList.isEmpty()) { // no messages found
				// String message = "You have no messages.";
				request.setAttribute("message", "You have no messages.");

				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextNotFound.jsp");
				dispatcher.forward(request, response);

			}

			request.setAttribute("messageList", messageList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextViewMessages.jsp");
			dispatcher.forward(request, response);

		} catch (Exception exc) {
			throw new RuntimeException(exc);

		}

	} // end list()

	//
	private void sendMessageToSeller(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// calls up the form that the user fills in their data in
		// (retextCreateUser.jsp)

		String isbn = request.getParameter("isbn"); // get the isbn from
													// previous screen

		String sellerName = "";

		// user needs to be logged in to do this

		HttpSession session = request.getSession(false);

		if (session.getAttribute("currUserId") != null) { // they are already
															// logged in

			int sellerId = Integer.parseInt(request.getParameter("id"));
			UsersDAO aUserDAO = new UsersDAO();
			try {
				AUser u = aUserDAO.get(sellerId);
				sellerName = u.getUserName();

			} // end try
			catch (Exception exc) {
				throw new RuntimeException(exc);
			}

			request.setAttribute("sellerName", sellerName);
			request.setAttribute("sellerId", sellerId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextContactSellerForm.jsp");
			dispatcher.forward(request, response);
		} // if (session != null)

		else { // make them log in

			request.setAttribute("isbn", isbn);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextLoginForm.jsp");

			dispatcher.forward(request, response);

		} // end else

	} // end sendMessageToSeller()

	private void createNewMessage(HttpServletRequest request, HttpServletResponse response) {
		// takes the info from gatherNewUserInfo() and stores it in the database

		// this all needs to be done for a new messages object
		MessagesDAO messDAO = new MessagesDAO();

		// if user is not logged in make them do it now

		HttpSession session = request.getSession(false);
		if (session == null) {
		}

		Integer tempUserId = (Integer)session.getAttribute("currUserId");
		int senderId = (int)tempUserId;

//		int senderId = (int) session.getAttribute("currUserId");

		int receiverId = Integer.parseInt(request.getParameter("id"));

		int viewed = 0;
		String message = request.getParameter("message");

		Messages newMess = new Messages(senderId, Integer.parseInt(request.getParameter("id")), viewed,
				request.getParameter("message"));
		messDAO.save(newMess);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextMessageSent.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (Exception exc) {
			throw new RuntimeException(exc);

		}
	} // end createNewMessage()

	private void confirmDeleteMessage(HttpServletRequest request, HttpServletResponse response) {
		// deletes the message from the database

		int id = Integer.parseInt(request.getParameter("id"));
		// this all needs to be done for a new messages object
		MessagesDAO messDAO = new MessagesDAO();
		try {
			request.setAttribute("id", id);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/retextConfirmDeleteMess.jsp");

			dispatcher.forward(request, response);

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
	} // end confirmDeleteMessage

	private void deleteMessage(HttpServletRequest request, HttpServletResponse response) {
		// deletes the message from the database

		int id = Integer.parseInt(request.getParameter("id"));
		// if they confirmed that they do want to delete then do it
		MessagesDAO messDAO = new MessagesDAO();
		try {
			messDAO.delete(id);
			request.setAttribute("id", id);
			// list(request, response);
			
			response.sendRedirect("/retextdemo/messages");

//			RequestDispatcher dispatcher = request.getRequestDispatcher("/messages");
//
//			dispatcher.forward(request, response);

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
	} // end deleteMessage

} // end class RetextMessagesServlet
