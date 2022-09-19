package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repo.CategoryRepo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepo caterepo;
	
	
	public List<Category> getAllCategories(){
		System.out.println("service get all categories");
		return caterepo.findAll();
	}
	
	public Category getCategoryByCid(int cid) {
		return caterepo.findByCid(cid);
	}

}
