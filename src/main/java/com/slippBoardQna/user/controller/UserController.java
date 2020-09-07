package com.slippBoardQna.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }
    
    @GetMapping("/signup")
    public String signup() {
        return "/user/signup";
    }
    
    @GetMapping("/logout")
    public String logout() {
        return "/user/logout";
    }
    
    @GetMapping("/list")
    public String list() {
        return "/user/list";
    }
}
