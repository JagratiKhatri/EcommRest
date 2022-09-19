package com.example.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Image;
import com.example.demo.service.ImageService;


@RestController
@CrossOrigin(origins = "http://localhost:3000/" )
public class ImageController {
	@Autowired
	private ImageService imageService;
	
	@PostMapping("/upload-Image")
	public String ImageUpload(@RequestParam("file") MultipartFile file) throws Exception  {
		
		
//		
//		System.out.println(file.getName());
//		System.out.println(file.getContentType());
//		System.out.println(file.getSize());
//		System.out.println(file.getOriginalFilename());
//		String dir="C:\\Users\\jagra\\OneDrive\\Documents\\UserRest (6)\\ImageUpload\\src\\main\\resources\\static\\Images";
//		Files.copy(file.getInputStream(), Paths.get(dir+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
//		
//		Image i=new Image();
//		i.setImagePath(dir+"\\"+file.getOriginalFilename());
//		imageService.saveImage(i);
//		return "Successfully Uploaded file";
		
		System.out.println(file.getName());
		System.out.println(file.getContentType());
		System.out.println(file.getSize());
		System.out.println(file.getOriginalFilename());
		String dir="C:\\Users\\jagra\\Documents\\workspace-sts-3.9.12.RELEASE\\EcomRest\\src\\main\\resources\\static\\Images";
		//String dir="C:\\Users\\jagra\\OneDrive\\Documents\\UserRest (6)\\ImageUpload\\src\\main\\resources\\static\\Images";
		Files.copy(file.getInputStream(), Paths.get(dir+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
		
		Image i=new Image();
		i.setImagePath(dir+"\\"+file.getOriginalFilename());
		imageService.saveImage(i);
		return "Successfully Uploaded file";
	}

}
