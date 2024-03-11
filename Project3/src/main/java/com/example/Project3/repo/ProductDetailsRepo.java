package com.example.Project3.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Project3.entity.ProductDetails;

public interface ProductDetailsRepo extends JpaRepository<ProductDetails, Integer> {
	
	@Query("SELECT p FROM ProductDetails p WHERE p.id = :id ")
	List<ProductDetails> getByProductId(int id); 
}
