package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.InvalidProductCategoryName;
import com.example.demo.Exception.InvalidProductName;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@RestController
@CrossOrigin(origins="http://localhost:3000/")
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("")
	public List<Product> AllProducts(){
		
		System.out.println(productService.getAllProducts());
		return productService.getAllProducts();
		}
	
	@PostMapping("/getByProductId")
	public Product searchProductById( @RequestParam int pid) throws Exception{
		return productService.searchProductById(pid);
	}
	@GetMapping("/getByProductId")
	public Product searchProductByid( @RequestParam int pid) throws Exception{
		return productService.searchProductById(pid);
	}
	@PostMapping("/getByName")
	public Product searchProductByName(@RequestParam String Name) throws Exception {
		return productService.searchProductByName(Name);
	}
//	@GetMapping("/getBycategoryId")
//	public List<Product> searchProductByCategory(@RequestParam int categoryId) throws Exception{
//		
//		Category c=categoryService.getCategoryByCid(categoryId);
//		List<Product> p=productService.getProductBypid(categoryId);
//		return p;
//	}
//	
//	@GetMapping("/getProductById")
//	public List<Product> getProduct(@RequestParam int cid) {
//		Product p=new Product();
//		Category c=categoryService.getCategoryByCid(cid);
//		p.setCategory(c);
//		
//		
//		return productService.getProductBypid(pid);
//		
//		
//	}
	
	@GetMapping("/getByCategoryId")
	public List<Product> getProductListCategorywise(@RequestParam  int cid){
		System.out.println(productService.getProductsbyCategoryId(cid));
		
		return productService.getProductsbyCategoryId(cid);
	}
	
	@GetMapping("/search/{query}")
	public List<Product> getProductBySearching(@PathVariable("query") String query){
		
		return productService.getProductBySearching(query);
	}

}
