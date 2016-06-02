package com.lulu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="lulu_ingredient")
public class Ingredient {
	@Id
	@Column(name="i_id")
	private Integer id;
	@Column(name="i_desc")
	private String description;
	@Column(name="i_type")
	private String type;
	@Column(name="i_cost_mod")
	private Double costMod;
	@Transient
	private String src;
	public Ingredient() {
		super();
	}
	public Ingredient(Integer id) {
		super();
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getCostMod() {
		return costMod;
	}
	public void setCostMod(Double costMod) {
		this.costMod = costMod;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	
}
