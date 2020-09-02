package io.github.srinivasv147.tictactoe.entities;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class Otp {
	
	@Id
	private String token;
	
	@OneToOne
	@JoinColumn(name = "email")
	private User User;
	
	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public Otp() {}
	
	public Otp(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
