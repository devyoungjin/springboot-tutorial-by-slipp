package com.devandy.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devandy.domain.QnaRepository;
import com.devandy.domain.Question;
import com.devandy.domain.User;
import com.devandy.util.HttpSessionUtils;

@Service
public class QnaServiceImpl implements QnaService{

	@Autowired
	private QnaRepository qnaRepository;

	@Override
	public void postQuestion(HttpSession session, String title, String contents) {
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		
		Question question = new Question(sessionedUser.getId(), sessionedUser.getName(), title, contents);
		
		qnaRepository.save(question);
	}
}
