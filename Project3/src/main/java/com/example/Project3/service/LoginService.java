package com.example.Project3.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Project3.entity.Role;
import com.example.Project3.entity.User;
import com.example.Project3.repo.UserRepo;

@Service
public class LoginService implements UserDetailsService {

	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Tìm user theo username , username is unique
		User user = userRepo.findByUsername(username);

		if (user != null) {
			//Tạo list quyền
			List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();

			//Lấy quyền của user thêm vào list quyền
			for (Role role : user.getRoles()) {
				list.add(new SimpleGrantedAuthority(role.getName()));
			}

			return new org.springframework.security.core.userdetails.User(username, user.getPassword(), list);
		}
		else {
			throw new UsernameNotFoundException("not found");
		}
	}
}
