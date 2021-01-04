package com.devandy.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devandy.domain.Qna;
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
	
	/**
	 * 게시글 목록 조회
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("question", qnaRepository.findAll());
		return "/";
	}
	
	/**
	 * 게시글 작성 페이지 요청
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/ask")
	public String qnaForm(HttpSession session, Model model) {
		
		model.addAttribute("user",HttpSessionUtils.getUserFromSession(session));
		
		if(HttpSessionUtils.isLoginUser(session)) {
			return "/qna/ask";
		} else {
			return "/user/login";
		}
	}
	
	/**
	 *  게시글 작성 
	 * @param session
	 * @param title
	 * @param contents
	 * @return
	 */
	@PostMapping("/post")
	public String ask(HttpSession session, String title, String contents) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/user/login";
		}
		
		qnaService.postQuestion(session, title, contents);
		
		return "redirect:/";
	}
	
	/**
	 *  게시글 상세조회 요청
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/{id}")
	public String showQna(@PathVariable Long id, Model model) {
		Qna qna = qnaRepository.findById(id).get();
		model.addAttribute("question", qna);
		return "/qna/detail";
	}
	
}
