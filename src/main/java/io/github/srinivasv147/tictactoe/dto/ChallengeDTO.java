package io.github.srinivasv147.tictactoe.dto;

public class ChallengeDTO {
	
	private Long id;
	
	private String challengee;
	
	private String challenger;
	
	private Boolean isChallengerX;
	
	public ChallengeDTO() {}
	
	public ChallengeDTO(ChallengeDTO challenge) {
		this.id = challenge.id;
		this.challengee = challenge.challengee;
		this.challenger = challenge.challenger;
		this.isChallengerX = challenge.isChallengerX;
	}
	
	public ChallengeDTO(Long id, String challengee, String challenger, Boolean isChallengerX) {
		this.id = id;
		this.challengee = challengee;
		this.challenger = challenger;
		this.isChallengerX = isChallengerX;
	}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
