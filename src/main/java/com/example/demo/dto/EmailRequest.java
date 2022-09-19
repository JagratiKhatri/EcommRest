package com.example.demo.dto;




public class EmailRequest {
	String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmailRequest(String email) {
		super();
		this.email = email;
	}

	public EmailRequest() {
		super();
	}

	@Override
	public String toString() {
		return "EmailRequest [email=" + email + "]";
	}
	

}
