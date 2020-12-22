package com.devandy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devandy.domain.QnaRepository;
import com.devandy.service.QnaService;
import com.devandy.util.HttpSessionUtils;

@Controller
@RequestMapping("/qna")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Autowired
	private QnaRepository qnaRepository;
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("question", qnaRepository.findAll());
		return "/qna/list";
	}
	
	@GetMapping("/ask")
	public String qnaForm(HttpSession session, Model model) {
		
		model.addAttribute("user",HttpSessionUtils.getUserFromSession(session));
		
		if(HttpSessionUtils.isLoginUser(session)) {
			return "/qna/ask";
		} else {
			return "/user/login";
		}
	}
	
	@PostMapping("/post")
	public String ask(HttpSession session, String title, String contents) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/user/login";
		}
		
		qnaService.postQuestion(session, title, contents);
		
		return "redirect:/qna/list";
	}
	
}
