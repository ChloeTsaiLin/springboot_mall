package com.tsailin.springbootmall.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsailin.springbootmall.dto.CreateOrderRequest;
import com.tsailin.springbootmall.dto.OrderQueryParams;
import com.tsailin.springbootmall.model.Order;
import com.tsailin.springbootmall.service.OrderService;
import com.tsailin.springbootmall.util.Page;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/users/{userId}/orders")
	private ResponseEntity<Order> createOrder(@PathVariable Integer userId,
											  @RequestBody @Valid CreateOrderRequest createOrderRequest){
		Integer orderId = orderService.createOrder(userId, createOrderRequest);
		Order order = orderService.getOrderById(orderId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}
	
	@GetMapping("/users/{userId}/orders")
	private ResponseEntity<Page<Order>> getOrders(@PathVariable Integer userId,
			
												  //Sort condition
												  @RequestParam(defaultValue = "created_date") String orderBy,
												  
												  //Pagination
												  @RequestParam(defaultValue = "5") @Max(50) @Min(1) Integer limit,
												  @RequestParam(defaultValue = "0") @Min(0) Integer offset){
		OrderQueryParams orderQueryParams = new OrderQueryParams();
		orderQueryParams.setUserId(userId);
		orderQueryParams.setOrderBy(orderBy);
		orderQueryParams.setLimit(limit);
		orderQueryParams.setOffset(offset);
		
		//Pagination
		Page<Order> page = new Page<>();
		page.setLimit(limit);
		page.setOffset(offset);
		page.setTotal(orderService.countOrder(orderQueryParams));
		page.setResults(orderService.getOrders(orderQueryParams));
		
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
}
