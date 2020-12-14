package com.devandy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devandy.domain.User;
import com.devandy.domain.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/signup")
	public String signup() {
		return "/user/create";
	}
	
	@PostMapping("/create")
	public String create(User user) {
		userRepository.save(user);
		return "redirect:/user/list";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/user/login";
	}
	
	@PostMapping("/join")
	public String join(String email, String password, HttpSession session) {
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			System.out.println("Login Failed : User Id doesn't exist");
			return "redirect:/user/login";
		} else if(!password.equals(user.getPassword())) {
			System.out.println("Login Failed : Wrong password");
			return "redirect:/user/login";
		} else {
			session.setAttribute("sessionedUser", user);
			System.out.println("Login Success : Hello, "+user.getName());
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/list")
	public String userList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}

}
