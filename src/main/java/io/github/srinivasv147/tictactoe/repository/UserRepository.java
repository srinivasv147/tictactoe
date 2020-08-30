package io.github.srinivasv147.tictactoe.repository;

import org.springframework.stereotype.Repository;

import io.github.srinivasv147.tictactoe.entities.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface UserRepository extends JpaRepository<User,String>{
	
	@Query("select u from User u where u.email = ?1")
	public Optional<User> findOneByEmail(String email);
	
	@Query("select u from User u where u.usrId = ?1")
	public Optional<User> findOneByUserId(String userId);

}
