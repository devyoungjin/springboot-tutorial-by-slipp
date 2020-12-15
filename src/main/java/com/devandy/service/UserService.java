package com.devandy.service;

import com.devandy.domain.User;

public interface UserService {

	User findByEmail(String email);
	
}
