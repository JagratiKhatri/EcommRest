package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

@RestController
@CrossOrigin(origins="")
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public List<Product> AllProducts(){
		return productService.getAllProducts();
	}
	
	@PostMapping("/getByProductId")
	public Product searchProductById( @RequestParam int pid) {
		return productService.searchProductById(pid);
		
	}
	@PostMapping("/getByName")
	public Product searchProductByName(@RequestParam String Name) {
		return productService.searchProductByName(Name);
	}
	@PostMapping("/getBycategory")
	public List<Product> searchProductByCategory(@RequestParam String category){
		return productService.searchProductByCategory(category);
	}
	

}
