package com.sanskar.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanskar.blog.entities.Comment;
import com.sanskar.blog.entities.Post;
import com.sanskar.blog.exceptions.ResourceNotFoundException;
import com.sanskar.blog.payloads.CommentDto;
import com.sanskar.blog.repositories.CommentRepo;
import com.sanskar.blog.repositories.PostRepo;
import com.sanskar.blog.services.CommentService;

@Service
public class CommentServicempl implements CommentService {
    
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commmentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post Id", postId));
		
		Comment comment=this.modelMapper.map(commentDto, Comment.class);
		comment.setPost(post);
		Comment savedComment=this.commmentRepo.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		
		Comment com=this.commmentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "Comment id", commentId));
        this.commmentRepo.delete(com);
	}

}
