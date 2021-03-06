package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.prs.business.LineItem;
import com.prs.business.Request;
import com.prs.business.Product;
import com.prs.db.LineItemRepo;
import com.prs.db.RequestRepo;

@CrossOrigin
@RestController
@RequestMapping("/line-items")
public class LineItemController {
	
	@Autowired
	private LineItemRepo lineItemRepo;
	
	@Autowired
	private RequestRepo requestRepo;
	
	// Get all LineItems
	@GetMapping("/")
	public List<LineItem> getAll() {
		return lineItemRepo.findAll();
	}
	
	// Get a LineItem by id
	@GetMapping("/{id}")
	public Optional<LineItem> getById(@PathVariable int id) {
		return lineItemRepo.findById(id);
	}
	
	// Add a LineItem
	@PostMapping("/")
	public LineItem addLineItem(@RequestBody LineItem l) {
		l = lineItemRepo.save(l);
		recalculateTotal(l);
		return l;
	}
	
	// Update a LineItem
	@PutMapping("/")
	public LineItem updateLineItem(@RequestBody LineItem l) {
		l = lineItemRepo.save(l);
		recalculateTotal(l);
		return l;
	}
	
	// Delete a LineItem by id
	@DeleteMapping("{id}")
	public LineItem deleteLineItem(@PathVariable int id) {
		Optional<LineItem> l = lineItemRepo.findById(id);

		if (l.isPresent()) {
			lineItemRepo.deleteById(id);
			recalculateTotal(l.get());
		} else {
			System.out.println("Error - line item not found for id " + id);
		}
		return l.get();
	}
	
	// Get all LineItems by Request ID
	@GetMapping("/lines-for-pr/{id}")
	public List<LineItem> getAllLineItemsByRequestId(@PathVariable int id) {
		return lineItemRepo.findByRequestId(id);
	}
	
	// Recalculates the Requests total based on LineItem Add, Update or Delete
	public void recalculateTotal(LineItem li) {	
		List<LineItem> lineItems = lineItemRepo.findByRequestId(li.getRequest().getId());
		
		double total = 0.0;
		for(LineItem lineItem : lineItems) {
			Product p = lineItem.getProduct();
			total += (p.getPrice() * lineItem.getQuantity());
		}
		
		Request request = li.getRequest();
		request.setTotal(total);
		requestRepo.save(request);
	}
	
}
