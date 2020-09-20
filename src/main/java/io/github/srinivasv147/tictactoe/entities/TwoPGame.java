package io.github.srinivasv147.tictactoe.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.github.srinivasv147.tictactoe.enums.GameResultEnum;

@Entity
public class TwoPGame {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@ManyToOne
	@JoinColumn(name = "xUser_id")
	User xUser;
	
	@ManyToOne
	@JoinColumn(name = "oUser_id")
	User oUser;
	
	String gameState;//comma separated game state. for simple games this is fine
	
	@Enumerated(EnumType.STRING)
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

	public List<Integer> getGameState() {
		String[] gameStateArr = this.gameState.split(",");
		List<Integer> state = new ArrayList<>();
		for(String pos : gameStateArr) state.add(Integer.parseInt(pos));
		return state;
	}

	public void setGameState(String gameState) {
		this.gameState = gameState;
	}
	
	public void setGameState(List<Integer> gameState) {
		StringBuilder sb = new StringBuilder();
		for(Integer pos : gameState) sb.append(pos.toString()+",");
		this.gameState = sb.substring(0, sb.length()-1);
	}

	public GameResultEnum getResult() {
		return result;
	}

	public void setResult(GameResultEnum result) {
		this.result = result;
	}

	
	
	
}
