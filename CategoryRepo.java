package com.sanskar.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sanskar.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
