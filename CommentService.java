package com.sanskar.blog.services;

import com.sanskar.blog.payloads.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto,Integer postId);
	void deleteComment(Integer commentId);
}

