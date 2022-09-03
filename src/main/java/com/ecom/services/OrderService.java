package com.ecom.services;

import java.util.List;

import com.ecom.payload.OrderDto;

public interface OrderService {

	//create
	OrderDto create(OrderDto orderDto);
	//update
	OrderDto update(OrderDto newOrderDto,int orderId);
	//delete
	void delete(int orderId);
	//get single
	OrderDto get(int orderId);
	//get all
	List<OrderDto> getAll();
}
