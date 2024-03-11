package com.example.Project3.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project3.dto.CartDetailDTO;
import com.example.Project3.entity.Cart;
import com.example.Project3.entity.CartDetail;
import com.example.Project3.repo.CartDetailRepo;
import com.example.Project3.repo.CartRepo;

import jakarta.persistence.NoResultException;

public interface CartDetailsService {
	void create(CartDetailDTO cartDetailDTO);
	List<CartDetailDTO> getCartById(int id);
}

@Service
class CartDetailsServiceImpl implements CartDetailsService {

	@Autowired
	CartDetailRepo cartDetailRepo;
	
	@Autowired
	CartRepo cartRepo;

	@Override
	public void create(CartDetailDTO CartDetailDTO) {
		
		CartDetail CartDetail = new ModelMapper().map(CartDetailDTO, CartDetail.class);
		CartDetail.setTotalAmount(CartDetail.getPrice() * CartDetail.getQuantity());
		cartDetailRepo.save(CartDetail);
		
		int cartId = CartDetail.getCart().getId();
		
		Cart cart = cartRepo.findById(cartId).orElseThrow(NoResultException :: new);
		cart.setQuantity(cart.getQuantity() + CartDetail.getQuantity());
		cart.setTotalPrice(cart.getTotalPrice() + CartDetail.getQuantity() * CartDetail.getPrice());
		cartRepo.save(cart);
	}

	@Override
	public List<CartDetailDTO> getCartById(int id) {
		List<CartDetail> CartDetail = cartDetailRepo.getByCartId(id);

		List<CartDetailDTO> CartDetailDTOs = CartDetail.stream()
				.map(order -> new ModelMapper().map(order, CartDetailDTO.class)).collect(Collectors.toList());
		
		return CartDetailDTOs;
	}
}
