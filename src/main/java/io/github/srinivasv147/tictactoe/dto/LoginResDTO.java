package io.github.srinivasv147.tictactoe.dto;

public class LoginResDTO {
	
	private String jwt;
	
	public LoginResDTO() {}
	
	public LoginResDTO(String jwt) {
		this.setJwt(jwt);
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

}
