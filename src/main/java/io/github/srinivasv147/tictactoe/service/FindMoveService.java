package io.github.srinivasv147.tictactoe.service;

import java.util.List;

import org.springframework.stereotype.Component;

import io.github.srinivasv147.tictactoe.dto.GameStateDTO;
import io.github.srinivasv147.tictactoe.enums.GameResultEnum;

@Component
public class FindMoveService {
	
	public GameStateDTO findNextMove(List<Integer> gameState) {
		
		if(!checkValid(gameState)) {
			return new GameStateDTO(false, false
					, gameState, GameResultEnum.UNDECIDED);
		}
		
		GameResultEnum result = checkGameOver(gameState);
		if(result != GameResultEnum.UNDECIDED) {
			return new GameStateDTO(true, true, gameState, result);
		}
		
		return new GameStateDTO(true, true
				, gameState, GameResultEnum.O_WINS);
	}

	private GameResultEnum checkGameOver(List<Integer> gameState) {
		
		return null;
	}

	private boolean checkValid(List<Integer> gameState) {
		/*
		 * Validity of tictactoe game:
		 * 1. All elements will be 1, -1, 0.
		 * 2. sum of everything must be 1, -1, 0 (initial state).
		 */
		
		int sumOfGame = 0;
		for(Integer x : gameState) {
			if(!x.equals(1) || !x.equals(0) || !x.equals(-1)) return false;
			sumOfGame += x;
		}
		if(sumOfGame != 0 || sumOfGame != 1 || sumOfGame != -1) return false;
		
		return true;
	}

}
