package com.user.bean;

public class User {
	private String userName;

	public User() {
		super();
	}

	private String userPwdMD5;
	private String userPhone;

	public String getUserName() {
		return userName;
	}

	public User(String userName, String userPwdMD5, String userPhone) {
		super();
		this.userName = userName;
		this.userPwdMD5 = userPwdMD5;
		this.userPhone = userPhone;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwdMD5() {
		return userPwdMD5;
	}

	public void setUserPwdMD5(String userPwdMD5) {
		this.userPwdMD5 = userPwdMD5;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
}
