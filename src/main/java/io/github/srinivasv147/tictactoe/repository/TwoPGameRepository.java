package io.github.srinivasv147.tictactoe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import io.github.srinivasv147.tictactoe.entities.TwoPGame;

public interface TwoPGameRepository  extends JpaRepository<TwoPGame, Long>{

	@Query("select g from TwoPGame g "
			+ "where (g.userX = userId or g.userO = userId) "
			+ "and TwoPGame.result = UNDECIDED")
	Optional<TwoPGame> getActivegame(String usrId);

}
