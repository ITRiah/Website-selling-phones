package com.example.Project3.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Project3.dto.SearchDTO;
import com.example.Project3.dto.UserDTO;
import com.example.Project3.entity.User;
import com.example.Project3.repo.UserRepo;

import jakarta.persistence.NoResultException;

@Service //tự tạo một đối tượng UserService và lưu lại
public class UserService { // UserDetailsService: đối tượng trong securiry
	
	@Autowired
	UserRepo userRepo; //đối tương db
	
	public Page<UserDTO> getAll(SearchDTO listUserDTO) {
		
		if(listUserDTO.getCurrentPage() == null)
			listUserDTO.setCurrentPage(0);
		if(listUserDTO.getSize() == null)
			listUserDTO.setSize(3);
		
		Sort sortBy = Sort.by("id").descending();
		
		PageRequest pageRequest = PageRequest.of(listUserDTO.getCurrentPage(), listUserDTO.getSize(), sortBy);
		
		Page<User> userPage = userRepo.findAll(pageRequest);
		
		//ModelMapper
		Page<UserDTO> userDTOPage = userPage.map(user -> new ModelMapper().map(user, UserDTO.class));
		return userDTOPage;
	}
	
	@Transactional
	public void creat(UserDTO userDTO) {
		if(!userRepo.findById(userDTO.getId()).isPresent())
		{
			
			User user = new ModelMapper().map(userDTO, User.class);
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword())); // nên convert khi lưu db
			userRepo.save(user);
		}
	}
	
	public UserDTO getByID(int id) {
		User user = userRepo.getById(id);
		if(user != null)
		{
			UserDTO userDTO = new ModelMapper().map(user, UserDTO.class);
			return userDTO;
		}
		return null;
	}
	
	public UserDTO findByUserName(String username) {
		User user = userRepo.findByUsername(username);
		if(user == null)
			throw new NoResultException();
		return new ModelMapper().map(user, UserDTO.class);
	}
	
	@Transactional
	public void delete(int id) {
		userRepo.deleteById(id);
	}
	
	@Transactional
	public void update(UserDTO userDTO) {
		User current = userRepo.findById(userDTO.getId()).orElseThrow(NoResultException :: new);
		
		if(userDTO.getFile() != null)
		{
			current.setAvatar(userDTO.getAvatar());
			userRepo.save(current);
		}
	}
	
	public void updateImage(int id , String path) {
		User user = userRepo.findById(id).orElseThrow(NoResultException :: new);
		user.setAvatar(path);
		userRepo.save(user);
	}
	
	public List<UserDTO> searchByName(String name){
		List<User> userList = userRepo.searchByName("%" + name + "%");
		List<UserDTO> userDTOList = userList.stream().map(user -> new ModelMapper().map(user, UserDTO.class)).collect(Collectors.toList());
		return userDTOList;
	}
	
	
}
