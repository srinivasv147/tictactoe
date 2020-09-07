package io.github.srinivasv147.tictactoe.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Column(unique = true)
	private String email;
	
	@Id
	@Access(AccessType.PROPERTY)
	private String usrId;
	
	public User() {}
	
	public User(String email, String usrId) {
		this.setEmail(email);
		this.setUsrId(usrId);
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsrId() {
		return usrId;
	}
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}
}
