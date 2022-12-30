package com.droidboyz.login;

import org.springframework.stereotype.Service;

@Service
public class LoginValidationService {

	public String validate(String email,String pass)
	{
		if(email.isBlank()) return "E-mail cannot be blank";
		if(email.length()<5 || email.length()>60) return "E-mail should be 5-60 chars.";
		if(!isEmail(email)) return "Invalid e-mail id format.";
		if(pass.isBlank()) return "Password field cannot be blank";
		if(pass.length()<6 || pass.length()>50) return "Invalid password";
		return "";
	}
	private boolean isEmail(String email) {
		int i=email.indexOf('@');
		int j=email.lastIndexOf('.');
		if(i<=0 ||j==-1 || j-i<=1 || j==email.length() || i!=email.lastIndexOf('@')) return false;
		String s=email.substring(0,i);
		String m=email.substring(i+1,j);
		String e=email.substring(j+1);
		if(s.toUpperCase().equals(s.toLowerCase()) || !areAllAlphas(m) || !areAllAlphas(e)) return false;
		return true;
	}

	private boolean areAllAlphas(String name) {
		// TODO Auto-generated method stub
		char[] chars=name.toCharArray();
		for(char c:chars)
			if(!(c>='a' && c<='z' || c>='A' && c<='Z')) return false;
		return true;
		}
}
