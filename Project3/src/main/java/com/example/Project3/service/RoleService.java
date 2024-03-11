package com.example.Project3.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Project3.dto.RoleDTO;
import com.example.Project3.dto.SearchDTO;
import com.example.Project3.entity.Role;
import com.example.Project3.repo.RoleRepo;

import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

public interface RoleService {
	void create (RoleDTO roleDTO);
	void update (RoleDTO roleDTO);
	void delete (int id);
	Page<RoleDTO> search(SearchDTO searchDTO);
}

@Service
 class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleRepo roleRepo;

	@Override
	@Transactional
	public void create(RoleDTO roleDTO) {
		Role role = new ModelMapper().map(roleDTO, Role.class);
		roleRepo.save(role);
		
		//trả về id
		roleDTO.setId(role.getId());		
	}

	@Override
	@Transactional
	public void update(RoleDTO roleDTO) {
		Role role = roleRepo.findById(roleDTO.getId()).orElseThrow(NoResultException :: new);
		role.setName(roleDTO.getName());
		roleRepo.save(role);
	}

	@Override
	public void delete(int id) {
		roleRepo.deleteById(id);
	}

	@Override
	public Page<RoleDTO> search(SearchDTO searchDTO) {
		if(searchDTO.getCurrentPage() == null)
			searchDTO.setCurrentPage(0);
		if(searchDTO.getSize() == null)
			searchDTO.setSize(5);
		
		if(searchDTO.getSortedField() == null)
			searchDTO.setSortedField("id");
		
		Sort sort = Sort.by(searchDTO.getSortedField()).ascending();
		
		Pageable pageable = PageRequest.of(searchDTO.getCurrentPage(), searchDTO.getSize(), sort);
		Page<Role> page = roleRepo.searchByName("%" + searchDTO.getKeyword() + "%", pageable);
		
		Page<RoleDTO> page2 = page.map(role -> new ModelMapper().map(role, RoleDTO.class));
		return page2;
	}
	
}
