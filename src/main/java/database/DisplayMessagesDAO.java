package database;

import static java.lang.System.out;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DataSource;
import model2.DisplayMessages;

import model2.UserInventory;
import model2.UserInventoryDisplay;

/**
 * A class to dsiplay info from the messages table of the retext db
 * 
 * @author Holly Williams
 *
 */

public class DisplayMessagesDAO {

	DataSource ds;
	
	public DisplayMessagesDAO() {
		this.ds = DataSource.getInstance();
	}

	// looks at my messages and retrieves them
	
//	public List<TitleLocated> findAvailableBooks(String isbn) throws SQLException {
	
	public List<DisplayMessages> listMyMessages()  {
		List<DisplayMessages> myMessageList = new ArrayList<DisplayMessages>();

		System.out.println("in DisplayMessagesdDAO  = " );

		
//		String sql = "select i.Price, i.Condition, u.UserName, u.Id , b.isbn " + 
//				"from retext.user_inventory i " +
//				"join retext.book_titles b on Isbn = ? and b.Id = i.Book_Id " +
//				"join retext.users u " +
//			"where b.id = i.Book_id and i.User_id = u.id ";
	
//		String sql = "select i.id, b.title, b.author, b.edition, b.isbn," + "i.price "
//				+ "from retext.book_titles b join retext.user_inventory i "
//				+ "where b.id = i.Book_id and i.User_id = ? and b.Title LIKE ? ";

//		select u.Id, u.userName, m.viewed, m.message
//		from retext.messages m join retext.users u
//		where m.receiverId = u.Id and m.receiverId = 1
		
		String viewed = "";
		String sql = "select u.Id, u.userName, m.viewed, m.message " + 
				"from retext.messages m join retext.users u " +
			"where m.senderId = u.Id and m.receiverId = ? ";
		
		int currUserId = 1;  // until sessions is in place
		
		System.out.println("sql: " + sql);

	//	System.out.println("in TitleLocatedDAO isbn = " + isbn);
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql);
				myStmt.setInt(1,currUserId);
				myRs = myStmt.executeQuery();

