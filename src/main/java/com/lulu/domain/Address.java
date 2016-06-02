package com.lulu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="lulu_address")
public class Address {
	@Id
	@Column(name="a_id")
	@SequenceGenerator(allocationSize=1,name="addressSequence",sequenceName="A_ID_SEQ")
	@GeneratedValue(generator="addressSequence",strategy=GenerationType.SEQUENCE)
	private int id;

	@Column(name="a_street")
	private String street;
	
	@Column(name="a_city")
	private String city;
	
	@Column(name="a_state")
	private String state;
	
	@Column(name="a_zipcode")
	private String zip;

	public Address() {
		super();
	}

	public Address(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	
	
}
