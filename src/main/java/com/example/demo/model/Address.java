package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name="addresses")
public class Address {
	
	@Id
	@GeneratedValue
	private int addressId;
	
	@NotNull
	private String name;
	@NotNull
	private String phoneNumber;
	@NotNull
	private String pincode;
	@NotNull
	private String locality;
	@NotNull
	private String address;
	@NotNull
	private String city;
	@NotNull
	private String state;
	private String landmark;
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name="id", referencedColumnName = "id" )
	private User user;

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address(int addressId, String name, String phoneNumber, String pincode, String locality, String address,
			String city, String state, String landmark, User user) {
		super();
		this.addressId = addressId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.pincode = pincode;
		this.locality = locality;
		this.address = address;
		this.city = city;
		this.state = state;
		this.landmark = landmark;
		this.user = user;
	}

	public Address() {
		super();
	}
	
	

}
