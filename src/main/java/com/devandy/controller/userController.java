package com.devandy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class userController {
	
	@GetMapping("/")
	public String main() {
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@PostMapping("/create")
	public String create(String userEmail, String userName, String userPassword) {
		System.out.println("email : "+userEmail+"\nname : "+userName+"\npassword : "+userPassword);
		return "redirect:/";
	}

}
