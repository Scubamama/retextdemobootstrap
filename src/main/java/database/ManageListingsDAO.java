package database;

import static java.lang.System.out;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DataSource;
import model2.AUser;
import model2.DisplayUserListings;
import model2.UserInventory;
import model2.UserInventoryDisplay;

/**
 * A class that manages listings of a particular user Uses prepared statements
 * to access a database
 * 
 * @author Holly Williams
 *
 */

public class ManageListingsDAO {

	DataSource ds;

	public ManageListingsDAO() {
		this.ds = DataSource.getInstance();
	}

	public List<UserInventoryDisplay> searchMyBooks(String text) throws SQLException {
		List<UserInventoryDisplay> myBookList = new ArrayList<UserInventoryDisplay>();

//		String sql = "select i.id, b.title, b.author, b.edition, b.isbn," + "i.price "
//				+ "from retext.book_titles b join retext.user_inventory i "
//				+ "where b.id = i.Book_id and i.User_id = ? and b.Title LIKE ? ";
		String sql = "select i.id, b.title, b.author, b.edition, b.isbn," + "i.price "
				+ "from book_titles b join user_inventory i "
				+ "on b.id = i.Book_id and i.User_id = ? and b.Title LIKE ? ";

		int currUserId = 1;

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, currUserId);
			myStmt.setString(2, "%" + text + "%");
			myRs = myStmt.executeQuery();

			// 3. Process the result set - put it into the ArrayList

			while (myRs.next()) {
				myBookList.add(
						new UserInventoryDisplay(myRs.getInt("Id"), myRs.getString("Title"), myRs.getString("author"),
								myRs.getString("edition"), myRs.getString("isbn"), myRs.getDouble("price")));
			}
			return myBookList;

		} // end try
		finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);

		}

	} // end searchMyBooks

	public List<DisplayUserListings> listMyBooks(int currUserId) throws SQLException {
		List<DisplayUserListings> listingList = new ArrayList<DisplayUserListings>();

		String isSold = "";
//		String sql = "select i.id, b.isbn, b.title, b.author, b.edition, " + "i.price, i.bookCondition, i.sold "
//				+ "from retext.book_titles b join retext.user_inventory i "
//				+ "where b.id = i.Book_id and i.User_id = ?";
		String sql = "select i.id, b.isbn, b.title, b.author, b.edition, " + "i.price, i.bookCondition, i.sold "
				+ "from book_titles b join user_inventory i "
				+ "on b.id = i.book_id and i.user_id = ?";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, currUserId);
			myRs = myStmt.executeQuery();

			// 3. Process the result set - put it into the ArrayList
			while (myRs.next()) {
				if (myRs.getInt("sold") == 0)
					isSold = "N";
				else
					isSold = "Y";

//				listingList.add(new DisplayUserListings(myRs.getInt("Id"), myRs.getString("isbn"),
//						myRs.getString("Title"), myRs.getString("author"), myRs.getString("edition"),
//						myRs.getDouble("price"), myRs.getString("bookCondition"), isSold, myRs.getInt("i.id")));
				listingList.add(new DisplayUserListings(myRs.getInt("Id"), myRs.getString("isbn"),
						myRs.getString("Title"), myRs.getString("author"), myRs.getString("edition"),
						myRs.getDouble("price"), myRs.getString("bookCondition"), isSold, myRs.getInt("id")));
			}
			return listingList;
		} // end try
		finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);

		}

	} // end listMyBooks

	public void save(UserInventory inv) {

		if (inv.getId() == 0) {
			insert(inv);
		} else {
			update(inv);
		}

	} // end save()

	private void update(UserInventory inv) {
		// this is going to update price and sold
		out.println("UPDATING User book... ");

		String sql = "UPDATE User_Inventory SET Price=?,Sold=?, bookCondition=? WHERE id=?";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);

			myStmt.setDouble(1, inv.getPrice());
			myStmt.setInt(2, inv.getSold());
			myStmt.setString(3, inv.getCondition());

			myStmt.setInt(4, inv.getId());

			myStmt.executeUpdate();
		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);

		} finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);

		}

	} // end update()

	private void insert(UserInventory inv) {

		out.println("INSERTING New User book... ");

		String sql = "INSERT INTO User_Inventory " + "(User_Id, Book_Id, Price, Sold)" + "VALUES (?, ?, ?, ?)";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			myStmt.setInt(1, inv.getUserId()); // pulls email from object passed
												// in
			myStmt.setInt(2, inv.getBookId());
			myStmt.setDouble(3, inv.getPrice());
			myStmt.setInt(4, inv.getSold());

			myStmt.executeUpdate();

			try {
				ResultSet generatedKeys = myStmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					inv.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Insertion failed, no new id created.");
				}

			} // end inner try
			finally {
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);
			}
		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);

		} finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);

		}

	} // end insert()

	public UserInventory get(Integer id) throws SQLException {

		String sql = "SELECT * FROM user_inventory where id=?";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, id);
			myRs = myStmt.executeQuery();
			if (myRs.next()) {
				UserInventory inv = new UserInventory(myRs.getInt("Id"), myRs.getInt("User_ID"), myRs.getInt("Book_ID"),
						myRs.getDouble("price"), myRs.getString("bookCondition"), myRs.getInt("Sold"));

				return inv;

			} else {
				return null;
			}

		} // end try
		finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);
		}

	} // end get(id)

	public void deleteListing(Integer listingId) throws SQLException {

		String sql = "DELETE FROM User_Inventory WHERE Id=?";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, listingId);

			myStmt.executeUpdate();

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);
		} finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);
		}

	} // end delete

} // end class ManageListingsDAO()
