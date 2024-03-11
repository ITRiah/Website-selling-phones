package com.example.Project3.dto;

import java.util.Date;
import java.util.List;

import com.example.Project3.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class CartDTO{
	private int id;
	
	private User user;
	
	@Min(0)
	private int quantity;
	
	private double totalPrice;
	
	private String status;
		
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;
	

	@JsonIgnoreProperties("cart") 
	private List<CartDetailDTO> cartDetailDTOs; 
}
