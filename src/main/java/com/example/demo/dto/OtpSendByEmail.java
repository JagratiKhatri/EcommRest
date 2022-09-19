package com.example.demo.dto;

public class OtpSendByEmail {
	int otpSendByEmail;
	String email;


	public OtpSendByEmail() {
		super();
	}

	public OtpSendByEmail(int otpSendByEmail, String email) {
		super();
		this.otpSendByEmail = otpSendByEmail;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getOtpSendByEmail() {
		return otpSendByEmail;
	}

	public void setOtpSendByEmail(int otpSendByEmail) {
		this.otpSendByEmail = otpSendByEmail;
	}

	@Override
	public String toString() {
		return "OtpSendByEmail [otpSendByEmail=" + otpSendByEmail + "]";
	}

	
	

}
