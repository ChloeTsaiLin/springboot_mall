package com.tsailin.springbootmall.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tsailin.springbootmall.dao.OrderDao;
import com.tsailin.springbootmall.dao.ProductDao;
import com.tsailin.springbootmall.dto.BuyItem;
import com.tsailin.springbootmall.dto.CreateOrderRequest;
import com.tsailin.springbootmall.model.Order;
import com.tsailin.springbootmall.model.OrderItem;
import com.tsailin.springbootmall.model.Product;
import com.tsailin.springbootmall.service.OrderService;

@Component
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ProductDao productDao;
	
	@Transactional
	@Override
	public Integer createOrder(Integer userId, @Valid CreateOrderRequest createOrderRequest) {
		System.out.println("OrderServiceImpl: createOrder");
		int totalAmount = 0;
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
			Product product = productDao.getProductById(buyItem.getProductId());
			
			int amount = buyItem.getQuantity() * product.getPrice();
			totalAmount += amount;
			
			//transform BuyItem to OrderItem
			OrderItem orderItem = new OrderItem();
			orderItem.setProductId(buyItem.getProductId());
			orderItem.setQuantity(buyItem.getQuantity());
			orderItem.setAmount(amount);
			
			orderItemList.add(orderItem);
		}
		
		Integer orderId = orderDao.createOrder(userId, totalAmount);
		orderDao.createOrderItems(orderId, orderItemList);
		
		return orderId;
	}

	@Override
	public Order getOrderById(Integer orderId) {
		Order order = orderDao.getOrderById(orderId);
		
		List<OrderItem> orderItemList = orderDao.getOrderItemByOrderId(orderId);
		
		order.setOrderItemList(orderItemList);
		
		return order;
	}
	
	
}
