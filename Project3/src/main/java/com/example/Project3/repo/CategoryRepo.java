package com.example.Project3.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Project3.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {
	@Query("SELECT c FROM Category c WHERE c.name LIKE :name")
	Page<Category> searchByName( String name, Pageable pageable);
}
