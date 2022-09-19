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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.CustomException;
import com.example.demo.Exception.InValidTokenException;
import com.example.demo.dto.AddToCartDto;
import com.example.demo.dto.AddToWishList;
import com.example.demo.dto.WishListDto;
import com.example.demo.model.User;
import com.example.demo.service.CartService;
import com.example.demo.service.UserDao;
import com.example.demo.service.WishListService;

@RestController
@RequestMapping("/wishlist")
@CrossOrigin(origins = "http://localhost:3000/")
public class WishListController {
	
	@Autowired
	private WishListService wishlistService;
	
	@Autowired
	private UserDao userService;
	// post cart api
	
	
	@PostMapping("/addToWishList/{token}")
	public ResponseEntity< ?> addToWishList(@RequestBody AddToWishList addToWishList , @PathVariable String token) throws InValidTokenException, CustomException{
		try {	
		User user=userService.findbyToken(token);
			 wishlistService.addToWishList(addToWishList , user);
			 
			 return new ResponseEntity<>("Product is added to wishlist Susscessfully",HttpStatus.CREATED);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	
	}
	@GetMapping("/{token}")
	public WishListDto getAllWishlistItems(@PathVariable String token) throws InValidTokenException{
		User user=userService.findbyToken(token);
		return wishlistService.listofwishlistItems(user);
	}
	


	@DeleteMapping("/delete/{wishlistItemId}/{token}")
	public ResponseEntity<?> deleteWishListItem(@PathVariable int wishlistItemId,@PathVariable String token){
		try {
		User user=userService.findbyToken(token);
		wishlistService.deleteWishListItem(wishlistItemId,user);
		return new ResponseEntity<String>("Product is deleted from wishlist",HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
}
