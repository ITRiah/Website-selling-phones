package com.example.Project3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project3.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
