package com.sanskar.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogAppApisApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

    // to change one object to other
    //created a bean which calls modelMapper method , which keeps the ModelMapper object ready whenever req
    @Bean
        ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
