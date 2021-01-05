package com.devandy.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devandy.domain.Qna;
import com.devandy.domain.QnaRepository;
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
		
		Qna qna = new Qna();
		qna.setAuthorId(sessionedUser.getUserId());
		qna.setAuthorName(sessionedUser.getName());
		qna.setCreatedDate(date.format(createdDate));
		qna.setTitle(title);
		qna.setContents(contents);
		
		qnaRepository.save(qna);
	
	}

	@Override
	public void updateQuestion(Long boardId, HttpSession session, String title, String contents) {
		Qna beforeUpdate = qnaRepository.findById(boardId).get();
		User user = HttpSessionUtils.getUserFromSession(session);
		
		// 날짜 생성
		Date createdDate = new Date();
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		
		Qna afterUpdate = new Qna(boardId, beforeUpdate.getAuthorId(), user.getName(), date.format(createdDate), title, contents);
		qnaRepository.save(afterUpdate);
	}

	@Override
	public void deleteQuestion(Long boardId) {
		Qna qna = qnaRepository.findById(boardId).get();
		qnaRepository.delete(qna);
	}

	@Override
	public boolean validationAuthorization(HttpSession session, Qna qna) {
		if(HttpSessionUtils.getUserFromSession(session)!=null
				&& HttpSessionUtils.getUserFromSession(session).getUserId().equals(qna.getAuthorId())) {
			return true;
		} else {
			return false;
		}
	}
}
