package com.example.Project3.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class OrderDTO {
	private Integer id;

	private String status;// new, pending , active

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
	private Date createdAt;

	private UserDTO user;

	@JsonIgnoreProperties("order") // giu ben nay hay ben kia bên này ạ
	private List<OrderDetailsDTO> detailsDTOs; // van de o list nay roi
}
