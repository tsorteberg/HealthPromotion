package dmacc.beans;

public class LogIn {
	private String username;
	private String password;
	private boolean usernameFound;
	private boolean passwordFound;
	
	public LogIn() {
		super();
		this.usernameFound = true;
		this.passwordFound = true;
	}
	
	public LogIn(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		this.usernameFound = true;
		this.passwordFound = true;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isUsernameFound() {
		return usernameFound;
	}

	public void setUsernameFound(boolean usernameFound) {
		this.usernameFound = usernameFound;
	}

	public boolean isPasswordFound() {
		return passwordFound;
	}

	public void setPasswordFound(boolean passwordFound) {
		this.passwordFound = passwordFound;
	}
	
	
	
}
