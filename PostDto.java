package com.sanskar.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sanskar.blog.entities.Comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
    
	private int postId;
	
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	private UserDto user; // we have taken UserDto because if we would have taken User which contains list of post,
	                      // at the time of creating post , it will traverse those posts , in which again User will be there ,
	                      //this will end up in infinite recursion , similarly for /category
	                      //but in UserDto and CategoryDto we dont have posts
	
	private CategoryDto category;
	
	private Set<CommentDto> comments=new HashSet<>();
}
