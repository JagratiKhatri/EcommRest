package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="category")
public class Category {
	@Id
	@GeneratedValue
	private int cid;
	
	@OneToMany(mappedBy = "category" ,cascade=CascadeType.ALL)
	private List<Product> products;
	
	private String categoryName;
	public Category(int cid, List<Product> products, String categoryName) {
		super();
		this.cid = cid;
		this.products = products;
		this.categoryName = categoryName;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	@Override
	public String toString() {
		return "Category [cid=" + cid + ", categoryName=" + categoryName + "]";
	}
	public Category() {
		super();
	}
	
	

}
