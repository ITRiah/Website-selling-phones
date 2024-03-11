package com.example.Project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project3.dto.CartDTO;
import com.example.Project3.dto.ResponseDTO;
import com.example.Project3.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	CartService CartService;
	
	@GetMapping("/")
	public ResponseDTO<List<CartDTO>> getAll() {
		return ResponseDTO.<List<CartDTO>>builder()
					.status(200)
					.data(CartService.getAll())
					.msg("ok")
					.build();
	}
	
	@GetMapping("/get-by-id")
	public ResponseDTO<CartDTO> getById(@RequestParam("id") int id) {
		
		return ResponseDTO.<CartDTO>builder()
				.status(200)
				.data(CartService.getById(id))
				.msg("ok")
				.build();
	}
	
	@PostMapping("/")
	public ResponseDTO<Void> create(@RequestBody CartDTO CartDTO) {
		CartService.create(CartDTO);
		
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("done")
				.build();
	}
	
	
	@PutMapping("/")
	public ResponseDTO<Void> update(@RequestBody CartDTO categoryDTO) {
		CartService.update(categoryDTO);
		
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("done")
				.build();
	}
		
	@DeleteMapping("/")
	public ResponseDTO<Void> delete(@RequestParam("id") int id) {
			CartService.delete(id);
			return ResponseDTO.<Void>builder()
					.status(200)
					.msg("done")
					.build();
	}	
}
