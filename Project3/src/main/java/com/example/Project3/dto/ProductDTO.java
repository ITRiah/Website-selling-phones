package com.example.Project3.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductDTO{
	private Integer id;
	
	@NotBlank
	private String name;
	private String image; //url
	private String description;
	
	@Min(0)
	private double price;
	
	@JsonIgnore //k có không trả về khi get by id
	private MultipartFile file;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date createdAt;

	private CategoryDTO category;
	
	private List<ProductDetailsDTO> productDetailsDTOs;
}
