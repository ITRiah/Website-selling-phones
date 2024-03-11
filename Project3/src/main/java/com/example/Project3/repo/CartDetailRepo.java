package com.example.Project3.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Project3.entity.CartDetail;

public interface CartDetailRepo extends JpaRepository<CartDetail, Integer> {
	@Query("SELECT p FROM CartDetail p WHERE p.cart.id = :id ")
	List<CartDetail> getByCartId(int id); 
}
