package com.example.Project3.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Project3.dto.ResponseDTO;
import com.example.Project3.dto.UserDTO;
import com.example.Project3.service.JWTService;
import com.example.Project3.service.UserService;



@RestController
@RequestMapping("/")
public class LoginController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JWTService jwtService;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public ResponseDTO<String> login(@RequestParam("username") String username,
						@RequestParam("password") String password){
		
		//check tài khoản, mật khẩu
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
		//login success, gen token
		return ResponseDTO.<String>builder()
					.status(200)
					.msg("ok")
					.data(jwtService.create(username))
					.build();
	}
	
	//Lấy người dùng hiện tại
	@GetMapping("/me")
	@PreAuthorize("isAuthenticated()")
	public ResponseDTO<UserDTO> me(Principal principal) {
		String username=  principal.getName();
		UserDTO userDTO = userService.findByUserName(username);
		return ResponseDTO.<UserDTO>builder()
				.status(200)
				.msg("ok")
				.data(userDTO)
				.build();
	}
	
	
	
}
