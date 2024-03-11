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

import com.example.Project3.dto.ColorDTO;
import com.example.Project3.dto.ResponseDTO;
import com.example.Project3.service.ColorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/color")
public class ColorController {
	@Autowired
	ColorService colorService;
	
	@GetMapping("/")
	public ResponseDTO<List<ColorDTO>> getAll() {
		return ResponseDTO.<List<ColorDTO>>builder()
					.status(200)
					.data(colorService.getAll())
					.msg("ok")
					.build();
	}
	
	@GetMapping("/get-by-id")
	public ResponseDTO<ColorDTO> getById(@RequestParam("id") int id) {
		
		return ResponseDTO.<ColorDTO>builder()
				.status(200)
				.data(colorService.getById(id))
				.msg("ok")
				.build();
	}
	
	@PostMapping("/")
	public ResponseDTO<Void> create(@RequestBody @Valid ColorDTO ColorDTO) {
		colorService.create(ColorDTO);
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("done")
				.build();
	}
	
	
	@PutMapping("/")
	public ResponseDTO<Void> update(@RequestBody ColorDTO ColorDTO) {
		colorService.update(ColorDTO);
		
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("done")
				.build();
	}
		
	@DeleteMapping("/")
	public ResponseDTO<Void> delete(@RequestParam("id") int id) {
		colorService.delete(id);
		
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("done")
				.build();
	}	
}
