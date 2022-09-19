package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.InValidTokenException;
import com.example.demo.dto.AddressDto;
import com.example.demo.model.Address;
import com.example.demo.model.User;
import com.example.demo.service.AddressService;
import com.example.demo.service.UserDao;

@RestController
@RequestMapping("/address")
@CrossOrigin(origins = "http://localhost:3000/")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@Autowired
	private UserDao userService;
	// add address

	@GetMapping("")
	public List<Address> getAll(){
		return addressService.Alladdresses();
	}
	
	@PostMapping("/{token}")
	public ResponseEntity<?> addAddress(@RequestBody Address address, @PathVariable String token)
			throws InValidTokenException {

		User user = userService.findbyToken(token);
//		address.setUser(user);
		int uid=user.getId();
		
		addressService.addAddress(address,user);

		return new ResponseEntity<String>("Address is added Successfully", HttpStatus.OK);

	}
	@PutMapping("/editAddress/{addressId}/{token}")
	public ResponseEntity<?> updateAddress(@RequestBody Address address, @PathVariable String token ,@PathVariable int addressId)
			throws InValidTokenException {

		User user = userService.findbyToken(token);
//		address.setUser(user);
		int uid=user.getId();
		
		addressService.updateAddress(address,user,addressId);

		return new ResponseEntity<String>("Address is edited Successfully", HttpStatus.OK);

	}
	
	@GetMapping("/getAllAdressByUserId/{token}")
	public List<Address> getAllAdressByUserId(@PathVariable String token) throws InValidTokenException{
		
		User user = userService.findbyToken(token);
		int uid=user.getId();
		return addressService.getAllAddressByUser(user);
	}
	
	@DeleteMapping("/deleteAddressByAddressId/{addressId}/{token}")
	public ResponseEntity<String> getAdressByAddressId(  @PathVariable int addressId, @PathVariable String token) throws InValidTokenException{
		
		try {
			User user=userService.findbyToken(token);
			addressService.deleteAddressById(addressId,user);
			
			return new ResponseEntity<String>("Address is deleted Succesfully",HttpStatus.OK);
			}
			catch(Exception e) {
				return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
			
	}
	
}
