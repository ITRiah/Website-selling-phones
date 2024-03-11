package com.example.Project3.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Project3.dto.ColorDTO;
import com.example.Project3.entity.Color;
import com.example.Project3.repo.ColorRepo;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

public interface ColorService {
	void create (ColorDTO colorDTO);
	void update (ColorDTO colorDTO);
	void delete (int id);
	ColorDTO getById(int id);
	List<ColorDTO> getAll();
}

@Service
 class ColorServiceImpl implements ColorService{
	
	@Autowired
	ColorRepo ColorRepo;

	@Override
	@Transactional
	public void create(ColorDTO colorDTO) {
		Color color = new ModelMapper().map(colorDTO, Color.class);
		ColorRepo.save(color);
	}

	@Override
	public void update(ColorDTO colorDTO) {
		Color color = ColorRepo.findById(colorDTO.getId()).orElseThrow(NoResultException :: new);
		color.setName(colorDTO.getName());
		ColorRepo.save(color);
	}

	@Override
	public void delete(int id) {
		ColorRepo.deleteById(id);
	}

	@Override
	public List<ColorDTO> getAll() {
		List<Color> colors = ColorRepo.findAll();
		
		List<ColorDTO> colorDTOs = colors.stream().map(Color -> new ModelMapper().map(Color, ColorDTO.class)) 
											.collect(Collectors.toList());
		return colorDTOs;
	}

	@Override
	public ColorDTO getById(int id) {
		Color Color = ColorRepo.findById(id).orElseThrow(NoResultException :: new);
		
		ColorDTO ColorDTO = new ModelMapper().map(Color, ColorDTO.class);
		
		return ColorDTO;
	}

	
}
