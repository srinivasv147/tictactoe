package io.github.srinivasv147.tictactoe.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Otp {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	
	@Column(unique = true)
	private String token;
	
	@OneToOne
	@JoinColumn(name = "email", unique = true)
	private User User;
	
	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public Otp() {}
	
	public Otp(String token, User user) {
		this.token = token;
		this.User = user;
	}
	
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
