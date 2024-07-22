package com.sanskar.blog.services;

import java.util.List;

import com.sanskar.blog.entities.Post;
import com.sanskar.blog.payloads.PostDto;
import com.sanskar.blog.payloads.PostResponse;

public interface PostService {
    
	// create post
	PostDto createPost(PostDto postDto, Integer userId,Integer categoryId);
	
	//update post
	PostDto updatePost(PostDto postDto,int postId);
	
	//delete post
	void deletePost(Integer postId);
	
	// get all posts
	PostResponse getAllPost(Integer pageNumber,Integer pageSize , String sortBy);
	
	// get post by id
	PostDto getPostById(Integer postId);
	
	// get all post by category
	List<PostDto> getPostByCategory(Integer categoryId);
	
	//get all posts by a user
	List<PostDto> getPostByUser(Integer userId);
    
	//search post
	List<PostDto> searchPosts(String keyword);
	
	
}
