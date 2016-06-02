package com.lulu.domain;

import java.util.List;

public class OrderInfoUtilPojo {
	private List<PaymentInfo> payment;
	private List<Store> stores;
	private List<ShippingInfo> shipping;
	public List<PaymentInfo> getPayment() {
		return payment;
	}
	public void setPayment(List<PaymentInfo> payment) {
		this.payment = payment;
	}
	public List<Store> getStores() {
		return stores;
	}
	public void setStores(List<Store> stores) {
		this.stores = stores;
	}
	public List<ShippingInfo> getShipping() {
		return shipping;
	}
	public void setShipping(List<ShippingInfo> shipping) {
		this.shipping = shipping;
	}
	
}
