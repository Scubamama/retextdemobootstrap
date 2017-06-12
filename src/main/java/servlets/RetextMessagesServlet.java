package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.MessagesDAO;
import database.UsersDAO;
import model2.AUser;
import model2.Messages;

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
		System.out.println("Inside RetextMessagesServlet - doGet.");
		// read messages
		sendMessageToSeller(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside RetextMessagesServlet - doPost.");
		//send messages
		createNewMessage(request, response);
	}

	// displays all of the copies of the requested title available at user's school
	private void sendMessageToSeller(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// calls up the form that the user fills in their data in (retextCreateUser.jsp)
		System.out.println("\n In retextMessagesServlet - sendMessageToSeller");

		String sellerName = "";
	//	MessagesDAO messDAO = new MessagesDAO();
		System.out.println("\n id: " + request.getParameter("id"));
		int sellerId =Integer.parseInt(request.getParameter("id"));
		UsersDAO aUserDAO = new UsersDAO();
		try {
			AUser u = aUserDAO.get(sellerId); 
			sellerName = u.getUserName();

		} //end try
		catch (Exception exc) {
	//		exc.printStackTrace();
			throw new RuntimeException(exc);
		}
		 
		System.out.println("\n sellerId: " + sellerId);
		System.out.println("\n sellerName: " + sellerName);
		request.setAttribute("sellerName", sellerName);
		request.setAttribute("sellerId", sellerId);
		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextContactSellerForm.jsp");
		dispatcher.forward(request, response);
		
	} // end sendMessageToSeller()

	private void createNewMessage(HttpServletRequest request, HttpServletResponse response) {
		// takes the info from gatherNewUserInfo() and stores it in the database
		
		System.out.println("\n In retextMessagesServlet - createNewMessage");
		// this all needs to be done for a new messages object
		MessagesDAO messDAO = new MessagesDAO();
		
	//	int senderId = Integer.parseInt(request.getParameter("senderId"));
		
		int senderId = 1;   // for now until login sessions are complete
		
		int receiverId = Integer.parseInt(request.getParameter("id"));

		int viewed = 0;
		String message = request.getParameter("message");
		
  System.out.println("senderId: " + senderId);
  System.out.println("receiverId: " + receiverId);
  System.out.println("viewed: " + viewed);
  System.out.println("message: " + message);
		
		Messages newMess = new Messages(senderId, 
				Integer.parseInt(request.getParameter("id")),
				viewed,	request.getParameter("message"));
		messDAO.save(newMess);
		
	//			request.setAttribute("newUser",request.getParameter("userName") );
		RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/WEB-INF/retextBrowse.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (Exception e) {}
		finally {}
	} // end createNewUser()
	
	
} // end class RetextCreateUserServlet
