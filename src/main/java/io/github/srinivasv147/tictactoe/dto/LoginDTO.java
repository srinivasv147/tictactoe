package io.github.srinivasv147.tictactoe.dto;

public class LoginDTO {
	
	private String token;
	private String email;
	
	public LoginDTO() {}
	
	public LoginDTO(String token, String email) {
		this.setEmail(email);
		this.setToken(token);
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
