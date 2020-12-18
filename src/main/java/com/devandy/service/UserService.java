package com.devandy.service;

import javax.servlet.http.HttpSession;

import com.devandy.domain.User;

public interface UserService {

	public void saveUserAfterCreated(User user);
	
	public void saveUserAfterUpdate(User user);
	
	public boolean validationLogin(String email, String password);
	
	public boolean checkAuthorizationForUpdate(HttpSession session, Long id);
	
	public boolean isExistEmail(String email);
	
}
