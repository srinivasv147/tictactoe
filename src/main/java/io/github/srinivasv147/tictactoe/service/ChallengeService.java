package io.github.srinivasv147.tictactoe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.srinivasv147.tictactoe.dto.ChallengeDTO;
import io.github.srinivasv147.tictactoe.entities.Challenge;
import io.github.srinivasv147.tictactoe.entities.User;
import io.github.srinivasv147.tictactoe.repository.ChallengeRepository;

@Service
public class ChallengeService {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ChallengeRepository challengeRepository;
	
	public ChallengeDTO insertChallenge(ChallengeDTO challenge) {
		try {
			User challengee = userService.getUserByUserId(challenge.getChallengee());
			User challenger = userService.getUserByUserId(challenge.getChallenger());
			Challenge dbChal = new Challenge(challengee, challenger, true);
			challengeRepository.saveAndFlush(dbChal);
			return new ChallengeDTO(dbChal.getId(), challengee.getUsrId(), challenger.getUsrId(), true);
		}
		catch(Exception e) {
			return null;
		}
	}

	public boolean checkChallenge(ChallengeDTO challengeDto) {
		if(challengeDto.getId() == null) return false;
		Optional<Challenge> challenge = challengeRepository.findById(challengeDto.getId());
		if(challenge.isPresent()) {
			if(!challenge.get().getChallengee().getUsrId().equals(challengeDto.getChallengee())) return false;
			if(!challenge.get().getChallenger().getUsrId().equals(challengeDto.getChallenger())) return false;
			return true;
		}
		else return false;
		
	}

}
