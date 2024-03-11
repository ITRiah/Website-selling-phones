package com.example.Project3.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.Project3.entity.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {
	@Query("SELECT c FROM Role c WHERE c.name LIKE :x ")
	Page<Role> searchByName(@Param("x") String s, Pageable pageable);
	
	Role findByName(String name);
}
