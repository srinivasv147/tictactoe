package io.github.srinivasv147.tictactoe.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import io.github.srinivasv147.tictactoe.dto.ChallengeDTO;
import io.github.srinivasv147.tictactoe.dto.TwoPGameDTO;
import io.github.srinivasv147.tictactoe.entities.TwoPGame;
import io.github.srinivasv147.tictactoe.entities.User;
import io.github.srinivasv147.tictactoe.enums.GameResultEnum;
import io.github.srinivasv147.tictactoe.repository.ChallengeRepository;
import io.github.srinivasv147.tictactoe.repository.TwoPGameRepository;

@Service
public class TwoPGameService {
	
	@Autowired
	TwoPGameRepository gameRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ChallengeService challengeService;
	
	@Autowired
	ChallengeRepository challengeRepository;
	
	@Autowired
	FindMoveService findMoveService;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public TwoPGame createGame(ChallengeDTO challenge) {
		User xUser = userService.getUserByUserId(challenge.getChallenger());
		User oUser = userService.getUserByUserId(challenge.getChallengee());
		TwoPGame game = new TwoPGame();
		game.setoUser(oUser);
		game.setxUser(xUser);
		game.setResult(GameResultEnum.UNDECIDED);
		game.setLastMoveTime(new Date());
		game.setGameState("0,0,0,0,0,0,0,0,0");
		TwoPGame savedGame = gameRepository.saveAndFlush(game);
		challengeRepository.deleteById(challenge.getId());
		return savedGame;
	}

	public boolean checkActiveGame(String usrId) {
		if(gameRepository.getActivegame(usrId).isPresent()) return true;
		else return false;
	}

	private boolean isValidMove(List<Integer> oldGameState
			, List<Integer> newGameState) {
		if(findMoveService.checkValid(newGameState)) {
			return checkMove(oldGameState, newGameState);
		}
		else return false;
	}

	private boolean checkMove(List<Integer> oldGameState, List<Integer> newGameState) {
		int same = 0;
		int diff = 0;
		Iterator<Integer> oldIterator = oldGameState.iterator();
		Iterator<Integer> newIterator = newGameState.iterator();
		while(oldIterator.hasNext()) {
			if(oldIterator.next() == newIterator.next()) same++;
			if(oldIterator.next() == 0 && newIterator.next() != 0) diff++;
		}
		if(same == 8 && diff == 1) return true;
		else return false;
	}

	public TwoPGame updateGame(TwoPGameDTO gameDto) {
		TwoPGame game = gameRepository.getOne(gameDto.getGameId());
		List<Integer> oldGameState = game.getGameState();
		List<Integer> newGameState = gameDto.getGameState().getGameState();
		if(isValidMove(oldGameState, newGameState)) {
			game.setGameState(newGameState);
			game.setResult(findMoveService.checkGameOver(newGameState));
			game.setLastMoveTime(new Date());
			gameRepository.saveAndFlush(game);
		}
		return null;
	}
	
	

}
