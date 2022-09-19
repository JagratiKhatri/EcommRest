package com.example.demo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Address;
import com.example.demo.model.User;

public interface AddressRepo extends JpaRepository<Address, Integer> {

	List<Address> findByUser(User user);

	Address findByAddressId(int addressId);

	//Address findByAddressId(int addressId);

}
