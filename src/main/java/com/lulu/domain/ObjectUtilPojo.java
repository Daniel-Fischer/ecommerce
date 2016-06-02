package com.lulu.domain;

import java.util.List;

public class ObjectUtilPojo {
	private Order order;
	private List<Cupcake> cupcakes;
	public ObjectUtilPojo() {
		super();
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public List<Cupcake> getCupcakes() {
		return cupcakes;
	}
	public void setCupcakes(List<Cupcake> cupcakes) {
		this.cupcakes = cupcakes;
	}
	@Override
	public String toString() {
		return "ObjectUtilPojo [order=" + order + ", cupcakes=" + cupcakes + "]";
	}
}
