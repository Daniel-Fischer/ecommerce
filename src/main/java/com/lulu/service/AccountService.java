package com.lulu.service;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lulu.dao.LoginDAO;
import com.lulu.dao.OrderDAO;
import com.lulu.dao.PaymentInfoDAO;
import com.lulu.domain.PaymentInfo;
import com.lulu.domain.User;

@Service
public class AccountService {
	@Autowired
	private LoginDAO ldao;
	@Autowired
	private OrderDAO odao;
	@Autowired
	private PaymentInfoDAO pidao;
	
	
	public boolean editAccount(User user){
		try{
			//User oldUser = ldao.findOne(user.getId());
			ldao.save(user);
			/*for (PaymentInfo payment : pidao.findByUserId(oldUser.getId())){
				payment.setEmail(user.getUsername());
				pidao.save(payment);
			}*/
			return true;
		}catch(HibernateException e){
			return false;
			
		}
	
	}
}
