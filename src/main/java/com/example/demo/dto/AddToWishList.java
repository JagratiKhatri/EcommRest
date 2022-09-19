package com.example.demo.dto;

import com.sun.istack.NotNull;

public class AddToWishList {
	private Integer id;
    private @NotNull Integer productId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public AddToWishList(Integer id, Integer productId) {
		super();
		this.id = id;
		this.productId = productId;
	}
	public AddToWishList() {
		super();
	}
    

}
