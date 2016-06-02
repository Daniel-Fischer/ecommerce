package com.lulu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="lulu_shipping_info")
public class ShippingInfo {
	@Id
	@Column(name="si_id")
	@SequenceGenerator(allocationSize=1,name="shippingSequence",sequenceName="SI_ID_SEQ")
	@GeneratedValue(generator="shippingSequence",strategy=GenerationType.SEQUENCE)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="si_address")
	private Address address;
	@ManyToOne
	@JoinColumn(name="si_login")
	private User user;
	public ShippingInfo() {
		super();
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "ShippingInfo [id=" + id + ", address=" + address + ", user=" + user + "]";
	}
	
	
}
