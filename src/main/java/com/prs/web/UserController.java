package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.prs.business.User;
import com.prs.db.UserRepo;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepo userRepo;
	
	// Get all users
	@GetMapping("/")
	public List<User> getAll() {
		return userRepo.findAll();
	}
	
	// Get a user by id
	@GetMapping("/{id}")
	public Optional<User> getById(@PathVariable int id) {
		return userRepo.findById(id);
	}
	
	// Add a user
	@PostMapping("/")
	public User addUser(@RequestBody User u) {
		u = userRepo.save(u);
		return u;
	}
	
	// Update a user
	@PutMapping("/")
	public User updateUser(@RequestBody User u) {
		u = userRepo.save(u);
		return u;
	}
	
	// Delete a user by id
	@DeleteMapping("{id}")
	public User deleteUser(@PathVariable int id) {
		Optional<User> u = userRepo.findById(id);
		if(u.isPresent()) {
			userRepo.deleteById(id);
		} else {
			System.out.println("Error - user not found for id " + id);
		}
		return u.get();
	}

	/*
	// Get Mapping - Get User by Username and Password
	@GetMapping("/login")
	public Optional<User> getByUserNameAndPassword(@RequestParam String userName, @RequestParam String password) {
		Optional<User> u = userRepo.findByUserNameAndPassword(userName, password);
		if(u.isPresent()) {
			return u;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}		
	}
	
	// Post Mapping - Get User by Username and Password
	@PostMapping("/login")
	public Optional<User> getByUserNameAndPassword(@RequestBody User u) {
		Optional<User> user = userRepo.findByUserNameAndPassword(u.getUserName(), u.getPassword());
		if(user.isPresent()) {
			return user;
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}		
	}
	*/
	
	// Login
	@PostMapping("/login")
	public Optional<User> login(@RequestBody User user) {
		return userRepo.findByUserNameAndPassword(user.getUserName(), user.getPassword());
	}
}
