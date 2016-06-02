package com.lulu.domain;

import java.sql.Timestamp;
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
@Table(name="lulu_order")
public class Order {
	@Id
	@Column(name="o_id")
	@SequenceGenerator(allocationSize=1,name="orderSequence",sequenceName="O_ID_SEQ")
	@GeneratedValue(generator="orderSequence",strategy=GenerationType.SEQUENCE)
	private Integer id;
/*	@OneToMany(mappedBy="order")
	private List<Cupcake> cupcakes;*/
	@ManyToOne
	@JoinColumn(name="o_store")
	private Store store;
	@ManyToOne
	@JoinColumn(name="o_payment")
	private PaymentInfo payment;
	@Column(name="o_status")
	private String status;
	@Column(name="o_ts")
	private Timestamp timestamp;
	@Column(name="o_totalcost")
	private Double totalCost;
	@ManyToOne
	@JoinColumn(name="o_shipping")
	private ShippingInfo shipping;
	@ManyToOne
	@JoinColumn(name="o_type")
	private OrderType type;
	public Order() {
		super();
	}
	
	public Order(Integer id, Double totalCost) {
		super();
		this.id = id;
		this.totalCost = totalCost;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
/*	public List<Cupcake> getCupcakes() {
		return cupcakes;
	}
	public void setCupcakes(List<Cupcake> cupcakes) {
		this.cupcakes = cupcakes;
	}*/
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public PaymentInfo getPayment() {
		return payment;
	}
	public void setPayment(PaymentInfo payment) {
		this.payment = payment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public ShippingInfo getShipping() {
		return shipping;
	}
	public void setShipping(ShippingInfo shipping) {
		this.shipping = shipping;
	}
	public OrderType getType() {
		return type;
	}
	public void setType(OrderType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", store=" + store + ", payment=" + payment + ", status="
				+ status + ", timestamp=" + timestamp + ", totalCost=" + totalCost + ", shipping=" + shipping
				+ ", type=" + type + "]";
	}
}
