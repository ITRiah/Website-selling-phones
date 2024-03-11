package com.example.Project3.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project3.dto.ProductDTO;
import com.example.Project3.entity.Product;
import com.example.Project3.repo.ProductDetailsRepo;
import com.example.Project3.repo.ProductRepo;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

public interface ProductService {
	void create (ProductDTO productDTO);
	void update (ProductDTO productDTO);
	void updateImage (int id, String path);
	void delete (int id);
	ProductDTO getById(int id);
	List<ProductDTO> getAll();
}

@Service
 class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepo productRepo;

	@Autowired
	ProductDetailsRepo productDetailsRepo;
	
	@Override
	@Transactional
	public void create(ProductDTO productDTO) {		
		Product product = new ModelMapper().map(productDTO, Product.class);
		productRepo.save(product);
		
//		List<ProductDetails> lst = product.getProductDetails();
//		
//		for (ProductDetails productDetails : lst) {
//			productDetailsRepo.save(productDetails);
//		}
	}

	@Override
	@Transactional
	public void update(ProductDTO productDTO) {
		Product product = productRepo.findById(productDTO.getId()).orElseThrow(NoResultException :: new);
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setImage(productDTO.getImage());
		product.setPrice(productDTO.getPrice());
		
		productRepo.save(product);
	}

	@Override
	public void delete(int id) {
		productRepo.deleteById(id);
	}

	@Override
	public List<ProductDTO> getAll() {
		List<Product> products = productRepo.findAll();
		
		List<ProductDTO> productDTOs = products.stream().map(product -> new ModelMapper().map(product, ProductDTO.class)) 
											.collect(Collectors.toList());
		return productDTOs;
	}

	@Override
	public ProductDTO getById(int id) {
		Product product = productRepo.findById(id).orElseThrow(NoResultException :: new);
		
		ProductDTO productDTO = new ModelMapper().map(product, ProductDTO.class);
		
		return productDTO;
	}

	@Override
	public void updateImage(int id , String path) {
		Product product = productRepo.findById(id).orElseThrow(NoResultException :: new);
		product.setImage(path);
		productRepo.save(product);
	}
}
