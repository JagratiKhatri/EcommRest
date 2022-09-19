package com.example.demo.dto;

import com.sun.istack.NotNull;

public class AddToCartDto {
    private int id;
    private @NotNull int productId;
    private @NotNull int quantity;

    public AddToCartDto() {
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public AddToCartDto(int id, int productId, int quantity) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
	}


  
}