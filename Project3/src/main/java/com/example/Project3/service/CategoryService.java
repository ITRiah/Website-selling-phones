package com.example.Project3.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project3.dto.CategoryDTO;
import com.example.Project3.entity.Category;
import com.example.Project3.repo.CategoryRepo;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

public interface CategoryService {
	void create (CategoryDTO categoryDTO);
	void update (CategoryDTO categoryDTO);
	void delete (int id);
	CategoryDTO getById(int id);
	List<CategoryDTO> getAll();
}

@Service
 class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryRepo categoryRepo;

	@Override
	@Transactional
	public void create(CategoryDTO CategoryDTO) {
		Category Category = new ModelMapper().map(CategoryDTO, Category.class);
		categoryRepo.save(Category);
	}

	@Override
	public void update(CategoryDTO categoryDTO) {
		Category category = categoryRepo.findById(categoryDTO.getId()).orElseThrow(NoResultException :: new);
		category.setName(categoryDTO.getName());
		System.out.println(categoryDTO.getId() + categoryDTO.getName());
		categoryRepo.save(category);
	}

	@Override
	public void delete(int id) {
		categoryRepo.deleteById(id);
	}

	@Override
	public List<CategoryDTO> getAll() {
		List<Category> Categorys = categoryRepo.findAll();
		
		List<CategoryDTO> CategoryDTOs = Categorys.stream().map(Category -> new ModelMapper().map(Category, CategoryDTO.class)) 
											.collect(Collectors.toList());
		return CategoryDTOs;
	}

	@Override
	public CategoryDTO getById(int id) {
		Category Category = categoryRepo.findById(id).orElseThrow(NoResultException :: new);
		
		CategoryDTO CategoryDTO = new ModelMapper().map(Category, CategoryDTO.class);
		
		return CategoryDTO;
	}

	
}
