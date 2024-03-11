package com.example.Project3.dto;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CartDetailDTO {
	private int id;
	
	@JsonIncludeProperties("id") // bỏ đi thuộc tính tạo vòng lặp ở class chính
	private CartDTO cart;
	
	private ProductDTO product;
	
	private String color;
	
	@Min(0)
	private int quantity;
	
	private double price;
	
	private double totalAmount;

}
