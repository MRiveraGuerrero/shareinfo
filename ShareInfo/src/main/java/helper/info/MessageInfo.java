package helper.info;

public class MessageInfo {
	private String message;
	private String username;
	
	public MessageInfo(String message, String username) {
		this.message = message;
		this.username = username;
	}
	
	public String getMessage() {
		return this.message;
	}	
	
	public String getUsername() {
		return this.username;
	}	

}