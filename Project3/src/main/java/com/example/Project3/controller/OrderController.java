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

import com.example.Project3.dto.OrderDTO;
import com.example.Project3.dto.ResponseDTO;
import com.example.Project3.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService OrderService;
	
	@GetMapping("/")
	public ResponseDTO<List<OrderDTO>> getAll() {
		return ResponseDTO.<List<OrderDTO>>builder()
					.status(200)
					.data(OrderService.getAll())
					.msg("ok")
					.build();
	}
	
	@GetMapping("/get-by-id")
	public ResponseDTO<OrderDTO> getById(@RequestParam("id") int id) {
		
		return ResponseDTO.<OrderDTO>builder()
				.status(200)
				.data(OrderService.getById(id))
				.msg("ok")
				.build();
	}
	
	@PostMapping("/")
	public ResponseDTO<Void> create(@RequestBody OrderDTO orderDTO) {
		OrderService.create(orderDTO);
		
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("done")
				.build();
	}
	
	
	@PutMapping("/")
	public ResponseDTO<Void> update(@RequestBody OrderDTO categoryDTO) {
		OrderService.update(categoryDTO);
		
		return ResponseDTO.<Void>builder()
				.status(200)
				.msg("done")
				.build();
	}
		
	@DeleteMapping("/")
	public ResponseDTO<Void> delete(@RequestParam("id") int id) {
		if(!OrderService.getById(id).getStatus().equals("Chờ duyệt")) {
			
			return ResponseDTO.<Void>builder()
					.status(200)
					.msg("Trạng thái đơn hàng không hợp lệ")
					.build();
		}
			OrderService.delete(id);
			return ResponseDTO.<Void>builder()
					.status(200)
					.msg("done")
					.build();
	}	
}
