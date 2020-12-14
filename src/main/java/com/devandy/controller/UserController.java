package com.devandy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/list")
	public String userList(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("/login")
	public String loginForm() {
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
	
	@GetMapping("/update/{id}")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {

		User sessionedUser = (User)session.getAttribute("sessionedUser");
		
		if(sessionedUser==null) {
			System.out.println("You must be logged in.");
			return "redirect:/user/login";
		} else if(!id.equals(sessionedUser.getId())){
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
		} else {
			model.addAttribute("user", sessionedUser);
			return "/user/update";
		}
	}
	
	@PostMapping("/{id}/update")
	public String update(@PathVariable Long id, User updatedUser) {
		
		User user = userRepository.findById(id).get();
		System.out.println("Before update : "+user.toString());
		
		user.update(updatedUser);
		userRepository.save(user);
		System.out.println("After update : "+user.toString());
		
		return "redirect:/user/list";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("sessionedUser");
		return "redirect:/";
	}

}
