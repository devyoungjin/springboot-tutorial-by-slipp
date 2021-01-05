package com.devandy.service;

import javax.servlet.http.HttpSession;

import com.devandy.domain.User;

public interface UserService {

	public void saveUserAfterCreated(User user);
	
	public void saveUserAfterUpdate(Long userId, User user);
	
	public boolean validationLogin(String email, String password);
	
	public boolean checkAuthorizationForUpdate(Long id, HttpSession session);
	
	public boolean isExistEmail(String email);
	
}
