package com.prs.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.prs.db.UserRepo;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepo userRepo;
	
}
