package com.sanskar.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanskar.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
