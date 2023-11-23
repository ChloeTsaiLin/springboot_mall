package com.tsailin.springbootmall.service;

import java.util.List;

import javax.validation.Valid;

import com.tsailin.springbootmall.dto.CreateOrderRequest;
import com.tsailin.springbootmall.dto.OrderQueryParams;
import com.tsailin.springbootmall.model.Order;

public interface OrderService {

	Integer createOrder(Integer userId, @Valid CreateOrderRequest createOrderRequest);

	Order getOrderById(Integer orderId);

	Integer countOrder(OrderQueryParams orderQueryParams);

	List<Order> getOrders(OrderQueryParams orderQueryParams);

}
