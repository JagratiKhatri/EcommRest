package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Exception.InvalidProductCategoryName;
import com.example.demo.Exception.InvalidProductId;
import com.example.demo.Exception.InvalidProductName;
import com.example.demo.model.Product;
import com.example.demo.repo.ProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepo productrepo;
	
	@Autowired 
	private CategoryService categoryService;

	public List<Product> getAllProducts(){
		return productrepo.findAll();
		
	}
	
	public Product searchProductById(int pid) throws InvalidProductId  {
		Product p=productrepo.findByPid(pid);
		if(p==null) {
			throw new InvalidProductId("Product doestn't exist with this id");
		}
		
		return p;
	}
	
	public Product searchProductByName(String name) throws InvalidProductName {
		Product p= productrepo.findByName(name);
		if(p==null) {
			throw new InvalidProductName("Product doestn't exist with this Name");
		}
		return p;
	}
//	public List<Product> searchProductByCategory(String category) throws InvalidProductCategoryName{
//	List<Product>	p=productrepo.findByCategory(category);
//		if(p==null) {
//			throw new InvalidProductCategoryName("Products doestn't exist with this Category for now");
//		}
//		return p;
//	}
	
	public List<Product> getProductsbyCategoryId(int cid) {
		return categoryService.getCategoryByCid(cid).getProducts();
		
//		return productrepo.findByCategory(categoryService.getCategoryByCid(cid));
		
	}
	
	public List<Product> getProductBySearching(String query){
		return productrepo.searchProducts(query);
	}

	
}
