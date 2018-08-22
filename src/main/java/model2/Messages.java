package model2;

/**
 * The Messages entity represents a message from a sender to a receiver using
 * both of those ids from the users table of the retext database as foreign keys
 * Used in the reText app and the messages table in the retext db
 * 
 * @author Holly Williams
 *
 */
public class Messages {

	private int id = 0;

	private int senderId = 0;
	private int receiverId = 0;
	private int viewed = 0;
	private String message = "";

	public Messages() {
		// super();
	}

	public Messages(int id, int senderId, int receiverId, int viewed, String message) {
		this(senderId, receiverId, viewed, message);
		this.id = id;
	}

	public Messages(int senderId, int receiverId, int viewed, String message) {
		this.senderId = senderId;
		this.receiverId = receiverId;
		this.viewed = viewed;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public int getViewed() {
		return viewed;
	}

	public void setViewed(int viewed) {
		this.viewed = viewed;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

} // end class Messages
