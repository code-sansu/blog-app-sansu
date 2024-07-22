package com.sanskar.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanskar.blog.entities.User;

@Repository
// inherit JpaRepository it provides functionalities for database operations , specify with which entity you want to work (here User) and type of ID used as primary key (here integer)  
public interface UserRepo extends JpaRepository<User, Integer>{ 
	

}
