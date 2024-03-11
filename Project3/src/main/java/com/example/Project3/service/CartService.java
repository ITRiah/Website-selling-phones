package com.example.Project3.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project3.dto.CartDTO;
import com.example.Project3.entity.Cart;
import com.example.Project3.repo.CartRepo;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

public interface CartService {
	void create (CartDTO cartDTO);
	void update (CartDTO cartDTO);
	void delete (int id);
	CartDTO getById(int id);
	List<CartDTO> getAll();
}

@Service
 class CartServiceImpl implements CartService{
	
	@Autowired
	CartRepo cartRepo;

	@Override
	@Transactional
	public void create(CartDTO cartDTO) {
		Cart cart = new ModelMapper().map(cartDTO, Cart.class);		
		
		cartRepo.save(cart);
	}

	@Override
	public void update(CartDTO CartDTO) {
		Cart order = cartRepo.findById(CartDTO.getId()).orElseThrow(NoResultException :: new);
		order.setStatus(CartDTO.getStatus());
		cartRepo.save(order);
	}

	@Override
	public void delete(int id) {
		cartRepo.deleteById(id);
	}

	@Override
	public List<CartDTO> getAll() {
		List<Cart> Cart = cartRepo.findAll();
		
		List<CartDTO> CartDTOs = Cart.stream().map(order -> new ModelMapper().map(order, CartDTO.class)) 
											.collect(Collectors.toList());
		return CartDTOs;
	}

	@Override
	public CartDTO getById(int id) {
		Cart order = cartRepo.findById(id).orElseThrow(NoResultException :: new);
		CartDTO CartDTO = new ModelMapper().map(order, CartDTO.class);
		return CartDTO;
	}

	
}
