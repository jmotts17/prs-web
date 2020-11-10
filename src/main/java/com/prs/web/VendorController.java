package com.prs.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.prs.business.Vendor;
import com.prs.db.VendorRepo;

@CrossOrigin
@RestController
@RequestMapping("/vendors")
public class VendorController {

	@Autowired
	private VendorRepo vendorRepo;
	
	// Get all vendors
	@GetMapping("/")
	public List<Vendor> getAll() {
		return vendorRepo.findAll();
	}
	
	// Get a vendor by id
	@GetMapping("/{id}")
	public Optional<Vendor> getById(@PathVariable int id) {
		return vendorRepo.findById(id);
	}
	
	// Add a vendor
	@PostMapping("/")
	public Vendor addVendor(@RequestBody Vendor v) {
		v = vendorRepo.save(v);
		return v;
	}
	
	// Update a vendor
	@PutMapping("/")
	public Vendor updateVendor(@RequestBody Vendor v) {
		v = vendorRepo.save(v);
		return v;
	}
	
	// Delete a vendor by id
	@DeleteMapping("{id}")
	public Vendor deleteVendor(@PathVariable int id) {
		Optional<Vendor> v = vendorRepo.findById(id);
		if(v.isPresent()) {
			vendorRepo.deleteById(id);
		} else {
			System.out.println("Error - vendor not found for id " + id);
		}
		return v.get();
	}
	
}
