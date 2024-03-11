package com.example.Project3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // enable to spring security
@EnableGlobalMethodSecurity(securedEnabled = true,
prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
	
	@Autowired
	UserDetailsService detailsService;
	
	@Autowired
	JwtTokenFilter jwtTokenFilter;
	
	//Xác thực đăng  nhập
	@Autowired //Tạo bean cho tham số vì autowire trên hàm.
	public void config (AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(detailsService)
			.passwordEncoder(new BCryptPasswordEncoder());
		
		
//		auth.inMemoryAuthentication()
//		.withUser("test")
//		.password(new BCryptPasswordEncoder().encode("123456")) //tránh người quản trị biết mật khẩu , encode khi lưu vào db
////		.roles("ROLE_ADMIN") //mặc định là role_+ quyền
//		.authorities("ADMIN") // fix cứng là admin
//		.and().passwordEncoder(new BCryptPasswordEncoder()); //check ngược lại mật khẩu sau khi encode , đăng nhập
	}
	
	@Bean // để check mật khẩu tài khoản bên LogController , tạo bean cho kiểu trả về khác với @Autowired
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	//Phân quyền theo đường dẫn và phương thức xác thực
	public SecurityFilterChain config(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeRequests()
			.requestMatchers("/admin/**")
			.hasAnyAuthority("ROLE_ADMIN", "ROLE_SUBADMIN")
			.requestMatchers("/customer/**")
			.authenticated()
			.anyRequest().permitAll()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
			.and().httpBasic()
			.and().csrf().disable();
			
			//chèn filter bên jwt trước bên check tài khoản của spring vì dùng bearer
			httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
			
		return httpSecurity.build();			
	}
}
