package com.lulu.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lulu.dao.AddressDAO;
import com.lulu.dao.CupcakeDAO;
import com.lulu.dao.OrderDAO;
import com.lulu.dao.OrderTypeDAO;
import com.lulu.dao.PaymentInfoDAO;
import com.lulu.dao.ShippingInfoDAO;
import com.lulu.dao.StoreDAO;
import com.lulu.domain.Cupcake;
import com.lulu.domain.ObjectUtilPojo;
import com.lulu.domain.Order;
import com.lulu.domain.OrderInfoUtilPojo;
import com.lulu.domain.PaymentInfo;
import com.lulu.domain.User;

@Service
public class OrdersService {
	@Autowired
	private OrderDAO odao;
	@Autowired
	private PaymentInfoDAO pidao;
	@Autowired
	private CupcakeDAO ccdao;
	@Autowired
	private ShippingInfoDAO sidao;
	@Autowired
	private StoreDAO sdao;
	@Autowired
	private OrderTypeDAO otdao;
	@Autowired
	private AddressDAO addao;
	
	public List<Order> grabOrdersForUser(User user){
		//System.out.println("hit grabOrders: "+ user);
		List<PaymentInfo> pilist=pidao.findByUserId(user.getId());
		List<Order> olist = new ArrayList<>();
		for (PaymentInfo pi: pilist){
			olist.addAll(odao.findByPaymentId(pi.getId()));
		}
		System.out.println(olist);
		return olist;
	}
	
	public List<Cupcake> grabCupcakesForOrder(Integer orderid){
		return ccdao.findByOrderId(orderid);
	}
	public void updateOrderStatus(Order order){
		odao.save(order);
	}
	public Order createOrderService(ObjectUtilPojo cupcakeOrder){
		Order order = cupcakeOrder.getOrder();
		pidao.save(order.getPayment());
		if (order.getType().getType().equals("Pick Up")){
			order.getType().setId(1);//(otdao.findOne(1));
		}else{
			order.getType().setId(2);//(otdao.findOne(2));
		}
		System.out.println("hello" + order.getType().getId());
		if (order.getShipping().getAddress() != null){
			order.getShipping().setAddress(addao.save(order.getShipping().getAddress()));
			order.setShipping(sidao.save(order.getShipping()));
		}else{
			order.setShipping(null);
		}
		//order.setStore(sdao.findById(order.getStore().getId()));
		//System.out.println(order.getStore());
		order = odao.save(order);
		List<Cupcake> cupcakes= cupcakeOrder.getCupcakes();
		for(Cupcake cupcake:cupcakes){
			cupcake.setOrder(order);
		}
		ccdao.save(cupcakes);
		return order;
	}
	public OrderInfoUtilPojo getInfo(Integer id){
		OrderInfoUtilPojo oiup = new OrderInfoUtilPojo();
		if (id != null){
			oiup.setPayment(pidao.findByUserId(id));
			oiup.setShipping(sidao.findByUserId(id));
		}
		oiup.setStores(sdao.findAll());
		return oiup;
	}
}
