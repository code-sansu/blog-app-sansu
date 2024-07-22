package com.sanskar.blog.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

// we don't want to expose out entities directly to services , we will user payloads instead 
public class UserDto {
	
	// these are bean validation annotations , validated with JSR 380 ,
	//Hibernate validator provides implementation of validation api
	// include dependency , add annotations here , and add @valid annotation before @ResponseBody in controller
	
	@NotNull
	private int id;
	
	@NotEmpty
	@Size(min = 4,message="length should be minimum 4")  // default message we are sending to exception handler
	private String name;
	
	@Email(message="Invalid email")
	private String email;
	
	@NotEmpty
	@Size(min=3, max=10)
	@Pattern(
	        regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*]).+$",
	        message = "Password must contain at least one digit and one special character , min: 3, max: 4"
	    )
	private String password;
	
	@NotEmpty(message = "cannot be empty")
	private String about;

}
