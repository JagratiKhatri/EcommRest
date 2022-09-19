package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.JwtUtil;
import com.example.demo.Exception.InValidTokenException;
import com.example.demo.model.User;
import com.example.demo.repo.UserRepo;

@Service
public class UserDao {
   
	@Autowired
	UserRepo urepo;
	
	 @Autowired
	 private JwtUtil jwtTokenUtil;
	 
    public User findByUsername(String username) {
       return urepo.findByUsername(username);
    }
    
    public User findbyToken(String token ) throws InValidTokenException {
    	String uname=jwtTokenUtil.getUsernameFromToken(token);
    	User u=urepo.findByUsername(uname);
    	if(u!=null) {
    		return u;
    	}
    	else {
    		throw new InValidTokenException("In valid Token");
    	}
    	
    }
    public User addUser(User user) {
    	
		return urepo.save(user);
	}
	
	public void deleteById(int id) {
		urepo.deleteById(id);
	}
	public List<User> getall() {
		// TODO Auto-generated method stub
		return urepo.findAll();
	}
	public User findByEmail(String email) {
	       return urepo.findByEmail(email);
	    }
	public void updateFnameLname(User u) {
	
		 urepo.save(u);
		
	}
	public void updateEmail(User u) {
		urepo.save(u);
		
	}
	public void updateMobileNumber(User u) {
		urepo.save(u);
		
	}
	public User updateUser(User u) {
	
    
		return urepo.save(u);
		
	}
	
	
}