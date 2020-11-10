package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.prs.business.LineItem;
import com.prs.db.LineItemRepo;

@CrossOrigin
@RestController
@RequestMapping("/lineitems")
public class LineItemController {
	
	@Autowired
	private LineItemRepo lineItemRepo;
	
	// Get all lineitems
	@GetMapping("/")
	public List<LineItem> getAll() {
		return lineItemRepo.findAll();
	}
	
	// Get a lineitem by id
	@GetMapping("/{id}")
	public Optional<LineItem> getById(@PathVariable int id) {
		return lineItemRepo.findById(id);
	}
	
	// Add a lineitem
	@PostMapping("/")
	public LineItem addLineItem(@RequestBody LineItem l) {
		l = lineItemRepo.save(l);
		return l;
	}
	
	// Update a lineitem
	@PutMapping("/")
	public LineItem updateLineItem(@RequestBody LineItem l) {
		l = lineItemRepo.save(l);
		return l;
	}
	
	// Delete a lineitem by id
	@DeleteMapping("{id}")
	public LineItem deleteLineItem(@PathVariable int id) {
		Optional<LineItem> l = lineItemRepo.findById(id);
		if (l.isPresent()) {
			lineItemRepo.deleteById(id);
		} else {
			System.out.println("Error - line item not found for id " + id);
		}
		return l.get();
	}
}
