package retextdemo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RetextManagerServlet
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			welcome(request, response);
		}
		finally {
			
		} // end finally
	} // end doGet


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	} // end doPost


	private void welcome(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// just show the welcome page retext-welcome.jsp
		 RequestDispatcher dispatcher = 
				 request.getRequestDispatcher("/retext-welcome.html");
		 dispatcher.forward(request, response);
	}
	
	
	
} // end RetextManagerServlet
