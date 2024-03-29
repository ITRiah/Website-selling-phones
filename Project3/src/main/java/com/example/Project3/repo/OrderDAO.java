package com.example.Project3.repo;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.Project3.entity.Orders;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class OrderDAO { //Phù hợp cho việc viết những câu lệnh SQL khó cần if else , cộng trừ câu lệnh
	@PersistenceContext // cho Spring biết rằng trường này nên được inject với một EntityManager được quản lý bởi JPA.
	EntityManager entityManager; 
	
	@SuppressWarnings("unchecked")//để thông báo cho trình biên dịch Java rằng việc sử dụng 
								  //kiểu dữ liệu không kiểm tra có thể an toàn trong ngữ cảnh cụ thể
	public List<Orders> orders(Date s){// neu muon phan trang can them tham so start, end.
		String jpql = "SELECT o FROM Order o WHERE o.createAt >= :x ";
		
//		String jpql2 = "SELECT o FROM Order o WHERE o.createAt < :x ";
//		if(1 > 0)
//			jpql = jpql2;
		
		return entityManager.createQuery(jpql)
				.setParameter("x", s)
				.setMaxResults(10) //10 records per page
				.setFirstResult(0)
				.getResultList();
	}
}
