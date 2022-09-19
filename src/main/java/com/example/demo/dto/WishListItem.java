package com.example.demo.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.Wishlist;
import com.sun.istack.NotNull;

public class WishListItem {
	@Id
	@GeneratedValue
	private int id;
	  private @NotNull Product product;

	  
	    public WishListItem (Wishlist wishlist) {
	        this.setId(wishlist.getId());
	        
	        this.setProduct(wishlist.getProduct());
	    }


		public WishListItem() {
			super();
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public Product getProduct() {
			return product;
		}


		public void setProduct(Product product) {
			this.product = product;
		}
	

}
