package com.prs.web;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.prs.business.Request;
import com.prs.db.RequestRepo;

@CrossOrigin
@RestController
@RequestMapping("/requests")
public class RequestController {

	@Autowired
	private RequestRepo requestRepo;
	
	// Get all requests
	@GetMapping("/")
	public List<Request> getAll() {
		return requestRepo.findAll();
	}
	
	// Get a request by id
	@GetMapping("/{id}")
	public Optional<Request> getById(@PathVariable int id) {
		return requestRepo.findById(id);
	}
	
	// Add a request
	@PostMapping("/")
	public Request addRequest(@RequestBody Request r) {
		r.setStatus("New");
		r.setSubmittedDate(LocalDateTime.now());
		r = requestRepo.save(r);
		return r;
	}
	
	// Update a request
	@PutMapping("/")
	public Request updateRequest(@RequestBody Request r) {
		r = requestRepo.save(r);
		return r;
	}
	
	// Delete a request by id
	@DeleteMapping("{id}")
	public Request deleteRequest(@PathVariable int id) {
		Optional<Request> r = requestRepo.findById(id);
		if (r.isPresent()) {
			requestRepo.deleteById(id);
		} else {
			System.out.println("Error - request not found for id " + id);
		}
		return r.get();
	}
	
	// Submit for review
	@PutMapping("/submit-review")
	public Request submitRequest(@RequestBody Request r) {
		if(r.getTotal() >= 50) {
			r.setStatus("Review");
		} else {
			r.setStatus("Approved");
		}
		r.setSubmittedDate(LocalDateTime.now());
		r = requestRepo.save(r);
		
		return r;
	}
	
	// Show requests in review status and not assigned to logged in user
	@GetMapping("/list-review/{id}")
	public List<Request> getAllReviewRequests(@PathVariable int id) {
		return requestRepo.findByUserIdNotAndStatus(id, "Review");		
	}
	
	// Approve a request
	@PutMapping("/approve")
	public Request approveRequest(@RequestBody Request r) {
		r.setStatus("Approved");
		r = requestRepo.save(r);
		return r;
	}
	
	// Reject a request
	@PutMapping("/reject")
	public Request rejectRequest(@RequestBody Request r) {
		r.setStatus("Rejected");
		r = requestRepo.save(r);
		return r;
	}
	
}
