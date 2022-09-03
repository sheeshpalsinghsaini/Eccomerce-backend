package com.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.payload.ApiResponse;
import com.ecom.payload.OrderDto;
import com.ecom.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	//create product
	@PostMapping("/")
	public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto){
		OrderDto createdOrder = this.orderService.create(orderDto);
		return new ResponseEntity<OrderDto>(createdOrder,HttpStatus.CREATED);
	}
	
	//get single product
	@GetMapping("/{ordId}")
	public ResponseEntity<OrderDto> getOrder(@PathVariable int ordId){
		OrderDto orderDto = this.orderService.get(ordId);
		return new ResponseEntity<OrderDto>(orderDto,HttpStatus.OK);
	}
	
	
	//get all products
	@GetMapping("/")
	public ResponseEntity<List<OrderDto>> getAll(){
		List<OrderDto> all = this.orderService.getAll();
		return new ResponseEntity<List<OrderDto>>(all,HttpStatus.OK);
	}
	
	
	//update 
	@PutMapping("/{ordId}")
	public ResponseEntity<OrderDto> update(@RequestBody OrderDto orderDto,@PathVariable int ordId){
		OrderDto update = this.orderService.update(orderDto, ordId);
		return new ResponseEntity<OrderDto>(update,HttpStatus.OK);
	}
	
	//delete
	public ResponseEntity<ApiResponse> delete(@PathVariable int ordId){
		this.orderService.delete(ordId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Order deleted successfully",true),HttpStatus.OK);
	}
	
	
}
