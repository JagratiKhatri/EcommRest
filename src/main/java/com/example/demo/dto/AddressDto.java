package com.example.demo.dto;

import com.sun.istack.NotNull;

public class AddressDto {
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
	
	private int uid;

	public AddressDto(int addressId, String name, String phoneNumber, String pincode, String locality, String address,
			String city, String state, String landmark, int uid) {
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
		this.uid = uid;
	}

	public AddressDto() {
		super();
	}

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

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	

}
