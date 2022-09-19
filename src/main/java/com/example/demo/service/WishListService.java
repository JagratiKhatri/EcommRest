package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.CustomException;
import com.example.demo.dto.AddToWishList;
import com.example.demo.dto.CartDto;
import com.example.demo.dto.CartItemDto;
import com.example.demo.dto.WishListDto;
import com.example.demo.dto.WishListItem;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;

import com.example.demo.model.Wishlist;
import com.example.demo.repo.ProductRepo;
import com.example.demo.repo.WishlistRepo;




@Service
public class WishListService {
	
	@Autowired
	private WishlistRepo wishlistRepo;
	
	
	@Autowired
	private ProductRepo productrepo;

	public void addToWishList(AddToWishList addToWishList, User user) throws CustomException {
		Product p=productrepo.findByPid(addToWishList.getProductId());
		
		Wishlist wishlist=wishlistRepo.findByProduct(p);
		if(wishlist==null) {
		
		Wishlist wish=new Wishlist();
//		Product p=productrepo.findByPid(addToWishList.getProductId());
	
		wish.setCreatedDate(new Date());
		wish.setProduct(p);
		wish.setUser(user);
		wishlistRepo.save(wish);
		}
		else {
			throw new CustomException("Product is already in Wishlist");
		}
		
	}
	public WishListDto listofwishlistItems(User user) {
		// TODO Auto-generated method stub
		List<Wishlist> wishlist =new ArrayList<>();
		List<WishListItem> wishlistItems=wishlistRepo.findAllByUserOrderByCreatedDateDesc(user);
		for (Wishlist wishlist2 : wishlist) {
			WishListItem wishlistItem=new WishListItem(wishlist2);
			
			wishlistItems.add(wishlistItem);
		}
		WishListDto wishlistDto=new WishListDto();
		wishlistDto.setWishlistItems(wishlistItems);
		return wishlistDto;
	}

	public void deleteWishListItem(int wishlistItemId, User user) throws CustomException {
		Optional<Wishlist> optionalWishlist=wishlistRepo.findById(wishlistItemId);
		if(optionalWishlist.isEmpty()) {
			throw new CustomException("Wishlist item id is invalid"+wishlistItemId);
			
		}
		Wishlist wishlist=optionalWishlist.get();
		if(wishlist.getUser()!=user) {
			throw new CustomException("Wishlist doesn't belong to user"+wishlistItemId);
		}
		wishlistRepo.delete(wishlist);
		
	}
	
}
