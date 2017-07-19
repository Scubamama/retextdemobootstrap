package database;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.DataSource;
import model2.DisplayUserInventory;
import model2.UserInventory;

/**
 * A class to do CRUD functions on the user_inventory db table Uses prepared
 * statements to access a database
 * 
 * @author Holly Williams
 *
 */

public class UserInventoryDAO {

	DataSource ds;

	public UserInventoryDAO() {
		this.ds = DataSource.getInstance();
	}

	public List<DisplayUserInventory> searchMyBooks(String text) throws SQLException {

		List<DisplayUserInventory> myBookList = new ArrayList<DisplayUserInventory>();

		String sql = "select i.id, b.title, b.author, b.edition, b.isbn," + "i.price "
				+ "from retext.book_titles b join retext.user_inventory i "
				+ "where b.id = i.Book_id and i.User_id = ? and b.Title LIKE ? ";

		int currUserId = 1; // change to sessions if needed

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
			// 3. do the actual db select
			myRs = myStmt.executeQuery();

			// 4. Process the result set - put it into the ArrayList

			while (myRs.next()) {
				myBookList.add(
						new DisplayUserInventory(myRs.getInt("Id"), myRs.getString("Title"), myRs.getString("author"),
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

	public List<DisplayUserInventory> listMyBooks() {
		List<DisplayUserInventory> invList = new ArrayList<DisplayUserInventory>();

		String sql = "select i.id, b.title, b.author, b.edition, b.isbn," + "i.price "
				+ "from retext.book_titles b join retext.user_inventory i "
				+ "where b.id = i.Book_id and i.User_id = ?";

		int currUserId = 1; // change to sessions if needed
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

			// 4. Process the result set - put it into the ArrayList
			while (myRs.next()) {
				invList.add(
						new DisplayUserInventory(myRs.getInt("Id"), myRs.getString("Title"), myRs.getString("author"),
								myRs.getString("edition"), myRs.getString("isbn"), myRs.getDouble("price")));

			}
		} // end try
		catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);
		}
		return invList;

	} // end listMyBooks

	public void save(UserInventory inv) {
		// save a user inventory item if one like this does not exist
		// otherwise update it

		if (inv.getId() == 0) {
			insert(inv);
		} else {
			update(inv);
		}

	} // end save()

	private void update(UserInventory inv) {
		// this is going to update price and sold
		out.println("UPDATING User book... ");

		String sql = "UPDATE User_Inventory SET Price=?,Sold=? WHERE id=?";

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
			myStmt.setInt(3, inv.getId());

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

		String sql = "INSERT INTO User_Inventory " + "(User_Id, Book_Id, Price, Sold, bookCondition) "
				+ "VALUES (?, ?, ?, ?, ?)";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			myStmt.setInt(1, inv.getUserId()); // pulls data from object
			myStmt.setInt(2, inv.getBookId());
			myStmt.setDouble(3, inv.getPrice());
			myStmt.setInt(4, inv.getSold());
			myStmt.setString(5, inv.getCondition());

			myStmt.executeUpdate();

			ResultSet generatedKeys = myStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				inv.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Insertion failed, no new id created.");
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

	// get a record from the user_inventory table that has this specific id
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

	// get a record from user_inventory that has this specific isbn
	public UserInventory get(String isbn) throws SQLException {

		String sql = "SELECT * FROM user_inventory where isbn=?";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, isbn);
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

	} // end get(isbn)

	public void delete(Integer currUserId, Integer bookId) throws SQLException {

		String sql = "DELETE FROM User_Inventory WHERE User_Id=? AND Book_Id=?";

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

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);

		} finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);
		}

	} // end delete

} // end class UserInventoryDAO
