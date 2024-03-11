package com.example.Project3.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project3.dto.OrderDTO;
import com.example.Project3.entity.OrderDetails;
import com.example.Project3.entity.Orders;
import com.example.Project3.entity.Product;
import com.example.Project3.entity.ProductDetails;
import com.example.Project3.repo.OrderRepo;
import com.example.Project3.repo.ProductDetailsRepo;
import com.example.Project3.repo.ProductRepo;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

public interface OrderService {
	void create (OrderDTO orderDTO);
	void update (OrderDTO orderDTO);
	void delete (int id);
	OrderDTO getById(int id);
	List<OrderDTO> getAll();
}

@Service
 class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepo OrderRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	ProductDetailsRepo productDetailsRepo;
	
	
	@Override
	@Transactional
	public void create(OrderDTO orderDTO) {
		Orders orders = new ModelMapper().map(orderDTO, Orders.class);		
		OrderRepo.save(orders);
	}

	@Override
	public void update(OrderDTO orderDTO) {
		Orders order = OrderRepo.findById(orderDTO.getId()).orElseThrow(NoResultException :: new);
		order.setStatus(orderDTO.getStatus());
		OrderRepo.save(order);
	}

	@Override
	public void delete(int id) {
		Orders order = OrderRepo.findById(id).orElseThrow(NoResultException :: new);
		
		List<OrderDetails> orderDetails = order.getOrderDetails();
		
		for (OrderDetails orderDetail : orderDetails) {
			int productId = orderDetail.getProduct().getId();
			Product product = productRepo.findById(productId).orElseThrow(NoResultException :: new);
			List<ProductDetails> productDetails = product.getProductDetails();
			
			for (ProductDetails productDetail : productDetails) {
				if(orderDetail.getColor().equals(productDetail.getColor())) {
					productDetail.setQuantity(productDetail.getQuantity() + orderDetail.getQuantity());
					productDetailsRepo.save(productDetail);
					break;
				}
			}
		}
		OrderRepo.deleteById(id);
	}

	@Override
	public List<OrderDTO> getAll() {
		List<Orders> orders = OrderRepo.findAll();
		
		List<OrderDTO> orderDTOs = orders.stream().map(order -> new ModelMapper().map(order, OrderDTO.class)) 
											.collect(Collectors.toList());
		return orderDTOs;
	}

	@Override
	public OrderDTO getById(int id) {
		Orders order = OrderRepo.findById(id).orElseThrow(NoResultException :: new);
		OrderDTO orderDTO = new ModelMapper().map(order, OrderDTO.class);
		return orderDTO;
	}

	
}
