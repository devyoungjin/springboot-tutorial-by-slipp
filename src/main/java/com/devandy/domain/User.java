package com.devandy.domain;

public class User {
	public String userEmail;
	public String userName;
	public String userPassword;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	@Override
	public String toString() {
		return "user [userEmail=" + userEmail + ", userName=" + userName + ", userPassword=" + userPassword + "]";
	}
	
	

}
