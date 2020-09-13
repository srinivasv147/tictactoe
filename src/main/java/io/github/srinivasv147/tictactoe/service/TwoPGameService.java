package io.github.srinivasv147.tictactoe.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import io.github.srinivasv147.tictactoe.dto.ChallengeDTO;
import io.github.srinivasv147.tictactoe.dto.TwoPGameDTO;
import io.github.srinivasv147.tictactoe.entities.TwoPGame;
import io.github.srinivasv147.tictactoe.entities.User;
import io.github.srinivasv147.tictactoe.enums.GameResultEnum;
import io.github.srinivasv147.tictactoe.repository.TwoPGameRepository;

@Service
public class TwoPGameService {
	
	@Autowired
	TwoPGameRepository gameRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ChallengeService challengeService;

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public TwoPGame createGame(ChallengeDTO challenge) {
		User xUser = userService.getUserByUserId(challenge.getChallenger());
		User oUser = userService.getUserByUserId(challenge.getChallengee());
		TwoPGame game = new TwoPGame();
		game.setoUser(oUser);
		game.setxUser(xUser);
		game.setResult(GameResultEnum.UNDECIDED);
		game.setLastMoveTime(new Date());
		TwoPGame savedGame = gameRepository.saveAndFlush(game);
		return savedGame;
	}

	public boolean checkActiveGame(String usrId) {
		if(gameRepository.getActivegame(usrId).isPresent()) return true;
		else return false;
	}

	private boolean isValidMove(TwoPGameDTO game) {
		
		return false;
	}

	public TwoPGame updateGame(TwoPGameDTO gameDto) {
		
		return null;
	}
	
	

}
