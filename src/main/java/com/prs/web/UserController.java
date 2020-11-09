package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	
}
