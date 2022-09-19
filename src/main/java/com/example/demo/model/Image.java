package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import com.sun.istack.NotNull;

@Entity
@Table(name="image")
public class Image {
	@Id
	@GeneratedValue
	private int id;
	@Column(unique=true) @NotNull
	private String imagePath;

	
	public Image(int id, String imagePath) {
		super();
		this.id = id;
		this.imagePath = imagePath;
	}

	public Image() {
		// TODO Auto-generated constructor stub
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
