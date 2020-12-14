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
	private Long id;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private String password;
	
	public boolean matchId(Long id) {
		return this.id.equals(id);
	}
	
	public boolean matchPassword(Long password) {
		return this.password.equals(password);
	}
	
	public void update(User updateUser) {
		this.email = updateUser.email;
		this.name = updateUser.name;
		if(updateUser.password!=null || updateUser.password.equals("")) {
			this.password = updateUser.password;
		}
	}
	
}
