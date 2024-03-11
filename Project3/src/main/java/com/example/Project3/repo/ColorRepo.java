package com.example.Project3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Project3.entity.Color;

public interface ColorRepo extends JpaRepository<Color, Integer> {
	
}
