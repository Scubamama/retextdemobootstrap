package database;

import static java.lang.System.out;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DataSource;
import model2.AUser;
import model2.Messages;


/**
 * CRUD functionality for messages in reText app
 * @author Holly Williams
 *
 */

public class MessagesDAO {

	DataSource ds;
	
	public MessagesDAO() {
		this.ds = DataSource.getInstance();
	}

	
	// not fixed yet - may not use this
	public List<AUser> searchUsers(String text) throws SQLException {
	//	DatabaseManager mgr = new DatabaseManager();
		List<AUser> userList = new ArrayList<AUser>();
		String sql = "SELECT * FROM Users where UserName LIKE ? ";
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1, "%" + text + "%");
				myRs = myStmt.executeQuery();

				// 4. Process the result set - put it into the ArrayList
				
				while (myRs.next()) {
					userList.add(new AUser(myRs.getInt("Id"), myRs.getString("Email"), myRs.getString("UserName"), myRs.getString("UserPassword"), myRs.getInt("TakeCards"), myRs.getString("school") ));
				}
				return userList;
		
			} //end try
			finally {
	//			mgr.silentClose(myConn, myStmt, myRs);
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);
			}
		
		} // end searchUsers

	// not fixed yet
	
//	public List<Messages> listMyMessages(Integer id) throws SQLException {
//	//	DatabaseManager mgr = new DatabaseManager();
//		List<Messages> userList = new ArrayList<Messages>();
//		String sql = "SELECT * FROM messages where id=?";
//		
//		PreparedStatement myStmt = null;
//		ResultSet myRs = null;
//		Connection myConn = null;
//		
//		
//		try {
//			// 1. Get a connection to the database
//				myConn = ds.getConnection();
//			// 2. Create a statement object
//				myStmt = myConn.prepareStatement(sql);
//				myStmt.setInt(1,id);
//				myRs = myStmt.executeQuery();
//	
//				if (myRs.next()) {
//	//				Messages m = new Messages(myRs.getInt("Id"), myRs.getString("Email"), myRs.getString("UserName"), myRs.getString("UserPassword"), myRs.getInt("TakeCards"), myRs.getString("school") );
//					return m;
//					
//				} else {
//					return null;
//				}
//		} //end try
//		
		
//		try {
//			// 1. Get a connection to the database
//				myConn = ds.getConnection();
//			// 2. Create a statement object
//				myStmt = myConn.prepareStatement(sql);
//				
//				myRs = myStmt.executeQuery();
//				
//			// 4. Process the result set - put it into the ArrayList
//			
//				while (myRs.next()) {
//					userList.add(new AUser(myRs.getInt("Id"), myRs.getString("Email"), myRs.getString("UserName"), myRs.getString("UserPassword"), myRs.getInt("TakeCards"), myRs.getString("school") ));
//				}
//				return userList;
//			} //end try

//			finally {
//	//			mgr.silentClose(myConn, myStmt, myRs);
//				DataSource.silentClose(myConn);
//				DataSource.silentClose(myStmt);
//				DataSource.silentClose(myRs);
//			}
//			
//	} // end listMyMessages

	
	public void save(Messages newMess) {
		// save a user if one like this does not exist 
		// otherwise update it
				
		out.println("in save newMess.getId() =  " + newMess.getId());
		if(newMess.getId() == 0){
			insert(newMess);
		}else {
			update(newMess);
		}
	
	} // end save()
	
	private void update (Messages newMess) {
		// this is just going to update the user name
		out.println("UPDATING... ");
		
		String sql = "UPDATE messages SET message=? WHERE id=?";
	//	DatabaseManager mgr = new DatabaseManager();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the databased
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql);

				myStmt.setString(1,newMess.getMessage());
				myStmt.setInt(2,newMess.getId());
				
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
	
	
	private void insert (Messages newMess) {
		
		out.println("INSERTING... ");
		
		String sql = "INSERT INTO messages "
				+ "(SenderId, ReceiverId, Viewed, message)"
				+ "VALUES (?, ?, ?, ?)";
		
	//	DatabaseManager mgr = new DatabaseManager();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//pulls data from object newMess & puts it into statement to add to db
				myStmt.setInt(1,newMess.getSenderId()); 
				myStmt.setInt(2,newMess.getReceiverId());
				myStmt.setInt(3,newMess.getViewed());
				myStmt.setString(4,newMess.getMessage());
				
				myStmt.executeUpdate();
				
				ResultSet generatedKeys = myStmt.getGeneratedKeys();
					if (generatedKeys.next()) {
						newMess.setId(generatedKeys.getInt(1));
					} else {
						throw new SQLException("Insertion failed, no new message created.");
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

	
	// not fixed yet
	public AUser get(Integer id) throws SQLException {
		
		String sql = "SELECT * FROM users where id=?";
		
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
					AUser u = new AUser(myRs.getInt("Id"), myRs.getString("Email"), myRs.getString("UserName"), myRs.getString("UserPassword"), myRs.getInt("TakeCards"), myRs.getString("school") );
					return u;
					
				} else {
					return null;
				}
	
			} //end try

			finally {
	//			mgr.silentClose(myConn, myStmt, myRs);
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);
			}

	} // end get()
	
	// not fixed yet
	public AUser get(String uName) throws SQLException {
		
		String sql = "SELECT * FROM users where UserName=?";
		
	//	DatabaseManager mgr = new DatabaseManager();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1,uName);
				myRs = myStmt.executeQuery();
	
				if (myRs.next()) {
					AUser u = new AUser(myRs.getInt("Id"), myRs.getString("Email"), myRs.getString("UserName"), myRs.getString("UserPassword"), myRs.getInt("TakeCards"), myRs.getString("school") );
					return u;
					
				} else {
					return null;
				}
	
			} //end try

			finally {
	//			mgr.silentClose(myConn, myStmt, myRs);
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);
			}

	} // end get()

	
	public void delete(Integer id) throws SQLException {
			
		String sql = "DELETE FROM messages WHERE id=?";
		
	//	DatabaseManager mgr = new DatabaseManager();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database 
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql);
				myStmt.setInt(1, id);

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

	} // end delete
	
} // end class MessagesDAO
