package com.sanskar.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.sanskar.blog.entities.Category;
import com.sanskar.blog.entities.Post;
import com.sanskar.blog.entities.User;
import com.sanskar.blog.exceptions.ResourceNotFoundException;
import com.sanskar.blog.payloads.PostDto;
import com.sanskar.blog.payloads.PostResponse;
import com.sanskar.blog.repositories.CategoryRepo;
import com.sanskar.blog.repositories.PostRepo;
import com.sanskar.blog.repositories.UserRepo;
import com.sanskar.blog.services.PostService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor

@Service
public class PostServiceImpl implements PostService {
    
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "user Id", userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Category id", categoryId));
		
		Post post= this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost=this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	
	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post id", postId));
        
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost=this.postRepo.save(post);
	    return this.modelMapper.map(updatedPost, PostDto.class);
	}

	
	@Override
	public void deletePost(Integer postId) {
       
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post id", postId));
	    this.postRepo.delete(post);
	}

	
	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy) {
	    
		//for paging , we need pageable object , then we call findall method with that object return Page of posts
	    //to get List of posts we use getContent method
		
		//whatever parameter is sent to sortBy it be sorted wrt it , either title,post id whatever 
		
	
		Pageable p=PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)) ;
		Page<Post> pagePost=this.postRepo.findAll(p);
		List<Post> posts=pagePost.getContent();
		
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	    
		PostResponse postResponse=new PostResponse();
		
		//we will return post response , all the content is fetched in pagePost above
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	
	@Override
	public PostDto getPostById(Integer postId) {
	  
	  Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post id", postId));	
	  return this.modelMapper.map(post, PostDto.class);
	 
	}

	
	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
	    
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "category id", categoryId));
		
		List<Post> posts=this.postRepo.findByCategory(cat);
		
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	    return postDtos;
	}

	
	@Override
	public List<PostDto> getPostByUser(Integer userId) {
	   
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "user id", userId));
		
		List<Post> posts=this.postRepo.findByUser(user);
		
		List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	    return postDtos;
		
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
	   
		List<Post> posts= this.postRepo.findByTitleContaining(keyword);
	    List<PostDto> postDto=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

}
