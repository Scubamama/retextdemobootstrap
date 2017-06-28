package database;

import static java.lang.System.out;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DataSource;
import model2.AUser;

/**
 * CRUD functionality for users in reText app
 * @author Holly Williams
 *
 */

public class UsersDAO {

	DataSource ds;
	
	public UsersDAO() {
		this.ds = DataSource.getInstance();
	}

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

	public List<AUser> listMyUsers() throws SQLException {
	//	DatabaseManager mgr = new DatabaseManager();
		List<AUser> userList = new ArrayList<AUser>();
		String sql = "SELECT * FROM users";
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql);
				
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
			
	} // end listMyUsers

	
	public void save(AUser newU) {
		// save a user if one like this does not exist 
		// otherwise update it
		
	//	insert(newU);   // for testing 
	//	update(newU);   // for testing
		
		out.println("in save newU.getId() =  " + newU.getId());
		if(newU.getId() == 0){
			insert(newU);
		}else {
			update(newU);
		}
	
	} // end save()
	
	private void update (AUser newU) {
		// this is just going to update the user name
		out.println("UPDATING... ");
		
		String sql = "UPDATE Users SET Email=?, UserName=?, UserPassword=?, TakeCards=?, school=?"
				+ " WHERE id=?";
		
//		String sql = "INSERT INTO Users "
//				+ "(Email, UserName, UserPassword, TakeCards, school)"
//				+ "VALUES (?, ?, ?, ?, ?)";
//
		
	//	DatabaseManager mgr = new DatabaseManager();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the databased
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql);

//				myStmt.setString(1,newU.getUserName());
//				myStmt.setInt(2,newU.getId());
				
				myStmt.setString(1,newU.getUserEmail()); //pulls email from object
				myStmt.setString(2,newU.getUserName());
				myStmt.setString(3,newU.getUserPassword());
				myStmt.setInt(4,newU.getTakeCards());
				myStmt.setString(5,newU.getUserSchool());
				myStmt.setInt(6,newU.getId());
				
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
	
	
	private void insert (AUser newU) {
		
		out.println("INSERTING... ");
		
		String sql = "INSERT INTO Users "
				+ "(Email, UserName, UserPassword, TakeCards, school)"
				+ "VALUES (?, ?, ?, ?, ?)";
		
	//	DatabaseManager mgr = new DatabaseManager();
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				myStmt.setString(1,newU.getUserEmail()); //pulls email from object
				myStmt.setString(2,newU.getUserName());
				myStmt.setString(3,newU.getUserPassword());
				myStmt.setInt(4,newU.getTakeCards());
				myStmt.setString(5,newU.getUserSchool());
				
				myStmt.executeUpdate();
				
				ResultSet generatedKeys = myStmt.getGeneratedKeys();
					if (generatedKeys.next()) {
						newU.setId(generatedKeys.getInt(1));
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
	// this will actually archive a user (set the archive field to 1 for true)
	//	String sql = "DELETE FROM users WHERE id=?";
		String sql = "UPDATE Users SET archived=1 WHERE id=?";
		
System.out.println("in dao delete, id: " + id);
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
	
	
	public int checkLogin(String uName, String uPassword) throws SQLException {
		// check the database to see if a user named uName with a password password exists
		int id = 0;   // will return 0 if id is not found
		String sql = "SELECT * FROM users where UserName=? and UserPassword=?";
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1,uName);
				myStmt.setString(2,uPassword);

				myRs = myStmt.executeQuery();
	
				if (myRs.next()) {
//					AUser u = new AUser(myRs.getInt("Id"), myRs.getString("Email"), myRs.getString("UserName"), myRs.getString("UserPassword"), myRs.getInt("TakeCards"), myRs.getString("school") );
					id = myRs.getInt("Id");
					return id;
					
				} else {
					return 0;
				}
	
			} //end try

			finally {
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);
			}

	} // end checkLogin
	
} // end class UsersDAO
