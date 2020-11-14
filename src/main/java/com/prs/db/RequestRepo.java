package com.prs.db;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prs.business.Request;

public interface RequestRepo extends JpaRepository<Request, Integer> {
	
	// Find all Requests in Review Status without the Users ID
	public List<Request> findByUserIdNotAndStatus(int id, String status);
		
}