	//			System.out.println("after executeQuery()");
				// 4. Process the result set - put it into the ArrayList
	//			if (myRs == null) {System.out.println("myRs is null");}
	//			else System.out.println("myRs is not null");
				// sellername, viewed, message
				while (myRs.next()) {	
					if (myRs.getInt("viewed") == 0) viewed = "n";
					else viewed = "y";
		System.out.println("myRs.getInt(Id): " + myRs.getInt("Id"));
		System.out.println("myRs.getInt(userName): " + myRs.getString("userName"));
		System.out.println("myRs.getInt(message): " + myRs.getString("message"));

		System.out.println("myRs.getInt(viewed): " + myRs.getInt("viewed"));
		System.out.println("viewed: " + viewed);
					myMessageList.add(new DisplayMessages(myRs.getInt("Id"),
							myRs.getString("userName"),viewed, 
							myRs.getString("message") ));
				}
				return myMessageList;
	
			} //end try
		catch (Exception exc) {
	//		e.printStackTrace();
			throw new RuntimeException(exc);

		}
		
		finally {
			
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);
			
		}
	//	return myMessageList;
		} // end findAvailableBooks

	
	// looks at my books and retrieves any with a title like what I am searching for
	
	public List<UserInventoryDisplay> searchMyBooks(String text) throws SQLException {
	//	DatabaseManager mgr = new DatabaseManager();
		List<UserInventoryDisplay> myBookList = new ArrayList<UserInventoryDisplay>();
		
		//String sql = "SELECT * FROM Users where UserName LIKE ? ";
		
		String sql = "select i.id, b.title, b.author, b.edition, b.isbn," + 
				"i.price " + 
				"from retext.book_titles b join retext.user_inventory i " +
			"where b.id = i.Book_id and i.User_id = ? and b.Title LIKE ? ";
		
		//String sql = "SELECT * FROM Book_Titles where Title LIKE ? ";  from booksdao
		int currUserId = 1;
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql);
				myStmt.setInt(1,currUserId);
				myStmt.setString(2, "%" + text + "%");
				myRs = myStmt.executeQuery();

				// 4. Process the result set - put it into the ArrayList
				
				while (myRs.next()) {
				//	userList.add(new AUser(myRs.getInt("Id"), myRs.getString("Email"), myRs.getString("UserName"), myRs.getString("UserPassword"), myRs.getInt("TakeCards"), myRs.getString("school") ));
				//	myBookList.add(new DisplayUserInventory(myRs.getInt("Id"), myRs.getString("Email"), myRs.getString("UserName"), myRs.getString("UserPassword"), myRs.getInt("TakeCards"), myRs.getString("school") ));
					myBookList.add(new UserInventoryDisplay(myRs.getInt("Id"),myRs.getString("Title"), 
							myRs.getString("author"), myRs.getString("edition"), 
							myRs.getString("isbn"), myRs.getDouble("price") ));	
					//out.println("inv id = " + getId());
				}
				return myBookList;
	
			} //end try
			finally {
	//			mgr.silentClose(myConn, myStmt, myRs);
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);
			}

		} // end searchMyBooks
	

	// lists all of the books that I have in my personal inventory
	
	public List<UserInventoryDisplay> listMyBooks() throws SQLException {
	//	DatabaseManager mgr = new DatabaseManager();
		List<UserInventoryDisplay> invList = new ArrayList<UserInventoryDisplay>();
		// String sql = "SELECT * FROM User_Inventory WHERE User_Id = ? AND Book_Id = ?";
		
		// this was working in mysql workbench
		String sql = "select i.id, b.title, b.author, b.edition, b.isbn," + 
						"i.price " + 
						"from retext.book_titles b join retext.user_inventory i " +
					"where b.id = i.Book_id and i.User_id = ?";
		
		int currUserId = 1;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql);
				myStmt.setInt(1,currUserId);
				myRs = myStmt.executeQuery();
				
			// 4. Process the result set - put it into the ArrayList
				while (myRs.next()) {							
					invList.add(new UserInventoryDisplay(myRs.getInt("Id"),myRs.getString("Title"), 
							myRs.getString("author"), myRs.getString("edition"), 
							myRs.getString("isbn"), myRs.getDouble("price") ));
				}
				return invList;
			} //end try
			finally {
	//			mgr.silentClose(myConn, myStmt, myRs);
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);
			}

	} // end listMyBooks

	
	public void save(UserInventory inv) {
		// save a user if one like this does not exist 
		// otherwise update it
		
	//	insert(newU);   // for testing 
	//	update(newU);   // for testing
		
	//	take out of comments after testing
	//	out.println("in save inv.getId() =  " + inv.getId());
		if(inv.getId() == 0){
			insert(inv);
		}else {
			update(inv);
		}
	
		
	} // end save()
	
	private void update (UserInventory inv) {
		// this is going to update price and sold
		out.println("UPDATING New User book... ");
		
		String sql = "UPDATE User_Inventory SET Price=?,Sold=? WHERE id=?";

	//	DatabaseManager mgr = new DatabaseManager();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql);

				myStmt.setDouble(1,inv.getPrice());
				myStmt.setInt(2,inv.getSold());
				myStmt.setInt(3,inv.getId());
				
				myStmt.executeUpdate();
			} //end try
			catch (Exception exc) {
				exc.printStackTrace();
			}
			finally {
	//			mgr.silentClose(myConn, myStmt, myRs);
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);
			}
		
	} // end update()
	
	
	private void insert (UserInventory inv) {
		
		out.println("INSERTING New User book... ");
		
		String sql = "INSERT INTO User_Inventory "
				+ "(User_Id, Book_Id, Price, Sold)"
				+ "VALUES (?, ?, ?, ?)";
		out.println("sql = " + sql);

	//	DatabaseManager mgr = new DatabaseManager();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				myStmt.setInt(1,inv.getUserId()); //pulls email from object
				myStmt.setInt(2,inv.getBookId());
				myStmt.setDouble(3,inv.getPrice());
				myStmt.setInt(4,inv.getSold());
			// 3. make the call to the db
				myStmt.executeUpdate();

				ResultSet generatedKeys = myStmt.getGeneratedKeys();
					if (generatedKeys.next()) {
						inv.setId(generatedKeys.getInt(1));
					} else {
						throw new SQLException("Insertion failed, no new id created.");
					}

			} //end try
			catch (Exception exc) {
		//		exc.printStackTrace();
				throw new RuntimeException(exc);
			}
			finally {
	//			mgr.silentClose(myConn, myStmt, myRs);
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);
			}

	} // end insert()

	
	public UserInventory get(Integer id) throws SQLException {
		
	String sql = "SELECT * FROM user_inventory where id=?";
	
//	DatabaseManager mgr = new DatabaseManager();
	PreparedStatement myStmt = null;
	ResultSet myRs = null;
	Connection myConn = null;
	
	try {
		// 1. Get a connection to the database
			myConn = ds.getConnection();
		// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1,id);
			myRs = myStmt.executeQuery();
			if (myRs.next()) {
			//	UserInventory inv = new AUser(myRs.getInt("Id"), myRs.getString("Email"), myRs.getString("UserName"), myRs.getString("UserPassword"), myRs.getInt("TakeCards"), myRs.getString("school") );
				UserInventory inv = new UserInventory(myRs.getInt("Id"), 
						myRs.getInt("User_ID"), myRs.getInt("Book_ID"), 
						myRs.getDouble("price"), myRs.getInt("Sold") );
				
				return inv;
				
			} else {
				return null;
			}

		} //end try
		finally {
	//		mgr.silentClose(myConn, myStmt, myRs);
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);
		}

	} // end get()
	
	public String getTitle(String isbn) throws SQLException {
		
	String sql = "SELECT Title FROM book_titles where isbn=?";
	String title = "";
	out.println("SQL: " + sql);
	int checkIsbn = 0;
	
//	DatabaseManager mgr = new DatabaseManager();
	PreparedStatement myStmt = null;
	ResultSet myRs = null;
	Connection myConn = null;
	
	try {
		// 1. Get a connection to the database
			myConn = ds.getConnection();
		// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1,isbn);
			myRs = myStmt.executeQuery();
			if (myRs.next()) {
			//	UserInventory inv = new AUser(myRs.getInt("Id"), myRs.getString("Email"), myRs.getString("UserName"), myRs.getString("UserPassword"), myRs.getInt("TakeCards"), myRs.getString("school") );
//				UserInventory inv = new UserInventory(myRs.getInt("Id"), 
//						myRs.getInt("User_ID"), myRs.getInt("Book_ID"), 
//						myRs.getDouble("price"), myRs.getInt("Sold") );
				title = myRs.getString("Title");
				out.println("title: " + title);
				return title;
				
			} else {
				return null;
			}

		} //end try
		finally {
	//		mgr.silentClose(myConn, myStmt, myRs);
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);
		}

	} // end getTitle()
		

	public void delete(Integer currUserId, Integer bookId) throws SQLException {
		
	String sql = "DELETE FROM User_Inventory WHERE User_Id=? AND Book_Id=?";
	
//	DatabaseManager mgr = new DatabaseManager();
	PreparedStatement myStmt = null;
	ResultSet myRs = null;
	Connection myConn = null;
	
	try {
		// 1. Get a connection to the database
			myConn = ds.getConnection();
		// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, currUserId);
			myStmt.setInt(2, bookId);
			
			myStmt.executeUpdate();

		} //end try
		catch (Exception exc) {
			exc.printStackTrace();
			
		}
		finally {
	//		mgr.silentClose(myConn, myStmt, myRs);
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);
		}

	} // end delete
	
} // end class TitleLocatedDAO
