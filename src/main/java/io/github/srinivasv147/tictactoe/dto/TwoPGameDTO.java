package io.github.srinivasv147.tictactoe.dto;

import java.util.List;

import io.github.srinivasv147.tictactoe.entities.TwoPGame;
import io.github.srinivasv147.tictactoe.enums.GameResultEnum;
import io.github.srinivasv147.tictactoe.service.FindMoveService;

public class TwoPGameDTO {
	
	Long gameId;
	
	String xUser;
	
	String oUser;
	
	GameStateDTO gameState;
	
	Boolean nextMoveX;
	
	public TwoPGameDTO(TwoPGame game){
		this.gameId = game.getId();
		this.oUser = game.getoUser().getUsrId();
		this.xUser = game.getxUser().getUsrId();
		List<Integer> state = game.getGameState();
		this.gameState.setGameState(state);
		this.nextMoveX = FindMoveService.findNextPlayer(state) == 1;
		this.gameState.setGameResult(game.getResult());
		this.gameState.setValid(true);
		this.gameState.setGameOver(
				!this.gameState.getGameResult().equals(GameResultEnum.UNDECIDED));
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getxUser() {
		return xUser;
	}

	public void setxUser(String xUser) {
		this.xUser = xUser;
	}

	public String getoUser() {
		return oUser;
	}

	public void setoUser(String oUser) {
		this.oUser = oUser;
	}

	public GameStateDTO getGameState() {
		return gameState;
	}

	public void setGameState(GameStateDTO gameState) {
		this.gameState = gameState;
	}

	public Boolean getNextMoveX() {
		return nextMoveX;
	}

	public void setNextMoveX(Boolean nextMoveX) {
		this.nextMoveX = nextMoveX;
	}
	
}
