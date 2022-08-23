package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productrepo;

	public List<Product> getAllProducts(){
		return productrepo.findAll();
		
	}
	
	public Product searchProductById(int pid) {
		
		return productrepo.findByPid(pid);
	}
	
	public Product searchProductByName(String name) {
		return productrepo.findByName(name);
	}
	public List<Product> searchProductByCategory(String category){
		return productrepo.findByCategory(category);
	}
}
