package com.example.Project3.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project3.dto.OrderDetailsDTO;
import com.example.Project3.entity.OrderDetails;
import com.example.Project3.repo.OrderDetailsRepo;

public interface OrderDetailsService {
	void create(OrderDetailsDTO orderDetailsDTO);
	List<OrderDetailsDTO> getByOrderId(int id);
}

@Service
class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	OrderDetailsRepo orderDetailsRepo;

	@Override
	public void create(OrderDetailsDTO orderDetailsDTO) {
		
		OrderDetails orderDetails = new ModelMapper().map(orderDetailsDTO, OrderDetails.class);
		
		orderDetailsRepo.save(orderDetails);
	}

	@Override
	public List<OrderDetailsDTO> getByOrderId(int id) {
		List<OrderDetails> orderDetails = orderDetailsRepo.getByOrderId(id);

		List<OrderDetailsDTO> orderDetailsDTOs = orderDetails.stream()
				.map(order -> new ModelMapper().map(order, OrderDetailsDTO.class)).collect(Collectors.toList());
		
		return orderDetailsDTOs;
	}
}
