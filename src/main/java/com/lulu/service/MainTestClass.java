package com.lulu.service;

import java.util.ArrayList;
import java.util.List;

import com.lulu.domain.*;

public class MainTestClass {
	public static ObjectUtilPojo test() {
		
		ObjectUtilPojo ordercupcakes = new ObjectUtilPojo();
		Order order = new Order();
		List<Cupcake> cupcakes = new ArrayList<>();
		for (int i=1; i < 6; i++){
			cupcakes.add(new Cupcake(new Ingredient(10), new Ingredient(7),new Ingredient(6), new Ingredient(2), i,  5.5+i));
		}
		PaymentInfo pay = new PaymentInfo();
		pay.setCcn("1234123412341234");
		pay.setCcv(321);
		pay.setEmail("john@botmail.com");
		order.setPayment(pay);
		ShippingInfo ship = new ShippingInfo();
		ship.setAddress(new Address(1));
		order.setStore(new Store(1));
		order.setShipping(ship);
		order.setStatus("baking");
		order.setTotalCost(100.23);
		order.setType(new OrderType(2));
		ordercupcakes.setCupcakes(cupcakes);
		ordercupcakes.setOrder(order);
		return ordercupcakes;
	}
}
