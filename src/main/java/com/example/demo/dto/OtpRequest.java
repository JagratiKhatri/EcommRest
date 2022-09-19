package com.example.demo.dto;

public class OtpRequest {
	
	private int otp;

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	@Override
	public String toString() {
		return "OtpRequest [otp=" + otp + "]";
	}

	public OtpRequest(int otp) {
		super();
		this.otp = otp;
	}

	public OtpRequest() {
		super();
	}
	

	

}
