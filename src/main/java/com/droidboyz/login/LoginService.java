package com.droidboyz.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.droidboyz.db.FakeDB;
import com.droidboyz.db.User;

@Service
public class LoginService {

	@Autowired
	FakeDB db;
	
	public User validateUser(String email, String pass)
	{
		return db.getUser(email, pass);
	}
}
