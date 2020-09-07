package io.github.srinivasv147.tictactoe.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Challenge {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "challengee_id")
	private User challengee;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "challenger_id")
	private User challenger;

	private Boolean isChallengerX;
	
	public Challenge() {}
	
	public Challenge(User challengee, User challenger, Boolean isChallengerX) {
		this.challengee = challengee;
		this.challenger = challenger;
		this.isChallengerX = isChallengerX;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Boolean getIsChallengerX() {
		return isChallengerX;
	}

	public void setIsChallengerX(Boolean isChallengerX) {
		this.isChallengerX = isChallengerX;
	}
	
	public User getChallengee() {
		return challengee;
	}

	public void setChallengee(User challengee) {
		this.challengee = challengee;
	}

	public User getChallenger() {
		return challenger;
	}

	public void setChallenger(User challenger) {
		this.challenger = challenger;
	}
	
}
