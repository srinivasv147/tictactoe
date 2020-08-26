package io.github.srinivasv147.tictactoe.dto;

public class CreateUserDTO {
	
	private String userId;
	private LoginDTO loginDTO;
	
	public CreateUserDTO() {}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public LoginDTO getLoginDTO() {
		return loginDTO;
	}

	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}

}
