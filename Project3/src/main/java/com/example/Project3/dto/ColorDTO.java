package com.example.Project3.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ColorDTO {
	private Integer id;
	
	@NotBlank
	@Size(min = 2, max = 20)
	private String name;

}
