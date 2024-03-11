package com.example.Project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project3.dto.CategoryDTO;
import com.example.Project3.dto.ResponseDTO;
import com.example.Project3.service.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/")
	public ResponseDTO<List<CategoryDTO>> getAll() {
		return ResponseDTO.<List<CategoryDTO>>builder()
					.status(200)
					.data(categoryService.getAll())
					.msg("ok")
					.build();
	}
	
	@GetMapping("/get-by-id")
	public ResponseDTO<CategoryDTO> getById(@RequestParam("id") int id) {
		
		return ResponseDTO.<CategoryDTO>builder()
				.status(200)
				.data(categoryService.getById(id))
				.msg("ok")
				.build();
	}
	
	@PostMapping("/")
	public ResponseDTO<Void> create(@RequestBody @Valid CategoryDTO CategoryDTO) {
		categoryService.create(CategoryDTO);
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("done")
				.build();
	}
	
	
	@PutMapping("/")
	public ResponseDTO<Void> update(@RequestBody CategoryDTO categoryDTO) {
		categoryService.update(categoryDTO);
		
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("done")
				.build();
	}
		
	@DeleteMapping("/")
	public ResponseDTO<Void> delete(@RequestParam("id") int id) {
		categoryService.delete(id);
		
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("done")
				.build();
	}	
}
