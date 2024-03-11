package com.example.Project3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project3.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

}
