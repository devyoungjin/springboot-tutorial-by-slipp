package com.devandy.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devandy.domain.QnaRepository;
import com.devandy.domain.Qna;
import com.devandy.domain.User;
import com.devandy.util.HttpSessionUtils;

@Service
public class QnaServiceImpl implements QnaService{

	@Autowired
	private QnaRepository qnaRepository;

	@Override
	public void postQuestion(HttpSession session, String title, String contents) {
		
		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		
		// 날짜 생성
		Date createdDate = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		Qna qna = new Qna(sessionedUser.getId(), sessionedUser, date.format(createdDate), title, contents);
		
		qnaRepository.save(qna);
	
	}
}
