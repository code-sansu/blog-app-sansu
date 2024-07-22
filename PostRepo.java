package com.sanskar.blog.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sanskar.blog.entities.Category;
import com.sanskar.blog.entities.Post;
import com.sanskar.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
  
	List<Post> findByUser(User user);              //custom finder methods
    List<Post> findByCategory(Category category);
    
    List<Post> findByTitleContaining(String title);
    
}
