package com.sanskar.blog.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	@Column(name="post_title" , length=25,nullable = false)
	private String title;
	
	@Column(length=1000)
	private String content;
	
	private String imageName;
	
	private  Date addedDate;
	
	// relation with category and user , columns will be created for category and user in post table
	
	@ManyToOne
	@JoinColumn(name="category_id")  // its called join column and we can rename it
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy ="post",cascade =  CascadeType.ALL )
	
	private Set<Comment> comments=new HashSet<>();
}
