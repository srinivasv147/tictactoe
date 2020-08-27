package io.github.srinivasv147.tictactoe.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.github.srinivasv147.tictactoe.dto.GameStateDTO;
import io.github.srinivasv147.tictactoe.enums.GameResultEnum;
import io.github.srinivasv147.tictactoe.objects.Move;

@Component
public class FindMoveService {
	
	Logger logger = LoggerFactory.getLogger(FindMoveService.class);
	private static final Double MAX_REWARD_FOR_WIN = 10000.0;
	
	public GameStateDTO findNextMove(List<Integer> gameState) {
		
		logger.info("checking if game state {} is valid", gameState.toString());
		if(!checkValid(gameState)) {
			return new GameStateDTO(false, false
					, gameState, GameResultEnum.UNDECIDED);
		}
		
		GameResultEnum result = checkGameOver(gameState);
		if(result != GameResultEnum.UNDECIDED) {
			return new GameStateDTO(true, true, gameState, result);
		}
		
		//find player to play. (assuming that x always plays first)
		int player = 0;
		for(int x : gameState) player += x;
		player = (player == 0) ? 1 : -1;
		logger.info("player to play is {}",player);
		
		//get rewards for the next move.
		Move nextMove = getNextMove(gameState, player);
		logger.info("position of next move is {}", nextMove.getPosition());
		gameState.set(nextMove.getPosition(), player);
		
		//check if game is over
		result = checkGameOver(gameState);
		boolean isGameOver = (result != GameResultEnum.UNDECIDED);
		
		return new GameStateDTO(true, isGameOver
				, gameState, result);
		
	}

	
	private Move getNextMove(List<Integer> gameState, int player) {
		
		List<Move> possibleMoves = new ArrayList<>();
		for(int i = 0; i < 9; i++) {
			if(gameState.get(i) == 0) {
				gameState.set(i, player);
				GameResultEnum result = checkGameOver(gameState);
				
				if(result == GameResultEnum.X_WINS) {
					possibleMoves.add(new Move(i,MAX_REWARD_FOR_WIN));
				}
				else if(result == GameResultEnum.O_WINS) {
					possibleMoves.add(new Move(i,-1*MAX_REWARD_FOR_WIN));
				}
				else if(result == GameResultEnum.GAME_IS_DRAWN) {
					possibleMoves.add(new Move(i,0.0));
				}
				else {
					Move opponentMove = getNextMove(gameState, player*-1);
					possibleMoves.add(new Move(i, opponentMove.getReward()));
				}
				
				gameState.set(i, 0);
			}
		}
		
		Move nextMove = new Move(possibleMoves.get(0).getPosition(),possibleMoves.get(0).getReward());
		for(Move move : possibleMoves) {
			if(player*move.getReward() > player*nextMove.getReward()) {
				nextMove.setReward(move.getReward());
				nextMove.setPosition(move.getPosition());
			}
		}
		
		return nextMove;
		
	}


	private GameResultEnum checkGameOver(List<Integer> gameState) {
		
		long sumRow, sumCol
		, rightDownDiagSum = 0, rightUpDiagSum = 0, zeroCount = 0;
		for(int i = 0; i < 3; i++) {
			sumRow = 0;
			sumCol = 0;
			for(int j = 0; j < 3; j++) {
				sumRow += gameState.get(i*3+j);
				sumCol += gameState.get(i+3*j);
				if(gameState.get(i*3+j) == 0) zeroCount++;
			}
			rightDownDiagSum += gameState.get(4*i);
			rightUpDiagSum += gameState.get(2*(3-i));
			if(sumRow == 3 || sumCol == 3) return GameResultEnum.X_WINS;
			if(sumRow == -3 || sumCol == -3) return GameResultEnum.O_WINS;
		}
		if(rightUpDiagSum == 3 || rightDownDiagSum == 3) {
			return GameResultEnum.X_WINS;
		}
		if(rightUpDiagSum == -3 || rightDownDiagSum == -3) {
			return GameResultEnum.O_WINS;
		}
		
		//No empty squares and no one has won so it is a draw.
		if(zeroCount == 0) return GameResultEnum.GAME_IS_DRAWN;
				
		return GameResultEnum.UNDECIDED;
		
	}

	private boolean checkValid(List<Integer> gameState) {
		/*
		 * Validity of tictactoe game:
		 * 1. All elements will be 1, -1, 0.
		 * 2. sum of everything must be 1, -1, 0 (initial state).
		 */
		logger.info("checking if gamestate {} size is valid", gameState.toString());
		if(gameState.size() != 9) return false;//wrong representation
		logger.info("gameState size is {}", gameState.size());
		long sumOfGame = 0;
		for(int x : gameState) {
			if(x != 1 && x != 0 && x != -1) return false;
			sumOfGame += x;
		}
		logger.info("sum of gamestate list is {}", sumOfGame);
		if(sumOfGame != 0 && sumOfGame != 1) return false;
		
		return true;
	}

}
