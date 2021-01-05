package com.devandy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devandy.domain.User;
import com.devandy.domain.UserRepository;
import com.devandy.service.UserService;
import com.devandy.util.HttpSessionUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/createForm")
	public String createForm() {
		return "/user/create";
	}
	
	@PostMapping("/create")
	public String create(User user) {
		userService.saveUserAfterCreated(user);
		return "redirect:/user/list";
	}
	
	@ResponseBody
	@PostMapping(value="/emailChk")
	public boolean emailCheck(@RequestBody String email) {
		return userService.isExistEmail(email);
	}
	
	@GetMapping("/list")
	public String userList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String join(String email, String password, HttpSession session) {
		User user = userRepository.findByEmail(email);
		
		if(userService.validationLogin(email, password)) {
			session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
			return "redirect:/";
		} else {
			return "redirect:/user/loginForm";
		}
	}
	
	@GetMapping("/updateForm/{userId}")
	public String updateForm(@PathVariable Long userId, Model model, HttpSession session) {
		
		if(userService.checkAuthorizationForUpdate(userId, session)) {
			model.addAttribute("user", HttpSessionUtils.getUserFromSession(session));
			return "user/update";
		} else {
			return "redirect:/user/loginForm";
		}
	}
	
	@PostMapping("/update/{userId}")
	public String update(@PathVariable Long userId, User updatedUser) {
		userService.saveUserAfterUpdate(userId, updatedUser);
		return "redirect:/user/list";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		return "redirect:/";
	}

}
