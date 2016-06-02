package com.lulu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lulu.domain.Cupcake;
import com.lulu.domain.ObjectUtilPojo;
import com.lulu.domain.Order;
import com.lulu.domain.OrderInfoUtilPojo;
import com.lulu.domain.User;
import com.lulu.service.MailService;
import com.lulu.service.OrdersService;

@RestController
public class OrdersController {
	
	@Autowired
	private OrdersService viewOrdersService;
	
	@RequestMapping(value="orders", method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE}, produces={MediaType.APPLICATION_JSON_VALUE})
	public List<Order> findOrders(@RequestBody User user){
		return viewOrdersService.grabOrdersForUser(user);
	}
	@RequestMapping(value="order/{orderId}", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public List<Cupcake> findCupcakes(@PathVariable(value="orderId") Integer id){
		return viewOrdersService.grabCupcakesForOrder(id);
	}	
	@RequestMapping(value="order/{orderId}/update", method=RequestMethod.POST)
	public void updateCupcake(@PathVariable(value="orderId") Integer id, @RequestBody Order order){
		if (id == order.getId()){
			viewOrdersService.updateOrderStatus(order);
		}
	}
	@RequestMapping(value="order/submit", method=RequestMethod.POST, consumes={MediaType.APPLICATION_JSON_VALUE})
	public void createOrder(@RequestBody ObjectUtilPojo cupcakeOrder){
		System.out.println(cupcakeOrder.getOrder());
		Order order = viewOrdersService.createOrderService(cupcakeOrder);
		User user = cupcakeOrder.getOrder().getPayment().getUser();
		System.out.println(order);
		String id = Integer.toString(order.getId());
		String ship = "N/A";
		if(order.getType().getType().equals("Delivery")){
			ship = order.getShipping().getAddress().getStreet() +", "+order.getShipping().getAddress().getCity() +", "+order.getShipping().getAddress().getState() +", "+order.getShipping().getAddress().getZip();
		}
		String storeloc=order.getStore().getAddress().getStreet() +", "+order.getStore().getAddress().getCity() +", "+order.getStore().getAddress().getState() +", "+order.getStore().getAddress().getZip();
		try {
			Map<String, String> parseMap = new HashMap<>();
			parseMap.put("#OT", order.getType().getType());
			if(user != null){
				parseMap.put("#FN", user.getFirstName());
				parseMap.put("#LN", user.getLastName());
			}else{
				parseMap.put("#FN", "Mr.");
				parseMap.put("#LN", "Anonymous");	
			}
			parseMap.put("#SI", ship);
			parseMap.put("#SL", storeloc);
			parseMap.put("#TC", Double.toString(order.getTotalCost()));
			parseMap.put("#PI", "####-####-####-"+new String(order.getPayment().getCcn()).substring(12));
			parseMap.put("#OID", id);	
			String filenName = "C:/Users/Daniel Fischer/my_git_repos/1603_Mar28_Java/Daniel_August_Code/Lulu_Cupcakes/LuluCupcakeService/src/main/resources/newOrderEmailTemplate.html";
			String parseString = MailService.parseEmailTemplate(parseMap, filenName);
			MailService.SendMail(order.getPayment().getEmail(), parseString, "Your new Lulu's Cupcake Bakery Account.");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	@RequestMapping(value="user/info", method=RequestMethod.POST)
	public OrderInfoUtilPojo grabUserInfoForOrder(@RequestBody User user){//@RequestBody ObjectUtilPojo cupcakeOrder){
		return viewOrdersService.getInfo(user.getId());
	}
	
	
}