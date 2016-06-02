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
@Table(name="lulu_cupcake")
public class Cupcake {
	@Id
	@Column(name="cc_id")
	@SequenceGenerator(allocationSize=1,name="CCSequence",sequenceName="CC_ID_SEQ")
	@GeneratedValue(generator="CCSequence",strategy=GenerationType.SEQUENCE)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="cc_orderid")
	private Order order;
	@ManyToOne
	@JoinColumn(name="cc_batter")
	private Ingredient batter;
	@ManyToOne
	@JoinColumn(name="cc_frosting")
	private Ingredient frosting;
	@ManyToOne
	@JoinColumn(name="cc_sprinkle")
	private Ingredient sprinkle;
	@ManyToOne
	@JoinColumn(name="cc_cup")
	private Ingredient cup;
	@Column(name="cc_quantity")
	private Integer qty;
	@Column(name="cc_cost")
	private Double cost;
	public Cupcake() {
		super();
	}
	
	public Cupcake(Ingredient batter, Ingredient frosting, Ingredient sprinkle, Ingredient cup,
			Integer quantity, Double cost) {
		super();
		this.batter = batter;
		this.frosting = frosting;
		this.sprinkle = sprinkle;
		this.cup = cup;
		this.qty = quantity;
		this.cost = cost;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Ingredient getBatter() {
		return batter;
	}
	public void setBatter(Ingredient batter) {
		this.batter = batter;
	}
	public Ingredient getFrosting() {
		return frosting;
	}
	public void setFrosting(Ingredient frosting) {
		this.frosting = frosting;
	}
	public Ingredient getSprinkle() {
		return sprinkle;
	}
	public void setSprinkle(Ingredient sprinkle) {
		this.sprinkle = sprinkle;
	}
	public Ingredient getCup() {
		return cup;
	}
	public void setCup(Ingredient cup) {
		this.cup = cup;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer quantity) {
		this.qty = quantity;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "Cupcake [id=" + id + ", batter=" + batter + ", frosting=" + frosting + ", sprinkle=" + sprinkle
				+ ", cup=" + cup + ", quantity=" + qty + ", cost=" + cost + "]";
	}
}
