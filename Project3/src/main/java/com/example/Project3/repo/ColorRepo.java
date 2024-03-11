package com.example.Project3.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Project3.entity.Category;
import com.example.Project3.entity.Color;

public interface ColorRepo extends JpaRepository<Color, Integer> {
	
}
