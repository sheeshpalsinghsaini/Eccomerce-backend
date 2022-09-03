package com.ecom.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//diff. identity and auto.
	private int cartId;
//	@OneToOne
//	private User user;
	private Date created;
	private Set<Cart> items = new HashSet<>();
	private String title;
	
	
	
}
