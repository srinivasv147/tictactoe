package io.github.srinivasv147.tictactoe.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import io.github.srinivasv147.tictactoe.dto.ChallengeDTO;
import io.github.srinivasv147.tictactoe.service.ChallengeService;

@Controller
public class TicTacToeWebSocketController {
	
	Logger logger = LoggerFactory.getLogger(TicTacToeWebSocketController.class);
	
	@Autowired
	SimpMessagingTemplate simpleMessagingTemplate;
	
	@Autowired
	ChallengeService challengeService;
	
	@MessageMapping("/game-move")
	public void playMove(Principal principal) {
		
	}
	
	@MessageMapping("/accept-challenge")
	public void acceptChallenge(ChallengeDTO challenge, Principal principal) throws Exception {
		//write with game db and repository tomorrow.
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
