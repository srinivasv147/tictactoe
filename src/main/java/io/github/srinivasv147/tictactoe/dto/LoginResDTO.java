package io.github.srinivasv147.tictactoe.dto;

public class LoginResDTO {
	
	private String jwt;
	private Boolean isValidEmail;
	private Boolean isUser;
	private String userId;
	
	public LoginResDTO() {}
	
	public LoginResDTO(String jwt, Boolean isValidEmail, Boolean isUser, String userId) {
		this.setJwt(jwt);
		this.setIsUser(isUser);
		this.setUserId(userId);
		this.setIsValidEmail(isValidEmail);
		this.setIsUser(isUser);
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public Boolean getIsUser() {
		return isUser;
	}

	public void setIsUser(Boolean isUser) {
		this.isUser = isUser;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getIsValidEmail() {
		return isValidEmail;
	}

	public void setIsValidEmail(Boolean isValidEmail) {
		this.isValidEmail = isValidEmail;
	}

}
