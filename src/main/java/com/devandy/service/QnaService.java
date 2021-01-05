package com.devandy.service;

import javax.servlet.http.HttpSession;

import com.devandy.domain.Qna;

public interface QnaService {
	
	/**
	 *  게시글 작성
	 * @param session
	 * @param title
	 * @param contents
	 */
	public void postQuestion(HttpSession session, String title, String contents);
	
	/**
	 *  권한 체크
	 * @param session
	 * @return
	 */
	public boolean validationAuthorization(HttpSession session, Qna qna); 
	
	/**
	 * 게시글 수정
	 * @param id
	 * @param session
	 * @param title
	 * @param contents
	 */
	public void updateQuestion(Long id, HttpSession session, String title, String contents);
	
	/**
	 * 게시글 삭제
	 * @param id
	 * @param session
	 */
	public void deleteQuestion(Long id);
	
}