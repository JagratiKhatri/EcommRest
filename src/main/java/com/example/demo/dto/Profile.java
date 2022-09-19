package com.example.demo.dto;

public class Profile {
	
	private String email ,fname,lname;
	private Number mobileNumber;
	private String image;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public Number getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Number mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Profile(String email, String fname, String lname, Number mobileNumber, String image) {
		super();
		this.email = email;
		this.fname = fname;
		this.lname = lname;
		this.mobileNumber = mobileNumber;
		this.image = image;
	}
	
	


}
