package com.example.demo;

public class JwtResponse {
	 private  String jwttoken;
	    public JwtResponse(String jwttoken) {
	        this.jwttoken = jwttoken;
	    }
	  
		public String getJwttoken() {
			return jwttoken;
		}

		public void setJwttoken(String jwttoken) {
			this.jwttoken = jwttoken;
		}

		public JwtResponse() {
			super();
		}
	    
	
    
}