package com.tsailin.springbootmall.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.tsailin.springbootmall.dao.OrderDao;
import com.tsailin.springbootmall.dao.ProductDao;
import com.tsailin.springbootmall.dao.UserDao;
import com.tsailin.springbootmall.dto.BuyItem;
import com.tsailin.springbootmall.dto.CreateOrderRequest;
import com.tsailin.springbootmall.dto.OrderQueryParams;
import com.tsailin.springbootmall.model.Order;
import com.tsailin.springbootmall.model.OrderItem;
import com.tsailin.springbootmall.model.Product;
import com.tsailin.springbootmall.model.User;
import com.tsailin.springbootmall.service.OrderService;

@Component
public class OrderServiceImpl implements OrderService{
	
	private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private UserDao userDao;
	
	@Transactional
	@Override
	public Integer createOrder(Integer userId, @Valid CreateOrderRequest createOrderRequest) {
		//check user exist
		User user = userDao.getUserById(userId);
		
		if(user == null) {
			log.warn("This userId({}) does not exist.", userId);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		int totalAmount = 0;
		List<OrderItem> orderItemList = new ArrayList<OrderItem>();
		
		for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
			Product product = productDao.getProductById(buyItem.getProductId());
			
			//check product (exist? stock?)
			if(product == null) {
				log.warn("This product({}) does not exist.", buyItem.getProductId());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
				
			} else if(product.getStock() < buyItem.getQuantity()) {
				log.warn("This product({}) is out of stock (stock: {}), can't meet the demand (order: {}).",
						buyItem.getProductId(), product.getStock(), buyItem.getQuantity() );
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
			}
			
			//update stock
			productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());
			
			//calculate totalAmount
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

	@Override
	public Integer countOrder(OrderQueryParams orderQueryParams) {
		return orderDao.countOrder(orderQueryParams);
	}

	@Override
	public List<Order> getOrders(OrderQueryParams orderQueryParams) {
		List<Order> orderList = orderDao.getOrders(orderQueryParams);
		
		for(Order order : orderList) {
			List<OrderItem> orderItemList 
				= orderDao.getOrderItemByOrderId(order.getOrderId());
			
			order.setOrderItemList(orderItemList);
		}
		return orderList;
	}
	
	
}
