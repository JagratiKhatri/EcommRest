package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.CustomException;
import com.example.demo.dto.AddressDto;
import com.example.demo.model.Address;
import com.example.demo.model.Cart;
import com.example.demo.model.User;
import com.example.demo.repo.AddressRepo;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepo addressRepo;
	
	public List<Address> Alladdresses(){
		return addressRepo.findAll();
	}

	public void addAddress(Address address,User user) {
	
		address.setUser(user);
	
		addressRepo.save(address);
	}

	public List<Address> getAllAddressByUser(User user) {
		
		return addressRepo.findByUser(user);
		
	}

	public void updateAddress(Address address, User user, int addressId) {
	 Address existingAddress =	addressRepo.findByAddressId(addressId);
	 existingAddress.setCity(address.getCity());
	 existingAddress.setAddress(address.getAddress());
	 existingAddress.setLandmark(address.getLandmark());
	 existingAddress.setPhoneNumber(address.getPhoneNumber());
	 existingAddress.setName(address.getName());
	 existingAddress.setState(address.getState());
	 existingAddress.setUser(user);
	 existingAddress.setLandmark(address.getLandmark());
	 existingAddress.setPincode(address.getPincode());
		
		 addressRepo.save(address);
		
	}

	public void deleteAddressById(int addressId, User user) {
		// TODO Auto-generated method stub
		Address address=addressRepo.findByAddressId(addressId);
		addressRepo.delete(address);
		
		
	}
//	Optional<Cart> optionalCart= Optional.of(cartRepository.findById(cartItemId));
//	if(optionalCart.isEmpty()) {
//		throw new CustomException("cart item id is invalid"+cartItemId);
//	}
//	Cart cart=optionalCart.get();
//	if(cart.getUser()!=user) {
//		throw new CustomException("cart item doesn't belong to user"+cartItemId);
//	}
//	cartRepository.delete(cart);
//	public void deleteAddressById(int addressId, User user) throws CustomException {
//	Optional<Address> optionalAddress=Optional.of(addressRepo.findByAddressId(addressId));
//		if(optionalAddress.isEmpty()) {
//			throw new CustomException("Address id is invalid"+addressId);
//		}
//		Address address=optionalAddress.get();
//		if(address.getUser()!=user) {
//			throw new CustomException("Address doesn't belong to user"+addressId);
//		}
//		addressRepo.delete(address);
//	}
		
	

	
	

}
