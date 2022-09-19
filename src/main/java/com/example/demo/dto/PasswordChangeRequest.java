package com.example.demo.dto;

public class PasswordChangeRequest {
	String newPassword;
	String confirmPassword;
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public PasswordChangeRequest(String newPassword, String confirmPassword) {
		super();
		this.newPassword = newPassword;
		this.confirmPassword = confirmPassword;
	}
	@Override
	public String toString() {
		return "PasswordChangeRequest [newPassword=" + newPassword + ", confirmPassword=" + confirmPassword + "]";
	}
	public PasswordChangeRequest() {
		super();
	}
	

}
