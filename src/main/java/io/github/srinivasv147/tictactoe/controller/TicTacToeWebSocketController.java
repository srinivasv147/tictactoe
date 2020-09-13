package io.github.srinivasv147.tictactoe.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import io.github.srinivasv147.tictactoe.dto.ChallengeDTO;
import io.github.srinivasv147.tictactoe.dto.TwoPGameDTO;
import io.github.srinivasv147.tictactoe.entities.TwoPGame;
import io.github.srinivasv147.tictactoe.service.ChallengeService;
import io.github.srinivasv147.tictactoe.service.TwoPGameService;

@Controller
public class TicTacToeWebSocketController {
	
	Logger logger = LoggerFactory.getLogger(TicTacToeWebSocketController.class);
	
	@Autowired
	SimpMessagingTemplate simpleMessagingTemplate;
	
	@Autowired
	ChallengeService challengeService;
	
	@Autowired
	TwoPGameService gameService;
	
	@MessageMapping("/game-move")
	public void playMove(TwoPGameDTO gameDto, Principal principal) {
		TwoPGame game = gameService.updateGame(gameDto);
		if(game != null) {
			TwoPGameDTO newGameDto = new TwoPGameDTO(game);
			this.simpleMessagingTemplate
			.convertAndSendToUser(newGameDto.getoUser(), "/queue/game", newGameDto);
			this.simpleMessagingTemplate
			.convertAndSendToUser(newGameDto.getxUser(), "/queue/game", newGameDto);
		}
	}
	
	@MessageMapping("/accept-challenge")
	public void acceptChallenge(ChallengeDTO challenge, Principal principal) throws Exception {
		if(challengeService.checkChallenge(challenge) 
				&& challenge.getChallengee().equals(principal.getName())
				&& !gameService.checkActiveGame(principal.getName())
				&& !gameService.checkActiveGame(challenge.getChallenger())) {
			// only act if challenge is valid and you are accepting challenges for yourself;
			//check if there is an active game;
			TwoPGame game = gameService.createGame(challenge);
			TwoPGameDTO gameDTO = new TwoPGameDTO(game);
			this.simpleMessagingTemplate
			.convertAndSendToUser(challenge.getChallengee(), "/queue/game", gameDTO);
			this.simpleMessagingTemplate
			.convertAndSendToUser(challenge.getChallengee(), "/queue/game", gameDTO);
		}
		
	}
	
	@MessageMapping("/challenge")
	public void challengePassThrough(ChallengeDTO challenge, Principal principal) throws Exception {
		
		logger.info("got challenge from user {} to user {}",challenge.getChallenger(),challenge.getChallengee());
		
		if(challenge.getChallengee() != null) {
			if(!principal.getName().equals(challenge.getChallenger())) return;// only create challenges for yourself
			if(challenge.getChallengee().equals(challenge.getChallenger())) return;//can not challenge yourself
			ChallengeDTO sendChallenge = challengeService.insertChallenge(challenge);
			if(sendChallenge == null) return;// successfully inserted challenge to db.
			this.simpleMessagingTemplate
			.convertAndSendToUser(challenge.getChallengee(), "/queue/challenge", sendChallenge);
		}
		
	}
}
