package com.example.Project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project3.dto.CartDetailDTO;
import com.example.Project3.dto.ResponseDTO;
import com.example.Project3.service.CartDetailsService;

@RestController
@RequestMapping("/cart-detail")
public class CartDetailsController {
	@Autowired
	CartDetailsService CartDetailService;

	@GetMapping("/")
	public ResponseDTO<List<CartDetailDTO>> getByProductId(@RequestParam("id") int id) {

		return ResponseDTO.<List<CartDetailDTO>>builder().status(200).data(CartDetailService.getCartById(id))
				.msg("ok").build();
	}

	@PostMapping("/")
	public ResponseDTO<Void> create(@RequestBody CartDetailDTO pd) {
		CartDetailService.create(pd);
		return ResponseDTO.<Void>builder().status(200).msg("done").build();
	}
}
