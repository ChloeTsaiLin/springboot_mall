package com.tsailin.springbootmall.dao;

import java.util.List;

import com.tsailin.springbootmall.model.Order;
import com.tsailin.springbootmall.model.OrderItem;

public interface OrderDao {

	Integer createOrder(Integer userId, int totalAmount);

	void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

	Order getOrderById(Integer orderId);

	List<OrderItem> getOrderItemByOrderId(Integer orderId);

}
