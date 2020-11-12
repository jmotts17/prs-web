package com.prs.db;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prs.business.User;

public interface UserRepo extends JpaRepository<User, Integer> {
	
	// Find a User by username and Password
	Optional<User> findByUserNameAndPassword(String userName, String password);
	
}
