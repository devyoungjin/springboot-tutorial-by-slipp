package com.devandy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.devandy.domain.QnaRepository;

@Controller
public class MainController {

	@Autowired
	QnaRepository qnaRepository;
	
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("question", qnaRepository.findAll());
		return "/qna/list";
	}
	
}
