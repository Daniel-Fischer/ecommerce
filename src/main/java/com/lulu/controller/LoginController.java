package com.lulu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lulu.domain.User;
import com.lulu.service.LoginService;
import com.lulu.service.MailService;

@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="login", method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE})
	public User doLogin(@RequestBody User user, HttpServletResponse response){
		System.out.println("Logging in");
		User currentUser = loginService.validateLogin(user.getUsername(), user.getPassword());
		
		if(currentUser.getId() > 0){
			response.setStatus(200);
		}else{
			response.setStatus(403);
		}
		
		return currentUser;
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE})
	public User doRegister(@RequestBody User user, HttpServletResponse response){
		User currentUser = null;
		try{
			currentUser = loginService.validateRegister(user);
		}catch(Exception e){
		
			response.setStatus(403);
			return null;
		}
		
		response.setStatus(200);
		
		try {
			Map<String, String> parseMap = new HashMap<>();
			parseMap.put("#UN", currentUser.getUsername());
			parseMap.put("#FN", currentUser.getFirstName());
			parseMap.put("#LN", currentUser.getLastName());
			
			String filenName = "C:/Users/Daniel Fischer/my_git_repos/1603_Mar28_Java/Daniel_August_Code/Lulu_Cupcakes/LuluCupcakeService/src/main/resources/registerEmailTemplate.html";
			String parseString = MailService.parseEmailTemplate(parseMap, filenName);
			MailService.SendMail(currentUser.getUsername(), parseString, "Your new Lulu's Cupcake Bakery Account.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return currentUser;
	}


}
