package io.github.srinivasv147.tictactoe.entities;

import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.github.srinivasv147.tictactoe.enums.GameResultEnum;

public class TwoPGame {
	
	@Id
	Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "xUser_id")
	User xUser;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "oUser_id")
	User oUser;
	
	String gameState;//comma separated game state. for simple games this is fine
	
	GameResultEnum result;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastMoveTime;

	public Date getLastMoveTime() {
		return lastMoveTime;
	}

	public void setLastMoveTime(Date lastMoveTime) {
		this.lastMoveTime = lastMoveTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getxUser() {
		return xUser;
	}

	public void setxUser(User xUser) {
		this.xUser = xUser;
	}

	public User getoUser() {
		return oUser;
	}

	public void setoUser(User oUser) {
		this.oUser = oUser;
	}

	public String getGameState() {
		return gameState;
	}

	public void setGameState(String gameState) {
		this.gameState = gameState;
	}

	public GameResultEnum getResult() {
		return result;
	}

	public void setResult(GameResultEnum result) {
		this.result = result;
	}

	
	
	
}
