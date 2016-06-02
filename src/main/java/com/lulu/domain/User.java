package com.lulu.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="lulu_login")
public class User {
	@Id
	@Column(name="l_id")
	@SequenceGenerator(allocationSize=1,name="loginSequence",sequenceName="L_ID_SEQ")
	@GeneratedValue(generator="loginSequence",strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="l_username")
	private String username;
	@Column(name="l_password")
	private String password;
	@Column(name="l_firstname")
	private String firstName;
	@Column(name="l_lastname")
	private String lastName;
/*	@OneToMany(mappedBy="user")
	private List<ShippingInfo> shippinginfo;*/
	public User(){}
	public User(String username, String password, String lastName, String firstName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public User(Integer id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
/*	public List<ShippingInfo> getShippinginfo() {
		return shippinginfo;
	}
	public void setShippinginfo(List<ShippingInfo> shippinginfo) {
		this.shippinginfo = shippinginfo;
	}*/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", Username=" + username + ", Password=" + password + "]";
	}
	
}
