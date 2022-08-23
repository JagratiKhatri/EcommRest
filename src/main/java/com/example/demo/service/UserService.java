package com.example.demo.service;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo urepo;
	
	public User getUser(User u) {
		return urepo.save(u);
	}
	
	public User login(String email,String password) {
		
		try {
			User user=urepo.findByEmailAndPassword(email, password);
			return user;
		}
		catch(Exception e) {
			return null;
		}
		
	}

		public User getUserById(int uid) {
			return urepo.findByUid(uid);
		}

		public User updateFnameLnameAndEmail(String email, String fname, String lname,int uid) {
			User u=urepo.findByUid(uid);
			u.setEmail(email);
			u.setFname(fname);
			u.setLname(lname);
			
			return urepo.save(u);
		}
		public List<User> getAll(){
			return urepo.findAll();
			
		}
	
	
}
