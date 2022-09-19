package com.example.demo.dto;

import java.util.List;

public class WishListDto {
	
	private List<WishListItem> wishlistItems;

	public List<WishListItem> getWishlistItems() {
		return wishlistItems;
	}

	public void setWishlistItems(List<WishListItem> wishlistItems) {
		this.wishlistItems = wishlistItems;
	}

	public WishListDto(List<WishListItem> wishlistItems) {
		super();
		this.wishlistItems = wishlistItems;
	}

	public WishListDto() {
		super();
	}

	

}
