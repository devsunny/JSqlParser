package com.asksunny.demo.domain;

public class Users implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userid;
	private String firstname;
	private String lastname;
	private String password;

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
