	package com.lulu.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="lulu_payment_info")
public class PaymentInfo {
	@Id
	@Column(name="pi_id")
	@SequenceGenerator(allocationSize=1,name="paymentSequence",sequenceName="PI_ID_SEQ")
	@GeneratedValue(generator="paymentSequence",strategy=GenerationType.SEQUENCE)
	private Integer id;
/*	@OneToMany(mappedBy="payment")
	private List<Order> orders;*/
	@Column(name="pi_ccn")
	private String ccn;
	@Column(name="pi_ccv")
	private Integer ccv;
	@Column(name="pi_email")
	private String email;
	@ManyToOne
	@JoinColumn(name="pi_login")
	private User user;
	public PaymentInfo() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
/*	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}*/
	public String getCcn() {
		return ccn;
	}
	public void setCcn(String ccn) {
		this.ccn = ccn;
	}
	public Integer getCcv() {
		return ccv;
	}
	public void setCcv(Integer ccv) {
		this.ccv = ccv;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "PaymentInfo [id=" + id + ", ccn=" + ccn + ", ccv=" + ccv + ", email=" + email
				+ "]";
	}
}
