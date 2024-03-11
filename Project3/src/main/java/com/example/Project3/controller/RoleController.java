package com.example.Project3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project3.dto.ResponseDTO;
import com.example.Project3.dto.RoleDTO;
import com.example.Project3.dto.SearchDTO;
import com.example.Project3.service.RoleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/role")

public class RoleController {
	@Autowired
	RoleService roleService;
	
	@PostMapping("/")
	public ResponseDTO<Void> create(@RequestBody @Valid  RoleDTO roleDTO) {
		roleService.create(roleDTO);
		return ResponseDTO.<Void>builder()
					.status(200)
					.msg("ok")
					.build();
	}
	
	@PutMapping("/")
	public ResponseDTO<Void> update(@RequestBody @Valid RoleDTO roleDTO) {
		roleService.update(roleDTO);
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("ok")
				.build();
	}
	
	@DeleteMapping("/")
	//@DeleteMapping("/{id}") thay RequestParam  = @PathVariable , khi gọi /id
	public ResponseDTO<Void> create(@RequestParam("id") int id) {
		roleService.delete(id);
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("ok")
				.build();
	}
	
	@PostMapping("/search")
	public ResponseDTO<Page<RoleDTO>> search(@RequestBody SearchDTO searchDTO){
		return ResponseDTO.<Page<RoleDTO>>builder()
				.status(200)
				.msg("ok")
				.data(roleService.search(searchDTO))
				.build();
	}
}
