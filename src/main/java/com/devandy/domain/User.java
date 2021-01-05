package com.devandy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter @Setter
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String password;
	
	public boolean matchId(Long userId) {
		return this.userId.equals(userId);
	}
	
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
	
	public void update(User updateUser) {
		this.email = updateUser.getEmail();
		this.name = updateUser.getName();
		if(updateUser.getPassword()!=null || updateUser.getPassword().equals("")) {
			this.password = updateUser.getPassword();
		}
	}
	
}
