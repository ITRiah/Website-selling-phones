package com.example.Project3.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project3.dto.ProductDetailsDTO;
import com.example.Project3.entity.ProductDetails;
import com.example.Project3.repo.ProductDetailsRepo;

public interface ProductDetailsService {
	void create(ProductDetailsDTO ProductColorDTO);
	List<ProductDetailsDTO> getByProductId(int id);
}

@Service
class ProductDetailsServiceImpl implements ProductDetailsService {

	@Autowired
	ProductDetailsRepo ProductColorRepo;

	@Override
	public void create(ProductDetailsDTO productDetailsDTO) {
		
		ProductDetails productColor = new ModelMapper().map(productDetailsDTO, ProductDetails.class);
		
		ProductColorRepo.save(productColor);
	}

	@Override
	public List<ProductDetailsDTO> getByProductId(int id) {
		List<ProductDetails> productColors = ProductColorRepo.getByProductId(id);

		List<ProductDetailsDTO> ProductColorDTOs = productColors.stream()
				.map(product -> new ModelMapper().map(product, ProductDetailsDTO.class)).collect(Collectors.toList());
		
		return ProductColorDTOs;
	}
}
