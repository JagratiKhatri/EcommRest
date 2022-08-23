package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.UserAlreadyExitsException;
import com.example.demo.Exception.userIsNotloggedInException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000/")
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("")
	public List<User> showUsers(){
		return userService.getAll();
	}

	@PostMapping("")
	public User Register(@RequestBody User u) throws Exception{
		List<User> users=userService.getAll();
		for (User user : users) {
			if(user.getEmail().equalsIgnoreCase(u.getEmail())) {
				System.out.println("User already exit ");
				throw new UserAlreadyExitsException("User already Exits");
				
			}
		
			
		}
		User newUser = userService.getUser(u);
		return newUser;
		
		

	}

	@PostMapping("/login")
	public boolean loginUser(@RequestParam String email, @RequestParam String password, HttpSession session) {
		String lemail=email.toLowerCase();
		System.out.println(lemail);
		User u = userService.login(lemail, password);
		if (u != null) {
			session.setAttribute("uid", u.getUid());
			session.setAttribute("email", u.getEmail());
			return true;
		}

		else
			return false;

	}

	@GetMapping("/profile")
	public User Profile(HttpSession session) throws userIsNotloggedInException {

		Integer Uid = (Integer) session.getAttribute("uid");
		if (Uid != null) {
			return userService.getUserById(Uid);
		} else {
			System.out.println("User id is not present in session");
			throw new userIsNotloggedInException("User is not logged In");
		}
	}
	
	@GetMapping("/logout")
	public void logout(HttpSession session) {
		session.invalidate();
		
	}
		
	@PutMapping("/profile")
	public User updateProfile(HttpSession session,@RequestParam String email , @RequestParam String fname, @RequestParam String lname ){
		Integer Uid = (Integer) session.getAttribute("uid");
		return userService.updateFnameLnameAndEmail(email,fname,lname,Uid);
		
	}
	
	
}
