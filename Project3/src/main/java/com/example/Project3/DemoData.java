package com.example.Project3;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Project3.entity.Role;
import com.example.Project3.entity.User;
import com.example.Project3.repo.RoleRepo;
import com.example.Project3.repo.UserRepo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DemoData implements ApplicationRunner { // thêm 1 admin để đăng nhập
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		// TODO Auto-generated method stub
		log.info("BEGIN");
		
		Role role = new Role();
		role.setName("ROLE_ADMIN");
		
	if(roleRepo.findByName(role.getName()) == null) {
			roleRepo.save(role);
			User user = new User();
			user.setUsername("sysadmin");
			user.setPassword(new BCryptPasswordEncoder().encode("123456"));
			user.setName("SYS ADMIN");
			user.setEmail("vhai31102002@gmail.com");
			user.setBirthdate(new Date());
			user.setRoles(Arrays.asList(role));
			
			userRepo.save(user);
			
			log.info("END");
	}
	}
}
