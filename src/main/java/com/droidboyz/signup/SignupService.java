package com.droidboyz.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.droidboyz.db.FakeDB;
import com.droidboyz.db.User;

@Service
public class SignupService {

	@Autowired
	FakeDB db;
	
	protected String createUser(User user)
	{
		String name=user.getName(), email=user.getEmail();
		if(db.checkExisting(email)) return "User already exists with the same email ID.";
		name+=" ";
		String nm="";
		while(!name.isBlank())
		{
			int tmp=name.indexOf(' ');
			nm+=String.valueOf(name.charAt(0)).toUpperCase()
					+name.substring(1,tmp+1);
			name=name.substring(tmp+1);
		}
		name=nm;
		nm=null;
		name=name.trim();
		user.setName(name);
		if(!db.createUser(user)) {
			return "Database error. Unable to create user.";
		}
		return "";
	}
}
