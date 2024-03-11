package com.example.Project3.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Project3.entity.OrderDetails;

public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {
	@Query("SELECT p FROM OrderDetails p WHERE p.order.id = :id ")
	List<OrderDetails> getByOrderId(int id); 
}
