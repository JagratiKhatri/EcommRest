package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.InValidTokenException;
import com.example.demo.dto.AddToCartDto;
import com.example.demo.dto.CartDto;
import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.CartService;
import com.example.demo.service.UserDao;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "http://localhost:3000/")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private UserDao userService;
	// post cart api
	
	
	@PostMapping("/addToCart/{token}")
	public ResponseEntity< ?> addTocart(@RequestBody AddToCartDto addTocartDto , @PathVariable String token) throws InValidTokenException{
		try {	
		User user=userService.findbyToken(token);
		System.out.println("controller");
			 cartService.addToCart(addTocartDto , user);
			 
			 return new ResponseEntity<>("Product is added to cart Susscessfully",HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.CREATED);
		}
	
	}
	
	
	// get all cart items for a user
	@GetMapping("/{token}")
	public CartDto getCartItems(@PathVariable String token) throws InValidTokenException{
		
		User user=userService.findbyToken(token);
		CartDto cartDto=cartService.listCartItems(user);
		return cartDto;
	}
	
	
	// delete a cart items;for a user
	
	@DeleteMapping("/delete/{cartItemId}/{token}")
	public ResponseEntity<?> getCartDetails(@PathVariable  int cartItemId, @PathVariable String token ) throws InValidTokenException, CustomException {
		try {
		User user=userService.findbyToken(token);
		cartService.deleteCartItem(cartItemId,user);
		
		return new ResponseEntity<String>("Item is deleted Succesfully",HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	

}
