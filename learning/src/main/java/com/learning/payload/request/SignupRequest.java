package com.learning.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

import lombok.Data;
@Data
public class SignupRequest {
	  
	  
	  
	  private String username;
	  
	  

	  @Email
	  private String email;
	  
	  
	  
	  private String name;
	  
	  @Size(min = 3, max = 20)
	  private String address;

	  private Set<String> role;

	  
	  
	  private String password;
}
