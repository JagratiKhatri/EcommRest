package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.JwtRequest;
import com.example.demo.JwtResponse;
import com.example.demo.JwtUtil;
import com.example.demo.Exception.InValidTokenException;
import com.example.demo.model.Image;
import com.example.demo.model.User;
import com.example.demo.service.UserDao;




@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	 @Autowired
	    private AuthenticationManager authenticationManager;
	    @Autowired
	    private JwtUtil jwtTokenUtil;
	    @Autowired
	    private UserDetailsService userDetailsService;
	    
	    @Autowired
	    private UserDao udao;
	    
	    @Autowired
	    private BCryptPasswordEncoder passwordEncoder;
	   
	    
	    
	    @RequestMapping(value = "/getToken", method = RequestMethod.POST)
	    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest , HttpSession session) throws Exception {
	        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
	        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	        final String jwttoken = jwtTokenUtil.generateToken(userDetails);
	        JwtResponse j=new JwtResponse();
	        j.setJwttoken(jwttoken);
	        session.setAttribute("jwttoken", jwttoken);
	        System.out.println("Received request to generate token for "+authenticationRequest);
	        return j;

	    }





	private void authenticate(String username, String password) throws Exception {
	        try {
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	        } catch (DisabledException e) {
	            throw new Exception("USER_DISABLED", e);
	        } catch (BadCredentialsException e) {
	            throw new Exception("INVALID_CREDENTIALS", e);
	        }
	    }

	    
	
		@GetMapping("/getuname")
	    public ResponseEntity<String> getUname(@RequestHeader("Authorization") String authorization) {
			
	        String token =authorization.substring(7);
	        return new ResponseEntity( jwtTokenUtil.getUsernameFromToken(token),HttpStatus.OK);
	    }
		
		@GetMapping("/getemail")
	    public ResponseEntity<String> getEmail(@RequestHeader("Authorization") String authorization) {
			 String token =authorization.substring(7);
		        System.out.println(token);
		        String uname= jwtTokenUtil.getUsernameFromToken(token);
		        if(uname!=null) {
		        	   User u = udao.findByUsername(uname);
		               System.out.println(u);
		               return new ResponseEntity( u.getEmail(),HttpStatus.OK);
		        }
		        else {
		        		return new ResponseEntity(0,HttpStatus.BAD_REQUEST);
		        }
	    }
		@GetMapping("/getuid")
	    public ResponseEntity<Integer> getUid(@RequestHeader("Authorization") String authorization) {
		
	        String token =authorization.substring(7);
	        System.out.println(token);
	        String uname= jwtTokenUtil.getUsernameFromToken(token);
	        if(uname!=null) {
	        	   User u = udao.findByUsername(uname);
	               System.out.println(u);
	               return new ResponseEntity( u.getId(),HttpStatus.OK);
	        }
	        else {
	        		return new ResponseEntity(0,HttpStatus.BAD_REQUEST);
	        }
	    }
	 
		@GetMapping("/validate")
		public Boolean validateToken(@RequestHeader("Authorization") String authorization) {
//			log.info("Validate the token");
			String token1 =authorization.substring(7);
	       // System.out.println(token);
	        //String uname= jwtTokenUtil.getUsernameFromToken(token);
	       // final String username =jwtTokenUtil.getUsernameFromToken(authorization);
	        return  !jwtTokenUtil.isTokenExpired(token1);
	    }
	    	@PostMapping("/users")
	    	public User saveUser( @RequestBody User user) {
	    	
	    		user.setPassword(passwordEncoder.encode(user.getPassword()));
	    		user.setIsAccountLocked(false);
	    		user.setIsEnabled(true);
	    		user.setIsCredentialsNonExpired(true);
	    		user.setRole("user");
	    		return udao.addUser(user);
	    	}
		@GetMapping("/users")
		public List<User> getAllUsers(){
			return udao.getall();
		}
		
		
		@GetMapping("/getUserByToken/{token}")
		  public ResponseEntity<User> getUser( @PathVariable String token , HttpSession session) throws InValidTokenException {
//			String token =(String) session.getAttribute("jwttoken");
	    
	        	   User u = udao.findbyToken(token);
	               System.out.println(u);
	               return new ResponseEntity( u,HttpStatus.OK);
	        
	        
	    }
		
		@PutMapping("/updateFnameLname")
		public ResponseEntity<String> editfnameLname(@RequestParam String fname , @RequestParam String lname,HttpSession session){
			String token =(String) session.getAttribute("jwttoken");
			String uname= jwtTokenUtil.getUsernameFromToken(token);
		        if(uname!=null) {
		        	   User u = udao.findByUsername(uname);
		        	   u.setFname(fname);
		        	   u.setLname(lname);
		               System.out.println(u);
		               udao.updateFnameLname(u);
		               return new ResponseEntity( "User Details Updated" ,HttpStatus.OK);
		        }
		        else {
		        		return new ResponseEntity(0,HttpStatus.BAD_REQUEST);
		        }	
			
		}
		@PutMapping("/updateEmail")
		public ResponseEntity<String> editEmail(@RequestParam String email,HttpSession session){
			
			String token =(String) session.getAttribute("jwttoken");
			 String uname= jwtTokenUtil.getUsernameFromToken(token);
		        if(uname!=null) {
		        	   User u = udao.findByUsername(uname);
		        	   u.setEmail(email);
		               System.out.println(u);
		               udao.updateEmail(u);
		               return new ResponseEntity( "User Details Updated",HttpStatus.OK);
		        }
		        else {
		        		return new ResponseEntity(0,HttpStatus.BAD_REQUEST);
		        }	
			
		}
		@PutMapping("/updateMobile")
		public ResponseEntity<String> editMobileNumber(@RequestParam String mobileNumber,HttpSession session){
			String token =(String) session.getAttribute("jwttoken");
			String uname= jwtTokenUtil.getUsernameFromToken(token);
		        if(uname!=null) {
		        	   User u = udao.findByUsername(uname);
		        	 u.setPhoneNumber(mobileNumber);
		               System.out.println(u);
		               udao.updateEmail(u);
		               return new ResponseEntity( "User Details Updated",HttpStatus.OK);
		        }
		        else {
		        		return new ResponseEntity(0,HttpStatus.BAD_REQUEST);
		        }	
			
		}
		
		@PutMapping("/updateProfile/{token}")
		public User editProfile(@PathVariable String token , @RequestBody  User user, HttpSession session) throws Exception{
	System.out.println("Profile updated");
			String uname= jwtTokenUtil.getUsernameFromToken(token);
		
			System.out.println(uname);
		        if(uname!=null) {
//
//		    		String dir="C:\\Users\\jagra\\OneDrive\\Documents\\UserRest (6)\\ImageUpload\\src\\main\\resources\\static\\Images";
//		    		Files.copy(file.getInputStream(), Paths.get(dir+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
//		    		u.setImage(dir+"\\"+file.getOriginalFilename());
		        	User u = udao.findByUsername(uname);
		        	System.out.println(user);
		        	System.out.println(u+" hjgjgj ");
		        
		        	u.setEmail(user.getEmail());
		        	u.setFname(user.getFname());
		        	
		        	u.setLname(user.getLname());
		        	
		        	
		        	u.setPhoneNumber(user.getPhoneNumber());
		        	u.setImage(u.getImage());
		        	
		               return udao.updateUser(u);
		        }
				return null;
		        
			
		}
		@PutMapping(value={"/updateProfileWithImage/{token}"} ,consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
		public ResponseEntity<String> editProfileWithImage(@PathVariable String token ,@RequestPart String fname,
				
				@RequestPart String lname,@RequestPart String email,@RequestPart String phoneNumber,
				@RequestPart("file") MultipartFile file) throws Exception{
			try{
				
//				
//				 fname:"",
//				    lname:"",
//				    email:"",
//				    phoneNumber:""
				
			String uname= jwtTokenUtil.getUsernameFromToken(token);
		        if(uname!=null) {

		        	String img=new ClassPathResource("static/Images/").getFile().getAbsolutePath();
		    		String dir="C:\\Users\\jagra\\OneDrive\\Documents\\UserRest (6)\\ImageUpload\\src\\main\\resources\\static\\Images";
		    		Files.copy(file.getInputStream(), Paths.get(img+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
		    	String s=	ServletUriComponentsBuilder.fromCurrentContextPath().path("/Images/").path(file.getOriginalFilename()).toUriString();
//		    		u.setImage(dir+"\\"+file.getOriginalFilename());
		        	User u = udao.findByUsername(uname);
		        	u.setEmail(email);
		        	u.setFname(fname);
		        	//u.setPassword(passwordEncoder.encode(user.getPassword()));
		        	u.setLname(lname);
		        	//u.setUsername(user.getUsername());
		        	u.setPhoneNumber(phoneNumber);
		        	u.setImage(s);
		        	udao.updateUser(u);
		               return new ResponseEntity( "User Details Updated",HttpStatus.OK);
		        }
		        else {
		        		return new ResponseEntity(0,HttpStatus.BAD_REQUEST);
		        }
			}
			catch(Exception e) {
				e.printStackTrace();
				return null;
			}
			
			
		}
		public String ImageUpload( MultipartFile file) throws Exception  {
			
			System.out.println(file.getName());
			System.out.println(file.getContentType());
			System.out.println(file.getSize());
			System.out.println(file.getOriginalFilename());
			String dir="C:\\Users\\jagra\\OneDrive\\Documents\\UserRest (6)\\ImageUpload\\src\\main\\resources\\static\\Images";
			System.out.println("Before saving the file");
			Files.copy(file.getInputStream(), Paths.get(dir+File.separator+file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			System.out.println("After saving file to location");
			String image=dir+"\\"+file.getOriginalFilename();
			return image;
//			i.setImagePath(dir+"\\"+file.getOriginalFilename());
//			imageService.saveImage(i);
//			
		}
	
}
