package com.devandy.service;

import javax.servlet.http.HttpSession;

public interface QnaService {
	
	public void postQuestion(HttpSession session, String title, String contents);
	
}