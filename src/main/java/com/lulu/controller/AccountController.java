package com.lulu.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lulu.domain.User;
import com.lulu.service.AccountService;

@RestController
public class AccountController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value="account", method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
	public void doLogin(@RequestBody User user, HttpServletResponse response){
		if (accountService.editAccount(user)){
			response.setStatus(200);
		}else{
			response.setStatus(403);
		}
	}
}
