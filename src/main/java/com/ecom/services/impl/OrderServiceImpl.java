package com.ecom.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.ResourceNotFoundException;
import com.ecom.models.Order;
import com.ecom.payload.OrderDto;
import com.ecom.repositries.OrderRepository;
import com.ecom.services.OrderService;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ModelMapper mapper;
	
	
	//create order
	@Override
	public OrderDto create(OrderDto orderDto) {
		Order order = this.mapper.map(orderDto, Order.class);
		Order savedOrder = this.orderRepository.save(order);
		return this.mapper.map(savedOrder, OrderDto.class);
	}

	//update order
	@Override
	public OrderDto update(OrderDto newOrderDto, int orderId) {
		Order order = this.orderRepository.findById(orderId).orElseThrow(()->new ResourceNotFoundException("Order with "+orderId+" Not found on server"));
		order.setOrderCreated(newOrderDto.getOrderCreated());
		order.setOrderDelivered(newOrderDto.getOrderDelivered());
		order.setOrderStatus(newOrderDto.getOrderStatus());
		order.setBillingAddress(newOrderDto.getBillingAddress());
		order.setPaymentStatus(newOrderDto.getPaymentStatus());
		order.setTotolAmount(newOrderDto.getTotolAmount());
		return this.mapper.map(order, OrderDto.class);
	}

	//delete order
	@Override
	public void delete(int orderId) {
		Order order = this.orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order with "+orderId+" not found on server"));
		this.orderRepository.delete(order);
	}

	//get single order
	@Override
	public OrderDto get(int orderId) {
		Order order = this.orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException("Order with "+orderId+" not found on server"));
		return this.mapper.map(order, OrderDto.class);
	}

	//get all orders
	@Override
	public List<OrderDto> getAll() {
		List<Order> allOrders = this.orderRepository.findAll();
		List<OrderDto> dtos = allOrders.stream().map((order)->this.mapper.map(order, OrderDto.class)).collect(Collectors.toList());
		return dtos;
	}
	
	
}
