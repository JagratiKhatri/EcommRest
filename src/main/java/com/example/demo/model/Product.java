package com.example.demo.model;

 

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="product")
public class Product {
	@Id
	@GeneratedValue
	private int pid;
	private String name;
	private double price;
	private String description;
	private String imageName;
	private double rating;
	private int noOfUserRated;
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cid", referencedColumnName = "cid" , insertable = false, updatable = false)
	private Category category;
	
	
	    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	    private List<Cart> carts;
	    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	    private List<Wishlist> wishlist;

	
	public List<Cart> getCarts() {
			return carts;
		}
		public void setCarts(List<Cart> carts) {
			this.carts = carts;
		}
		public List<Wishlist> getWishlist() {
			return wishlist;
		}
		public void setWishlist(List<Wishlist> wishlist) {
			this.wishlist = wishlist;
		}
	
	public Product(int pid, String name, double price, String description, String imageName, double rating,
			int noOfUserRated, Category category, List<Cart> carts, List<Wishlist> wishlist) {
		super();
		this.pid = pid;
		this.name = name;
		this.price = price;
		this.description = description;
		this.imageName = imageName;
		this.rating = rating;
		this.noOfUserRated = noOfUserRated;
		this.category = category;
		this.carts = carts;
		this.wishlist = wishlist;
	}
	public Product() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Cart> getCart() {
		return carts;
	}
	public void setCart(Cart cart) {
		this.carts = (List<Cart>) cart;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getNoOfUserRated() {
		return noOfUserRated;
	}
	public void setNoOfUserRated(int noOfUserRated) {
		this.noOfUserRated = noOfUserRated;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", imageName=" + imageName + ", rating=" + rating + ", noOfUserRated=" + noOfUserRated + ", category="
				+ category + ", cart=" + carts + "]";
	}
	
	

}
