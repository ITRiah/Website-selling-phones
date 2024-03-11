package com.example.Project3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project3.dto.OrderDetailsDTO;
import com.example.Project3.dto.ResponseDTO;
import com.example.Project3.service.OrderDetailsService;

@RestController
@RequestMapping("/order-details")
public class OrderDetailsController {
	@Autowired
	OrderDetailsService orderDetailsService;

	@GetMapping("/")
	public ResponseDTO<List<OrderDetailsDTO>> getByProductId(@RequestParam("id") int id) {

		return ResponseDTO.<List<OrderDetailsDTO>>builder().status(200).data(orderDetailsService.getByOrderId(id))
				.msg("ok").build();
	}

	@PostMapping("/")
	public ResponseDTO<Void> create(@RequestBody OrderDetailsDTO pd) {
		orderDetailsService.create(pd);
		return ResponseDTO.<Void>builder().status(200).msg("done").build();
	}
}
