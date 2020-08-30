package io.github.srinivasv147.tictactoe.dto;

public class ChallengeDTO {
	
	private String challengee;
	
	private String challenger;
	
	private Boolean isChallengerX;
	
	public ChallengeDTO() {}

	public String getChallengee() {
		return challengee;
	}

	public void setChallengee(String challengee) {
		this.challengee = challengee;
	}

	public String getChallenger() {
		return challenger;
	}

	public void setChallenger(String challenger) {
		this.challenger = challenger;
	}

	public Boolean getIsChallengerX() {
		return isChallengerX;
	}

	public void setIsChallengerX(Boolean isChallengerX) {
		this.isChallengerX = isChallengerX;
	}

}
