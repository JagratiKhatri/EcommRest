package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Image;
import com.example.demo.repo.ImageRepo;

@Service
public class ImageService {

	@Autowired
	private ImageRepo imageRepo;
	
	public void saveImage(Image i) {
		
		imageRepo.save(i);
		
	}
}
