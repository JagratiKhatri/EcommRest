package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;
@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{
	public Product findByPid(int id);
	
	public Product findByName(String name);
	public List<Product> findByCategory(String category);

}
