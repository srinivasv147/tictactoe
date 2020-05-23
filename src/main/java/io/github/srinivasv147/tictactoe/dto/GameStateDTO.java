package io.github.srinivasv147.tictactoe.dto;

import java.util.List;

import io.github.srinivasv147.tictactoe.enums.GameResultEnum;

public class GameStateDTO {
	
	private Boolean valid;
	private Boolean gameOver;
	private List<Integer> gameState;
	private GameResultEnum gameResult;
	
	public GameStateDTO() {}
	
	public GameStateDTO(Boolean valid
			, Boolean gameOver
			, List<Integer> gameState
			, GameResultEnum gameResult) {
		
		this.setValid(valid);
		this.setGameState(gameState);
		this.setGameOver(gameOver);
		this.setGameResult(gameResult);
		
	}

	public List<Integer> getGameState() {
		return gameState;
	}

	public void setGameState(List<Integer> gameState) {
		this.gameState = gameState;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public Boolean getGameOver() {
		return gameOver;
	}

	public void setGameOver(Boolean gameOver) {
		this.gameOver = gameOver;
	}

	public GameResultEnum getGameResult() {
		return gameResult;
	}

	public void setGameResult(GameResultEnum gameResult) {
		this.gameResult = gameResult;
	}

}
