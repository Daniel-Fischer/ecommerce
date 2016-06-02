package com.lulu.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="lulu_store")
public class Store {
	
	@Id
	@Column(name="s_id")
	private Integer id;
	@ManyToOne
	@JoinColumn(name="s_address")
	private Address address;
/*	@OneToMany(mappedBy="store")
	private List<Order> orders;*/
	public Store() {
		super();
	}
	public Store(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
/*	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}*/
	@Override
	public String toString() {
		return "Store [id=" + id + ", address=" + address + "]";
	}
	
}
