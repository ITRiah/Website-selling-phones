package com.example.Project3.dto;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductDetailsDTO {
	private int id;
	
	//@JsonIncludeProperties("id") //chỉ lấy id
	private ProductDTO product;
	
	private String color;
	
	@Min(0)
	private int quantity;
		
	private String images;
	
	@JsonIgnore
	private MultipartFile file;
}
