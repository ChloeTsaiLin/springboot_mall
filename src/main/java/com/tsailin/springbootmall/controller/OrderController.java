package com.tsailin.springbootmall.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tsailin.springbootmall.dto.CreateOrderRequest;
import com.tsailin.springbootmall.model.Order;
import com.tsailin.springbootmall.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/users/{userId}/orders")
	private ResponseEntity<Order> createOrder(@PathVariable Integer userId,
											  @RequestBody @Valid CreateOrderRequest createOrderRequest){
		System.out.println("OrderController: createOrder");
		Integer orderId = orderService.createOrder(userId, createOrderRequest);
		Order order = orderService.getOrderById(orderId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}
}
