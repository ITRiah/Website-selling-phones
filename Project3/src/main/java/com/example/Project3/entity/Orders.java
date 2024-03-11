package com.example.Project3.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Orders extends TimeAuditable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	private String status;//new, pending , active
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "order" , cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetails> orderDetails;
	
	
	
	
}
