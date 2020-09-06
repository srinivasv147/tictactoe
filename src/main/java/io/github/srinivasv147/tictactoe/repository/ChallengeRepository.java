package io.github.srinivasv147.tictactoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.srinivasv147.tictactoe.entities.Challenge;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long>{
	

}
