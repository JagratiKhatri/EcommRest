package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.CategoryService;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/getAllCategories")
	public List<Category> getAllCategories() {
	

		return categoryService.getAllCategories();

	}

	@GetMapping("/getByCid")
	private Category getCategoryById(@RequestParam int cid) {
		System.out.println(cid);
		return categoryService.getCategoryByCid(cid);

	}

}
