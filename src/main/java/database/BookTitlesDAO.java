package database;

import static java.lang.System.out;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.DataSource;
import model2.BookTitles;

/**
 * CRUD functionality for the book titles table
 * Uses prepared statements to access a database
 * 
 * @author Holly Williams
 *
 */

public class BookTitlesDAO {

	DataSource ds;
	
	public BookTitlesDAO() {
		this.ds = DataSource.getInstance();
		
	}

	public List<BookTitles> searchBooks(String text) throws SQLException {
		List<BookTitles> bookList = new ArrayList<BookTitles>();
		String sql = "SELECT * FROM Book_Titles where Title LIKE ? ";
		
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

			// 3. Process the result set - put it into the ArrayList
				
				while (myRs.next()) {
					bookList.add(new BookTitles(myRs.getInt("Id"), myRs.getString("Title"), myRs.getString("Author"), myRs.getString("Edition"), myRs.getString("Isbn") ));
				}
				return bookList;
	
			} //end try
			finally {
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);

			}
			
		} // end searchBooks

	// pulls all of the book titles 
	public List<BookTitles> listMyBooks() throws SQLException {
		List<BookTitles> bookList = new ArrayList<BookTitles>();
		String sql = "SELECT * FROM Book_Titles";
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database

				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql);
				myRs = myStmt.executeQuery();
				
			// 3. Process the result set - put it into the ArrayList
			
				while (myRs.next()) {
					bookList.add(new BookTitles(myRs.getInt("Id"), myRs.getString("Title"), myRs.getString("Author"), myRs.getString("Edition"), myRs.getString("Isbn") ));
				}
				return bookList;
			} //end try
			finally {
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);

			}

	} // end listMyBooks

	
	public void save(BookTitles book) {
		// save a user if one like this does not exist 
		// otherwise update it
		
		if(book.getId() == 0){
			out.println("INSERTING... ");
			insert(book);
		}else {
			out.println("UPDATING... ");
			update(book);
		}
	
	} // end save()
	
	private void update (BookTitles book) {
		out.println("UPDATING New Book Title... ");
		
		String sql = "UPDATE Book_titles SET Title=?, Author=?, Edition=?,Isbn=? WHERE id=?";
	
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database
			
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql);
			
				myStmt.setString(1,book.getTitle());
				myStmt.setString(2,book.getAuthor());
				myStmt.setString(3,book.getEdition());
				myStmt.setString(4,book.getIsbn());
				myStmt.setInt(5,book.getId());
				
				myStmt.executeUpdate();
			} //end try
			catch (Exception exc) {
				throw new RuntimeException(exc);

			}
			finally {
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);

			}
		
	} // end update()
	
	
	private void insert (BookTitles book) {
		
		out.println("INSERTING New Book Title... ");
		
		String sql = "INSERT INTO Book_Titles "
				+ "(Title, Author, Edition, Isbn)"   //CourseDept and CourseNumber later
				+ "VALUES (?, ?, ?, ?)";
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				myStmt.setString(1,book.getTitle()); //pulls info from object passed in
				myStmt.setString(2,book.getAuthor());
				myStmt.setString(3,book.getEdition());
				myStmt.setString(4,book.getIsbn());
				
				myStmt.executeUpdate();
				try (ResultSet generatedKeys = myStmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						book.setId(generatedKeys.getInt(1));
					} else {
						throw new SQLException("Insertion failed, no new id created.");
					}
						
				} // end inner try
			
			} //end try
			catch (Exception exc) {
//				exc.printStackTrace();
				throw new RuntimeException(exc);
			}
			finally {
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);

			}

	} // end insert()

// gets a bookTitle given its db id	
	public BookTitles get(Integer id) throws SQLException {
			
		String sql = "SELECT * FROM Book_Titles where id=?";
		
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
					BookTitles b = new BookTitles(myRs.getInt("Id"), myRs.getString("Title"), myRs.getString("Author"), myRs.getString("Edition"), myRs.getString("Isbn") );
					return b;
					
				} else {
					return null;
				}
	
			} //end try

			finally {
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);

			}
			
	} // end get(id)
	
	// gets a bookTitle given its title
	public BookTitles get(String title) throws SQLException {
			
		String sql = "SELECT * FROM Book_Titles where Title=?";
		
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		Connection myConn = null;
		
		try {
			// 1. Get a connection to the database
				myConn = ds.getConnection();
			// 2. Create a statement object
				myStmt = myConn.prepareStatement(sql);
				myStmt.setString(1,title);
				myRs = myStmt.executeQuery();
				
				if (myRs.next()) {
					BookTitles b = new BookTitles(myRs.getInt("Id"), myRs.getString("Title"), myRs.getString("Author"), myRs.getString("Edition"), myRs.getString("Isbn") );
					return b;
					
				} else {
					return null;
				}
	
			} //end try

			finally {
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);

			}
			
	} // end get(title)

	// deletes a book title row from the db
	public void delete(Integer id) throws SQLException {
		
		String sql = "DELETE FROM Book_Titles WHERE id=?";
		
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
//				exc.printStackTrace();	
				throw new RuntimeException(exc);
			}
			finally {
				DataSource.silentClose(myConn);
				DataSource.silentClose(myStmt);
				DataSource.silentClose(myRs);

			}
		
	} // end delete
	
} // end class BookTitlesDAO
