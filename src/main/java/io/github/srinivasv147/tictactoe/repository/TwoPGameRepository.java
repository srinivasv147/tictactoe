package io.github.srinivasv147.tictactoe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.srinivasv147.tictactoe.entities.TwoPGame;

@Repository
public interface TwoPGameRepository  extends JpaRepository<TwoPGame, Long>{

	@Query("select g from TwoPGame g "
			+ "where (g.xUser.id = ?1 or g.oUser.id = ?1) "
			+ "and g.result = 'UNDECIDED'")
	Optional<TwoPGame> getActivegame(String usrId);
	
	@Query("select g from TwoPGame g where g.id = ?1")
	TwoPGame getGameByGameId(Long gameId);

}
