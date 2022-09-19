package com.example.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.CartItemNotExistException;
import com.example.demo.Exception.CustomException;
import com.example.demo.dto.AddToCartDto;
import com.example.demo.dto.CartDto;
import com.example.demo.dto.CartItemDto;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.model.Wishlist;
import com.example.demo.repo.CartRepo;
import com.example.demo.repo.ProductRepo;

@Service
public class CartService {
	 @Autowired
	    private  CartRepo cartRepository;
	 
	 @Autowired
	 private ProductRepo productRepo;
	 	
	 	public Cart getCartById( int cartId) {
	 		return cartRepository.findById(cartId);
	 		
	 	}


		public void addToCart(AddToCartDto addTocartDto, User user) throws CustomException {
			// TODO Auto-generated method stub\
			System.out.println(addTocartDto.getProductId());
			Product p=productRepo.findByPid(addTocartDto.getProductId());
			System.out.println(p.getPid() +"Product Id aa rahi hai ki nahi");
			Cart cart1=cartRepository.findByProduct(p);
			if(cart1==null) {
			Cart cart=new Cart();
//			Product p=productRepo.findByPid(addTocartDto.getProductId());
			cart.setProduct(p);
			cart.setQuantity(addTocartDto.getQuantity());
			cart.setCreatedDate(new Date());
			cart.setUser(user);
			cartRepository.save(cart);
			}
			else {
				throw new CustomException("Product is already in Cart");
			}
		}

		public CartDto listCartItems(User user) {
			// TODO Auto-generated method stub
		List<Cart> cartList=cartRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<CartItemDto> cartItems=new ArrayList<>	();
		double totalCost=0;
		for (Cart cart : cartList) {
			CartItemDto CartItemDto=new CartItemDto(cart);
			totalCost+= CartItemDto.getQuantity()*cart.getProduct().getPrice();
			cartItems.add(CartItemDto);
			
			
		}
		
		CartDto cartDto=new CartDto();
		cartDto.setCartItems(cartItems);
		cartDto.setTotalCost(totalCost);
		return cartDto;
		}

		public void deleteCartItem(int cartItemId, User user) throws CustomException {
			Optional<Cart> optionalCart= Optional.of(cartRepository.findById(cartItemId));
			if(optionalCart.isEmpty()) {
				throw new CustomException("cart item id is invalid"+cartItemId);
			}
			Cart cart=optionalCart.get();
			if(cart.getUser()!=user) {
				throw new CustomException("cart item doesn't belong to user"+cartItemId);
			}
			cartRepository.delete(cart);
		}

	   

}
