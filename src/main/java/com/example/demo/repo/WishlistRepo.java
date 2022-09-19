package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.WishListItem;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.model.Wishlist;

@Repository
public interface WishlistRepo extends JpaRepository<Wishlist, Integer>{

	public List<WishListItem> findAllByUserOrderByCreatedDateDesc(User user);

	

	public Wishlist findByProduct(Product p);

}
