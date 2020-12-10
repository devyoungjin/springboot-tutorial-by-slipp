package com.devandy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter @Setter
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String password;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
}
