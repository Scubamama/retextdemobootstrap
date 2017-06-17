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

//	private int id = 0;
	private int senderId = 0;
	private String senderName = "";
	private int messageId = 0;
	private String viewed = "";
	private String message = "";
	
	public DisplayMessages(Integer senderId, String senderName, Integer messageId,
			String viewed, String message) {
	
		this(senderName, messageId, viewed, message);
		this.senderId = senderId;
	//	this.id = id;
//		this.sellerName = sellerName;
//		this.viewed = viewed;
//		this.message = message;
	}
	
	public DisplayMessages(String senderName, Integer messageId, String viewed, String message) {
	
		this.senderName = senderName;
		this.messageId = messageId;
		this.viewed = viewed;
		this.message = message;
	}
	
	public DisplayMessages() {
		//super();
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
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
