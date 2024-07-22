package com.sanskar.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

// custom response entity
public class ApiResponse {
	
	private String message;
	private boolean success;

}
