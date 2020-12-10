package com.devandy.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devandy.domain.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private List<User> users = new ArrayList<User>();
	
	@GetMapping("/signup")
	public String signup() {
		return "/user/create";
	}
	
	@PostMapping("/create")
	public String create(User user) {
		users.add(user);
		return "redirect:/user/list";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}
	
	@GetMapping("/list")
	public String userList(Model model) {
		model.addAttribute("users", users);
		return "/user/list";
	}

}
