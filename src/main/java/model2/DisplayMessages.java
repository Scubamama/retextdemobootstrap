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
public class DisplayMessages {

	private int id = 0;
	private String sellerName = "";
	private String viewed = "";
	private String message = "";
	
	public DisplayMessages(Integer id, String sellerName, 
			String viewed, String message) {
	
		this(sellerName, viewed, message);
		this.id = id;
//		this.sellerName = sellerName;
//		this.viewed = viewed;
//		this.message = message;
	}
	
	public DisplayMessages(String sellerName, String viewed, String message) {
	
		this.sellerName = sellerName;
		this.viewed = viewed;
		this.message = message;
	}
	
	public DisplayMessages() {
		//super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getViewed() {
		return viewed;
	}

	public void setViewed(String viewed) {
		this.viewed = viewed;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	
} // end class DisplayMessages
