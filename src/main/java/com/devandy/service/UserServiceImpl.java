package com.devandy.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devandy.domain.User;
import com.devandy.domain.UserRepository;
import com.devandy.util.HttpSessionUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void saveUserAfterCreated(User user) {
		userRepository.save(user);
		System.out.println(user.toString());
	}
	
	@Override
	public void saveUserAfterUpdate(User updatedUser) {
		User user = userRepository.findByEmail(updatedUser.getEmail());
		System.out.println("***** Before update : "+user.toString());
		
		// 비밀번호 없으면 이전 비밀번호 그대로 사용
		if(updatedUser.getPassword()==null || updatedUser.getPassword()=="") {
			updatedUser.setPassword(user.getPassword());
		}
		
		user.update(updatedUser);
		userRepository.save(user);
		
		System.out.println("***** After update : "+user.toString());
	}
	
	@Override
	public boolean validationLogin(String email, String password) {
		User user = userRepository.findByEmail(email);
		if(user==null) {
			System.out.println("***** Login Failed : User Id doesn't exist");
			return false;
		} else if(!user.matchPassword(password)) {
			System.out.println("***** Login Failed : Wrong password");
			return false;
		} else {
			System.out.println("***** Login Success : Hello, "+user.getName());
			return true;
		}
	}

	@Override
	public boolean checkAuthorizationForUpdate(HttpSession session, Long id) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			System.out.println("***** You must be logged in.");
			return false;
		} else if(!HttpSessionUtils.getUserFromSession(session).matchId(id)){
			System.out.println("***** 자신의 정보만 수정할 수 있습니다.");
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
		} else {
			return true;
		}
	}

	@Override
	public boolean isExistEmail(String email) {
		if(userRepository.findByEmail(email)!=null) {
			return false;
		} else {
			return true;
		}
	}

}
