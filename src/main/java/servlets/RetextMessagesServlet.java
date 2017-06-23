package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.DisplayMessagesDAO;
import database.MessagesDAO;
import database.TitleLocatedDAO;
import database.UsersDAO;
import model2.AUser;
import model2.DisplayMessages;
import model2.Messages;
import model2.TitleLocated;

/**
 * Servlet implementation class RetextTitleLocatedServlet
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("Inside RetextMessagesServlet - doGet.");

		String pathInfo = request.getPathInfo();
//		System.out.println("pathInfo: " + pathInfo);
		
		if (pathInfo == null || "".equals(pathInfo)) {
			list(request, response); // list messages to this user
		} else if (pathInfo.equals("/view")) {
			list(request, response); // list messages to this user
		
		} else if (pathInfo.equals("/send")) {
				sendMessageToSeller(request, response);
		} else if (pathInfo.equals("/delete")) {
			//delete message
			confirmDeleteMessage(request, response);
		}
 		
	} // end doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
//		System.out.println("Inside RetextMessagesServlet - doPost.");
		String pathInfo = request.getPathInfo();
//		System.out.println("pathInfo: " + pathInfo);
		
		if (pathInfo == null || "".equals(pathInfo)) {
			list(request, response); // list messages to this user
		} else if (pathInfo.equals("/send")) {
			//send messages
			createNewMessage(request, response);
		} else if (pathInfo.equals("/deleteYes")) {
			deleteMessage(request, response);
		}
		
	} // end doPost
	
	// list all of this user's messages
	private void list(HttpServletRequest request, HttpServletResponse response) {

//		System.out.println("\n In retextMessagesServlet - list");
		
		int userId = 1;  // change later when sessions are in place
		
		DisplayMessagesDAO disMessDAO = new DisplayMessagesDAO();
		// get all users messages
		List<DisplayMessages> messageList = null;
		try {
			messageList = disMessDAO.listMyMessages();
		}
		catch (Exception exc) {
	//		e.printStackTrace();
			throw new RuntimeException(exc);

		}
		request.setAttribute("messageList", messageList);

		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextViewMessages.jsp");
		try {   // WHY???
			dispatcher.forward(request, response);   
		} catch (Exception e) {}
		finally {}

	} // end list()


	// 
	private void sendMessageToSeller(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// calls up the form that the user fills in their data in (retextCreateUser.jsp)
//		System.out.println("\n In retextMessagesServlet - sendMessageToSeller");

		String sellerName = "";
	//	MessagesDAO messDAO = new MessagesDAO();
//		System.out.println("\n id: " + request.getParameter("id"));
		int sellerId =Integer.parseInt(request.getParameter("id"));
		UsersDAO aUserDAO = new UsersDAO();
		try {
			AUser u = aUserDAO.get(sellerId); 
			sellerName = u.getUserName();

		} //end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
		 
//		System.out.println("\n sellerId: " + sellerId);
//		System.out.println("\n sellerName: " + sellerName);
		
		request.setAttribute("sellerName", sellerName);
		request.setAttribute("sellerId", sellerId);
		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextContactSellerForm.jsp");
		dispatcher.forward(request, response);
		
	} // end sendMessageToSeller()

	private void createNewMessage(HttpServletRequest request, HttpServletResponse response) {
		// takes the info from gatherNewUserInfo() and stores it in the database
		
//		System.out.println("\n In retextMessagesServlet - createNewMessage");
		// this all needs to be done for a new messages object
		MessagesDAO messDAO = new MessagesDAO();
		
	//	int senderId = Integer.parseInt(request.getParameter("senderId"));
		
		int senderId = 1;   // for now until login sessions are complete
		
		int receiverId = Integer.parseInt(request.getParameter("id"));

		int viewed = 0;
		String message = request.getParameter("message");
		
//  System.out.println("senderId: " + senderId);
//  System.out.println("receiverId: " + receiverId);
//  System.out.println("viewed: " + viewed);
//  System.out.println("message: " + message);
		
		Messages newMess = new Messages(senderId, 
				Integer.parseInt(request.getParameter("id")),
				viewed,	request.getParameter("message"));
		messDAO.save(newMess);
		
	//			request.setAttribute("newUser",request.getParameter("userName") );
		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextMessageSent.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {}
		finally {}
	} // end createNewUser()
	

	private void confirmDeleteMessage(HttpServletRequest request, HttpServletResponse response) {
		// deletes the message from the database
		
//	System.out.println("\n In retextMessagesServlet - confirmDeleteMessage");
		int id = Integer.parseInt(request.getParameter("id"));
//	System.out.println("\n id: " + id);
		// this all needs to be done for a new messages object
		MessagesDAO messDAO = new MessagesDAO();
		try {
			request.setAttribute("id", id);

			RequestDispatcher dispatcher = 
					 request.getRequestDispatcher("/WEB-INF/retextConfirmDeleteMess.jsp");

			dispatcher.forward(request, response);

		} //end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
	} // end confirmDeleteMessage


	private void deleteMessage(HttpServletRequest request, HttpServletResponse response) {
		// deletes the message from the database
		
//	System.out.println("\n In retextMessagesServlet - deleteMessage");
	
		int id = Integer.parseInt(request.getParameter("id"));
//		System.out.println("\n deleting: " + id);
		// if they confirmed that they do want to delete then do it
		MessagesDAO messDAO = new MessagesDAO();
		try {
			messDAO.delete(id);
			request.setAttribute("id", id);
			list(request, response);
//			RequestDispatcher dispatcher = 
//					 request.getRequestDispatcher("/WEB-INF/retextViewMessages.jsp");
//
//			dispatcher.forward(request, response);
	//		response.sendRedirect(arg0);  // to go back to previous page

		} //end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		}
	} // end deleteMessage
	
} // end class RetextCreateUserServlet
