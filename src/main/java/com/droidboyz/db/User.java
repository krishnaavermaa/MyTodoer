package com.droidboyz.db;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class User {
	private final long max=9999999999L;
	
	private static int userCount;
	
	private int uid;
	@Length(min = 2,max = 40,message = "Name should be 2-40 chars.")
	@Pattern(regexp = "([a-zA-Z]+[ ]?)*", message = "Invalid Name. Only Alphabets and space allowed.")
	private String name;
	@Range(min = 1000000000L, max = max, message = "Invalid 10 digit Phone no. Please recheck.")
	private Long phone;
	@Length(min=6, max=30, message = "Password should be 6-30 chars.")
	private String password;
	@Email(message = "Invalid e-mail id format.")
	private String email;
	
	public User() {
		// TODO Auto-generated constructor stub
		this.uid=++userCount;
	}

	public int getUid() {
		return uid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
