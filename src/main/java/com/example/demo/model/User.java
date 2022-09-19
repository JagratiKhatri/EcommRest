package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue
	private int id;
	private String fname;
	private String lname;
	@Column(unique=true )@NotNull
	private String email;
	@Column(unique=true) @NotNull
	private String username;
	private String password;
	private String role;
	private Boolean isAccountLocked;
	private Boolean isCredentialsNonExpired;
	private Boolean isEnabled;
	private String PhoneNumber ;
	private String image;
	

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Address> address;

	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String string) {
		this.image = string;
	}
	public String getPhoneNumber() {
		return PhoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Boolean getIsAccountLocked() {
		return isAccountLocked;
	}
	public void setIsAccountLocked(Boolean isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}
	public Boolean getIsCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}
	public void setIsCredentialsNonExpired(Boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public User(int id, String fname, String lname, String email, String username, String password, String role,
			Boolean isAccountLocked, Boolean isCredentialsNonExpired, Boolean isEnabled, String phoneNumber,
			String image) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.isAccountLocked = isAccountLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
		PhoneNumber = phoneNumber;
		this.image = image;
	}
	public User() {
		super();
	}
	
}