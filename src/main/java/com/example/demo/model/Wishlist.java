package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="wishlist")
public class Wishlist {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;


	    @Column(name = "created_date")
	    private Date createdDate;
	    @JsonIgnore
	    @ManyToOne
	    @JoinColumn(name = "pid", referencedColumnName = "pid")
	    private Product product;

	    @JsonIgnore
	   
	    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	    @JoinColumn( name = "uid")
	    private User user;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Wishlist() {
			super();
		}

		public Wishlist(Integer id, Date createdDate, Product product, User user) {
			super();
			this.id = id;
			this.createdDate = createdDate;
			this.product = product;
			this.user = user;
		}

		@Override
		public String toString() {
			return "WishList [id=" + id + ", createdDate=" + createdDate + ", product=" + product + ", user=" + user
					+ "]";
		}
	    

}
