package com.lulu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="lulu_order_type")
public class OrderType {
	@Id
	@Column(name="ot_id")
	private Integer id;
	@Column(name="ot_type")
	private String type;
	public OrderType() {
		super();
	}
	
	public OrderType(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
