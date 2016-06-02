package com.lulu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lulu.dao.LoginDAO;
import com.lulu.domain.User;

@Service
public class LoginService {
	@Autowired
	private LoginDAO ldao;
	
	public User validateLogin(String username, String password){
		User user = ldao.findByUsername(username);
		User loggedIn = new User();
		loggedIn.setId(-1);
		if(user != null){
			if (user.getUsername().equals(username)&&user.getPassword().equals(password)){
				loggedIn = user;
			}	
		}
		return loggedIn;
	}
	public User validateRegister(User u) throws Exception{
		ldao.save(u);
		return u;
	}
}
