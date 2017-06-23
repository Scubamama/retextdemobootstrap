package model2;

/**
 * The Book entity represents a book title used in the reText app (working name tabby)
 *  and the books table in the retext db
 * 
 * @author Holly Williams
 *
 */
public class DisplayUserListings {

	private int id = 0;
	private String isbn = "";
	private String title = "";
	private String author = "";
	private String edition = "";
	private Double price = 0.0;
	private String condition = "";
	private String sold = "";
	private int listingId = 0;
	private String dept = "";
	private String courseNum = "";
	
	public DisplayUserListings(Integer id, String isbn, String title, String author, String edition, Double price,
			String condition, String sold, Integer listingId,
			String dept, String courseNum ) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.edition = edition;
		this.price = price;
		this.condition = condition;
		this.sold = sold;
		this.listingId = listingId;
		this.dept = dept;
		this.courseNum = courseNum;
	}
	
	public DisplayUserListings(Integer id, String isbn, String title, String author, String edition, Double price,
			String condition, String sold, Integer listingId ) {
		this.id = id;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.edition = edition;
		this.price = price;
		this.condition = condition;
		this.sold = sold;
		this.listingId = listingId;
//		this.dept = dept;
//		this.courseNum = courseNum;
	}

	public String getCondition() {
		return condition;
	}


	public void setCondition(String condition) {
		this.condition = condition;
	}


	public String getSold() {
		return sold;
	}

	public void setSold(String sold) {
		this.sold = sold;
	}

	public int getListingId() {
		return listingId;
	}


	public void setListingId(int listingId) {
		this.listingId = listingId;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public DisplayUserListings(int id, String title, String author, String edition, String isbn, Double price) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.edition = edition;
		this.isbn = isbn;
		this.price = price;
	}


	public DisplayUserListings(String title, String author, String edition, String isbn, Double price) {
		super();
		this.title = title;
		this.author = author;
		this.edition = edition;
		this.isbn = isbn;
		this.price = price;
	}


	public DisplayUserListings(String title, String author, String edition, String dept, String courseNum, String isbn,
			Double price) {
		super();
		this.title = title;
		this.author = author;
		this.edition = edition;
		this.dept = dept;
		this.courseNum = courseNum;
		this.isbn = isbn;
		this.price = price;
	}

	public DisplayUserListings() {
		//super();
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getEdition() {
		return edition;
	}


	public void setEdition(String edition) {
		this.edition = edition;
	}


	public String getDept() {
		return dept;
	}


	public void setDept(String dept) {
		this.dept = dept;
	}


	public String getCourseNum() {
		return courseNum;
	}


	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}
	
} // end class DisplayUserInventory
