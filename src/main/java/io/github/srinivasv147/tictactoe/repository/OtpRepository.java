package io.github.srinivasv147.tictactoe.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.github.srinivasv147.tictactoe.entities.Otp;

@Repository
public interface OtpRepository extends JpaRepository<Otp, String>{

	@Query("select t from Otp t inner join t.User u where t.token = ?1")
	public Optional<Otp> findOneByToken(String otp);
	
	@Query("select t from Otp t inner join t.User u where u.email = ?1")
	public Optional<Otp> findOneByUser(String email);
	
}
