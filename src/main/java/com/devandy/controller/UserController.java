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

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService UserService;
	
	@GetMapping("/createForm")
	public String createForm() {
		return "/user/create";
	}
	
	@PostMapping("/create")
	public String create(User user) {
		userRepository.save(user);
		System.out.println(user);
		return "redirect:/user/list";
	}
	
	@ResponseBody
	@PostMapping(value="/emailChk")
	public int emailCheck(@RequestBody String email) {
		int result = 0;
		
		if(UserService.findByEmail(email)!=null) {
			result = 1;
		} else {
			result = 0;
		}
		
		return result;
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
		
		// 비밀번호 없으면 이전 비밀번호 그대로 사용
		if(updatedUser.getPassword()==null || updatedUser.getPassword()=="") {
			updatedUser.setPassword(user.getPassword());
		}
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
