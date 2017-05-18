package model2;

/**
 * The TitleLocated entity represents a both book and user data used to display 
 *   price, condition, and seller name
 *   
 *   Used in the reText app 
 * 
 * @author Holly Williams
 *
 */
public class TitleLocated {

	private int id = 0;
	private String title = "";
	private String author = "";
	private String edition = "";
	private String dept = "";
	private String courseNum = "";
	private String isbn = "";
	private Double price = 0.0;
	private String condition = "";
	private String seller = "";
	
	public TitleLocated(Integer id, String title, String author, String edition, String dept, String courseNum,
			String isbn, Double price, String condition, String seller) {
	
		this(id,title,author,edition,isbn,price);
	//	this.id = id;
	//	this.title = title;
	//	this.author = author;
	//	this.edition = edition;
		this.dept = dept;
		this.courseNum = courseNum;
	//	this.isbn = isbn;
	//	this.price = price;
		this.condition = condition;
		this.seller = seller;
	}


	public TitleLocated(int id, String title, String author, String edition, String isbn, Double price) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.edition = edition;
		this.isbn = isbn;
		this.price = price;
	}


	public TitleLocated(String title, String author, String edition, String isbn, Double price) {
		super();
		this.title = title;
		this.author = author;
		this.edition = edition;
		this.isbn = isbn;
		this.price = price;
	}


	public TitleLocated(String title, String author, String edition, String dept, String courseNum, String isbn,
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

	public TitleLocated(Integer id, String isbn, Double price, String condition, String seller) {
	
		this.id = id;
		this.isbn = isbn;
		this.price = price;
		this.condition = condition;
		this.seller = seller;
	}
	public TitleLocated() {
		//super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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
	

	public String getCondition() {
		return condition;
	}


	public void setCondition(String condition) {
		this.condition = condition;
	}


	public String getSeller() {
		return seller;
	}


	public void setSeller(String seller) {
		this.seller = seller;
	}

	
} // end class TitleLocated
