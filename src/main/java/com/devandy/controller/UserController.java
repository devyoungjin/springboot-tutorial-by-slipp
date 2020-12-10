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
	
	@GetMapping("/create")
	public String signup() {
		return "create";
	}
	
	@PostMapping("/create")
	public String create(User user) {
		System.out.println(user);
		users.add(user);
		return "redirect:/list";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/list")
	public String userList(Model model) {
		model.addAttribute("users", users);
		return "list";
	}

}
