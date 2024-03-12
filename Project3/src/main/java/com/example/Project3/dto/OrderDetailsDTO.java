package com.example.Project3.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class OrderDetailsDTO {
	
	private Integer id;
	
	//@JsonBackReference //tránh vòng lặp vì khi lấy order sẽ lấy orderdetails , còn lấy orderdetails lấy order
					   // khi đọc thì lấy order sẽ lấy orderdetails , còn lấy orderdetails không cần lấy order nên sẽ để @jsonBack 
					   // cần 1 jsonBack và 1 @JsonManagedReference
	//@JsonIncludeProperties("id") // bỏ đi thuộc tính tạo vòng lặp ở class chính
	private OrderDTO order;
	
	private String color;

	private ProductDTO product;
	
	@Min(0)
	private int quantity; // số lượng mua.
	@Min(0)
	private double price; // gía lúc đặt có thể giá giảm
}
