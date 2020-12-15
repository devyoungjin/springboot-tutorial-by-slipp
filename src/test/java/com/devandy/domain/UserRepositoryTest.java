package com.devandy.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryTest extends JpaRepository<User, Long>{
	User findByEmail(String email);
}
