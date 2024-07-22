package com.sanskar.blog.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity                //when you annotate a class as entity , it creates table in corresponding database with name same as class_name
@Table(name="users")   //if you want to change table name in the database
@NoArgsConstructor     //creates a constructor with no arguments , instance can be used directly
@Getter                //get and set the values of fields directly ,without having to create methods for them
@Setter


public class User {
    //whatever fields is there in the entity class is becomes the column in the table
	
	@Id                                               //primary key in the table
    @GeneratedValue(strategy = GenerationType.IDENTITY)   //auto increment this id column , generate auto incremented values 
	private int id;
    
    @Column(name="user_name",nullable=false,length=100) // changes column name in the database table to user_nacme , cannot accept null values 
	private String name;
	
	private String password;
	
	private String about;
	
	private String email;
	
	// cascadetypeALL means all the changes in parent reflected for children 
	// mapped with user column
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    private List<Post> posts=new ArrayList<>();
}

