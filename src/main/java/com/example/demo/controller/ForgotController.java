package com.example.demo.controller;

import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmailRequest;
import com.example.demo.dto.OtpRequest;
import com.example.demo.dto.OtpSendByEmail;
import com.example.demo.dto.PasswordChangeRequest;
import com.example.demo.model.User;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserDao;


@RestController
@RequestMapping("")
@CrossOrigin(origins = "http://localhost:3000/" )
public class ForgotController {
	Random r=new Random(1000);;
	
	@Autowired
	private UserDao userService;
	
	@Autowired
	private EmailService emailservice;
	
	@RequestMapping("/forgotPassword")
	public ResponseEntity<String> forgotPassword() {
		 return new ResponseEntity<>( "Your age is ",  HttpStatus.OK);	}
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	OtpSendByEmail myotp =new OtpSendByEmail();
	@PostMapping("/forgot")
	public ResponseEntity<String> sendOTP(@RequestBody EmailRequest email, HttpSession session) {
		
		
		int otp=r.nextInt(99999999);
		System.out.println(otp );
		String message="<h1>OTP =" + otp+"</h1>";
		String subject="OTP From Eccommerce";
		String to=email.getEmail();
		User u =userService.findByEmail(to);
		if(u==null) {
			return new ResponseEntity<>("Email doesn't exist",HttpStatus.BAD_REQUEST);
		}
		else if (emailservice.sendEmail(message, subject, to)==true) {
		
		System.out.println("hjhjhjkhkhkhk");
		
		
		
		
			System.out.println("hjgjgjhgjhg");
			
			myotp.setOtpSendByEmail(otp);
			myotp.setEmail(to);
			session.setAttribute("myotp", otp);
			session.setAttribute("email", email.getEmail());
			return new ResponseEntity<>( "email is send Successfully ",  HttpStatus.OK); 
		
		}
		else {
			
		session.setAttribute("message", "Check Your email id !!");
		return new ResponseEntity<>("Check your email ID ",HttpStatus.BAD_REQUEST);
		}
	
		
	} 
	
@PostMapping("/verifyOtp")
public ResponseEntity<String> verifyOtp( @RequestBody OtpRequest otpr, HttpSession session) {
	
	Integer myOtp= myotp.getOtpSendByEmail();
	System.out.println(myotp);
	System.out.println("session se otp aayega"+myOtp);
	String email=(String)session.getAttribute("email");
	if(myOtp==otpr.getOtp()) {
		
			
			return new ResponseEntity<>("password Change otp", HttpStatus.OK) ;
		
		
	}
	else {
		return new ResponseEntity<String>("Please enter correct otp", HttpStatus.BAD_REQUEST);
	}
}
	@PostMapping("/changepassword")
	public ResponseEntity<String> changePassword(@RequestBody  PasswordChangeRequest password  ,HttpSession session) {
		String email=(String)session.getAttribute("email");
		User u=userService.findByEmail(myotp.getEmail());
		
		String pass=password.getNewPassword();
		String confrmPass= password.getConfirmPassword();
		if(pass.contentEquals(confrmPass)) {
			u.setPassword(passwordEncoder.encode(pass));
			userService.addUser(u);
			return new ResponseEntity<>("Password change susscesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("password dosn't match please enter the password again", HttpStatus.BAD_REQUEST);
		}
	}
	

}
