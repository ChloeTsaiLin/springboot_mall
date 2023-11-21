package com.tsailin.springbootmall.service;

import javax.validation.Valid;

import com.tsailin.springbootmall.dto.CreateOrderRequest;
import com.tsailin.springbootmall.model.Order;

public interface OrderService {

	Integer createOrder(Integer userId, @Valid CreateOrderRequest createOrderRequest);

	Order getOrderById(Integer orderId);

}
