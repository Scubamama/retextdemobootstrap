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
 * 
 * @author Holly Williams
 *
 */

public class MessagesDAO {

	DataSource ds;

	public MessagesDAO() {
		this.ds = DataSource.getInstance();
	}

	public void save(Messages newMess) {
		// save a user if one like this does not exist
		// otherwise update it

		if (newMess.getId() == 0) {
			insert(newMess);
		} else {
			update(newMess);
		}

	} // end save()

	private void update(Messages newMess) {
		out.println("UPDATING... ");

		String sql = "UPDATE messages SET message=? WHERE id=?";
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the databased
			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);

			myStmt.setString(1, newMess.getMessage());
			myStmt.setInt(2, newMess.getId());

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

	private void insert(Messages newMess) {

		out.println("INSERTING... ");

		String sql = "INSERT INTO messages " + "(SenderId, ReceiverId, Viewed, message)" + "VALUES (?, ?, ?, ?)";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// pulls data from object newMess & puts it into statement to add to
			// db
			myStmt.setInt(1, newMess.getSenderId());
			myStmt.setInt(2, newMess.getReceiverId());
			myStmt.setInt(3, newMess.getViewed());
			myStmt.setString(4, newMess.getMessage());

			myStmt.executeUpdate();

			ResultSet generatedKeys = myStmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				newMess.setId(generatedKeys.getInt(1));
			} else {
				throw new SQLException("Insertion failed, no new message created.");
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


	public void delete(Integer id) throws SQLException {

		String sql = "DELETE FROM messages WHERE id=?";

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

		} // end try
		catch (Exception exc) {
			throw new RuntimeException(exc);

		} finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);
		}

	} // end delete

} // end class MessagesDAO
