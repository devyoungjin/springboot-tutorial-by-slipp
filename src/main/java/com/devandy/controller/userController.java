package com.devandy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.devandy.domain.User;

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
	public String create(User user) {
		System.out.println(user);
		return "redirect:/";
	}

}
