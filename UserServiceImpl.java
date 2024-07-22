package com.sanskar.blog.services.impl;

import java.util.List;



import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanskar.blog.entities.User;
import com.sanskar.blog.payloads.UserDto;
import com.sanskar.blog.repositories.UserRepo;
import com.sanskar.blog.services.UserService;
import com.sanskar.blog.exceptions.*;



// controller -> methods(service) -> repository ->

@Service
public class UserServiceImpl implements UserService {
    
	
	@Autowired                 
	private UserRepo userRepo;
	
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		UserDto createdUserDto= this.userToDto(savedUser);
		return createdUserDto;
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		//lambda expression to find user by id or else throw an exception if not found
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		
		User updatedUser=this.userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users=this.userRepo.findAll();
		// lambda stream api used to convert list of users to list of userDtos
		List<UserDto> userDtos= users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
     
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
        
		this.userRepo.delete(user);
	}
    
	
	// conversion of UserDto to User and vice versa , 
	// because our methods makes use of userDto but , userRepo.save() method takes entity as argument (User in our case)
	
	
	/*private User dtoToUser(UserDto userDto) {
		
		User user=new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}*/
	
	
	//we do do not need to convert objects manually instead we will make use of model mappers
	@Autowired
	private ModelMapper modelMapper;
	
	public User dtoToUser(UserDto userDto){
		User user=this.modelMapper.map(userDto, User.class);
		return user; 
	}
	
	public UserDto userToDto(User user){
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
	    return userDto;
	}
}
