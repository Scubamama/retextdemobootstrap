package database;

import static java.lang.System.out;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DataSource;
import model2.BookTitles;
import model2.TitleLocated;
import model2.UserInventory;
import model2.UserInventoryDisplay;

/**
 * A class to dsiplay info from the user inventory table of the retext db Uses
 * prepared statements to access a database
 * 
 * @author Holly Williams
 *
 */

public class TitleLocatedDAO {

	DataSource ds;

	public TitleLocatedDAO() {
		this.ds = DataSource.getInstance();
	}

	public List<TitleLocated> findAvailableBooks(String isbn) {
		List<TitleLocated> myBookList = new ArrayList<TitleLocated>();

		String sql = "select i.Price, i.bookCondition, u.UserName, u.Id , b.isbn " + "from retext.user_inventory i "
				+ "join retext.book_titles b on Isbn = ? and b.Id = i.Book_Id " + "join retext.users u "
				+ "where b.id = i.Book_id and i.User_id = u.id ";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, isbn);
			// 3. Do the actual db select
			myRs = myStmt.executeQuery();

			// 4. Process the result set - put it into the ArrayList
			while (myRs.next()) {
				myBookList.add(new TitleLocated(myRs.getInt("Id"), myRs.getString("Isbn"), myRs.getDouble("price"),
						myRs.getString("bookCondition"), myRs.getString("userName")));

			}
			return myBookList;

		} // end try
		catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);
		}
	} // end findAvailableBooks


	public List<TitleLocated> findAvailableBooks(String isbn, String schoolName, String campus,
			String nickName) {
		List<TitleLocated> myBookList = new ArrayList<TitleLocated>();

		String sql = "select i.Price, i.bookCondition, u.UserName, u.Id, b.isbn, b.id, i.id, " +
					"s.schoolName, s.nickName, s.campus, u.campus, u.school " +
				"from retext.user_inventory i " +
				"join retext.book_titles b on Isbn = ? and b.Id = i.Book_Id  " +
				"join retext.users u on i.User_id = u.id and u.school = ? and u.campus = ? " +
				"join retext.school s on  ? = s.schoolName and ? = s.campus or ? = s.NickName";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			myConn = ds.getConnection();
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, isbn);
			myStmt.setString(2, schoolName);
			myStmt.setString(3, campus);
			myStmt.setString(4, schoolName);
			myStmt.setString(5, campus);
			myStmt.setString(6, nickName);

			myRs = myStmt.executeQuery();

			while (myRs.next()) {
				myBookList.add(new TitleLocated(myRs.getInt("Id"), myRs.getString("Isbn"), myRs.getDouble("price"),
						myRs.getString("bookCondition"), myRs.getString("userName")));
			}
			return myBookList;

		} // end try
		catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);
		}
	} // end findAvailableBooks


	public List<TitleLocated> findAvailableBooks(String isbn, String schoolName, String campus) {
		List<TitleLocated> myBookList = new ArrayList<TitleLocated>();

		String sql = "select i.Price, i.bookCondition, u.UserName, u.Id, b.isbn, b.id, i.id, " +
				"s.schoolName, s.campus, u.campus, u.school " +
				"from user_inventory i " +
				"join book_titles b on Isbn = ? and b.Id = i.Book_Id  " +
				"join users u on i.User_id = u.id and u.school = ? and u.campus = ? and archived = 0 " +
				"join school s on  ? = s.schoolName and ? = s.campus";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			myConn = ds.getConnection();
			if (myConn == null) System.out.println("myConn is null");
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, isbn);
			myStmt.setString(2, schoolName);
			myStmt.setString(3, campus);
			myStmt.setString(4, schoolName);
			myStmt.setString(5, campus);

			myRs = myStmt.executeQuery();
			while (myRs.next()) {
				myBookList.add(new TitleLocated(myRs.getInt("Id"), myRs.getString("Isbn"), myRs.getDouble("price"),
						myRs.getString("bookCondition"), myRs.getString("userName")));
			}
			return myBookList;

		} // end try
		catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);
		}
	} // end findAvailableBooks

	public void save(UserInventory inv) {
		// save a user if one like this does not exist
		// otherwise update it

		if (inv.getId() == 0) {
			insert(inv);
		} else {
			update(inv);
		}

	} // end save()

	private void update(UserInventory inv) {
		out.println("UPDATING New User book... ");

		String sql = "UPDATE User_Inventory SET Price=?,Sold=? WHERE id=?";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			myConn = ds.getConnection();
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

		String sql = "INSERT INTO User_Inventory " + "(User_Id, Book_Id, Price, Sold)" + "VALUES (?, ?, ?, ?)";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			myConn = ds.getConnection();
			myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			myStmt.setInt(1, inv.getUserId()); // pulls email from object
			myStmt.setInt(2, inv.getBookId());
			myStmt.setDouble(3, inv.getPrice());
			myStmt.setInt(4, inv.getSold());
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

	public BookTitles get(Integer id) throws SQLException {

		String sql = "SELECT * FROM book_titles where id=?";

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

				BookTitles book = new BookTitles(myRs.getInt("Id"), myRs.getString("title"), myRs.getString("author"),
						myRs.getString("edition"), myRs.getString("isbn"));

				return book;

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

	public String getTitle(String isbn) throws SQLException {

		String sql = "SELECT Title FROM book_titles where isbn=?";
		String title = "";
		int checkIsbn = 0;

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
				title = myRs.getString("Title");
				return title;

			} else {
				return null;
			}

		} // end try
		finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);
		}

	} // end getTitle()

	public int getId(String isbn) throws SQLException {

		String sql = "SELECT id FROM book_titles where isbn=?";

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

				return myRs.getInt("Id");

			} else { // this isbn is not found
				return 0;
			}

		} // end try
		finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);
		}

	} // end getIs(isbn)

} // end class TitleLocatedDAO
