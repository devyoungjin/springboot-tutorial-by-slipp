package com.devandy.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;

import com.devandy.domain.User;
import com.devandy.domain.UserRepositoryTest;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(Lifecycle.PER_CLASS)
public class UserControllerTest {

	@Autowired
	UserRepositoryTest userRepositoryTest;
	
	@BeforeAll
	void beforeAll() {
		System.out.println("========== 통합 테스트 전 ==========");
	}

	@Test
	@DisplayName("회원가입 테스트")
	void createUser() {
		// given
		User user = new User();
		user.setId(1L);
		user.setEmail("dev.youngjinmo@gmail.com");
		user.setPassword("1234567");
		user.setName("DevAndy");
		
		// when
		userRepositoryTest.save(user);
		
		// then
		User savedUser = userRepositoryTest.findByEmail(user.getEmail());
		System.out.println(savedUser.toString());
	}
	
	@AfterAll
	void afterAll() {
		System.out.println("========== 통합 테스트 후 ==========");
	}
	
}
