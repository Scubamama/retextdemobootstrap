package database;

import static java.lang.System.out;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DataSource;
import model2.School;

/**
 * CRUD functionality for schools in reText app Uses prepared statements to
 * access a database
 * 
 * @author Holly Williams
 *
 */

public class SchoolDAO {

	DataSource ds;

	public SchoolDAO() {
		this.ds = DataSource.getInstance();
	}

	public List<School> searchSchool(String text) throws SQLException {
		List<School> schoolList = new ArrayList<School>();
		String sql = "SELECT * FROM School where SchoolName LIKE ? "; // later
																		// add
																		// or
																		// nickName
																		// like

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, "%" + text + "%");
			// 3. get info from the db
			myRs = myStmt.executeQuery();
			// 4. Process the result set - put it into the ArrayList
			while (myRs.next()) {
				schoolList.add(new School(myRs.getInt("Id"), myRs.getString("SchoolName"), myRs.getString("NickName"),
						myRs.getString("City"), myRs.getString("Campus")));
			}
			return schoolList;

		} // end try
		finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);

		}

	} // end searchSchool

	public List<School> listSchools() throws SQLException {
		List<School> schoolList = new ArrayList<School>();
		String sql = "SELECT * FROM School";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database

			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);
			// 3. get info from the db
			myRs = myStmt.executeQuery();

			// 4. Process the result set - put it into the ArrayList

			while (myRs.next()) { // field names are from the db table
				// userList.add(new AUser(myRs.getInt("Id"),
				// myRs.getString("Email"), myRs.getString("UserName"),
				// myRs.getString("UserPassword"), myRs.getInt("TakeCards"),
				// myRs.getString("school") ));
				schoolList.add(new School(myRs.getInt("Id"), myRs.getString("SchoolName"), myRs.getString("NickName"),
						myRs.getString("City"), myRs.getString("Campus")));
			}
			return schoolList;
		} // end try

		finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);

		}

	} // end listSchools

	public void save(School school) {
		// save a user if one like this does not exist
		// otherwise update it

		if (school.getId() == 0) {
			insert(school);
		} else {
			update(school);
		}

	} // end save()

	private void update(School school) {
		// this is just going to update the user name
		out.println("UPDATING... ");

		String sql = "UPDATE School SET SchoolName=?, NickName=?, City=?, Campus=? WHERE id=?";
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			// pulls info from object and puts it into the statement object
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, school.getName());
			myStmt.setString(2, school.getNickName());
			myStmt.setString(3, school.getCity());
			myStmt.setString(4, school.getCampus());
			myStmt.setInt(5, school.getId());

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

	private void insert(School school) {

		out.println("INSERTING... ");

		String sql = "INSERT INTO School " + "(SchoolName, NickName, City, Campus)" + "VALUES (?, ?, ?, ?)";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			// pulls info from object and puts it into the statement object
			myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			myStmt.setString(1, school.getName());
			myStmt.setString(2, school.getNickName());
			myStmt.setString(3, school.getCity());
			myStmt.setString(4, school.getCampus());
			// 3. pull info from db
			myStmt.executeUpdate();
			// 4. Process the result set
			try {
				ResultSet generatedKeys = myStmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					school.setId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Insertion failed, no new id created.");
				}
			
			
//			try (ResultSet generatedKeys = myStmt.getGeneratedKeys()) {
//				if (generatedKeys.next()) {
//					school.setId(generatedKeys.getInt(1));
//				} else {
//					throw new SQLException("Insertion failed, no new id created.");
//				}

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

	public School get(Integer id) throws SQLException {

		String sql = "SELECT * FROM School where id=?";

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
				// create a return object from db info
				School s = new School(myRs.getInt("Id"), myRs.getString("SchoolName"), myRs.getString("NickName"),
						myRs.getString("City"), myRs.getString("Campus"));
				return s;

			} else {
				return null;
			}

		} // end try
		finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);

		}

	} // end get()


	public School get(String name, String campus) throws SQLException {

		String sql = "SELECT * FROM School where schoolName=? and campus=?";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, name);
			myStmt.setString(2, campus);

			myRs = myStmt.executeQuery();

			if (myRs.next()) {
				// create a return object from db info
				School s = new School(myRs.getInt("Id"), myRs.getString("SchoolName"), myRs.getString("NickName"),
						myRs.getString("City"), myRs.getString("Campus"));
				return s;

			} else {
				return null;
			}

		} // end try
		finally {
			DataSource.silentClose(myConn);
			DataSource.silentClose(myStmt);
			DataSource.silentClose(myRs);

		}

	} // end get()


	public void delete(Integer id) throws SQLException {

		String sql = "DELETE FROM School WHERE id=?";

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;

		try {
			// 1. Get a connection to the database
			myConn = ds.getConnection();
			// 2. Create a statement object
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, id);
			// 3. Do the actual delete db
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

} // end class SchoolDAO
