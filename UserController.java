package com.sanskar.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanskar.blog.payloads.ApiResponse;
import com.sanskar.blog.payloads.UserDto;
import com.sanskar.blog.services.UserService;

import jakarta.validation.Valid;

@RestController                // tells that this class is a controller (REST->returns a response 
                               //body)
                               //So whatever i am doing in this class will be my rest end points

@RequestMapping("/api/users") // executes coming requests , if i hit localhost:9090/api/users
                              //it maps the request to this class , we can also specify request method 

public class UserController {
   
	@Autowired
	private UserService userService;  //we dont need to create object instead we ask from springboot 
	                                  //to give it from the bean factory(or application context) 
	                                  // and autowire it (attach it) with the reference(userService) we have created here
	                                  // this explains springs IOC(inversion of control) and dependency injection
	
	
	
	
	//POST - create user	
	@PostMapping("/")           //request mapping, method=POST, when i hit localhost:9090/api/users/ with post method
	
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){ // @RequestBody converts the JSON object coming over here to my entity(or UserDto in our case)
		                                                                     
		
		UserDto createdUserDto=this.userService.createUser(userDto);
		
	    return new ResponseEntity<>(createdUserDto,HttpStatus.CREATED);
	}
	
	
	
	
	
  	//PUT  - update user
	@PutMapping("/{userId}")    // {userId} specifies which userId to be updated
	                            //called path URI variable
	
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer uId){   // to fetch path URI variable we have taken @PathVariable annotation
		
		UserDto updatedUser=this.userService.updateUser(userDto, uId);
		return ResponseEntity.ok(updatedUser);
	}
	
	
	
	
 	//DELETE - delete user
	
	// we could have used map and done like this
	/*
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Integer userId){ // if dont know the type then "?"
		
		this.deleteUser(userId);
		
		return new ResponseEntity(Map.of("messsage","User deleted successfully"),HttpStatus.OK);
	}
	*/
	
	// we are creating a class apiresponse to return apiresponse 
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
	}
	
	
	
	
	
	//GET - get user
	
	//for all users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
	
	//user by id
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
		
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
}
