package com.tsailin.springbootmall.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.tsailin.springbootmall.dao.OrderDao;
import com.tsailin.springbootmall.dto.OrderQueryParams;
import com.tsailin.springbootmall.model.Order;
import com.tsailin.springbootmall.model.OrderItem;
import com.tsailin.springbootmall.rowmapper.OrderItemRowMapper;
import com.tsailin.springbootmall.rowmapper.OrderRowMapper;

@Component
public class OrderDaoImpl implements OrderDao{
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private String addFilterSql(String sql,
								Map<String, Object> map,
								OrderQueryParams orderQueryParams) {
		if(orderQueryParams.getUserId() != null) {
			sql += " AND user_id = :userId";
			map.put("userId", orderQueryParams.getUserId());
		} else {
			System.out.println("orderQueryParams.getUserId() == null");
		}
	
		return sql;
	}
	
	@Override
	public Integer createOrder(Integer userId, int totalAmount) {
		String sql = "INSERT INTO order_table (user_id, total_amount, created_date, last_modified_date) "
				+ "VALUES (:userId, :totalAmount, :createdDate, :lastModifiedDate)";
		
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("totalAmount", totalAmount);
		
		Date now = new Date();
		map.put("createdDate", now);
		map.put("lastModifiedDate", now);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
		
		int orderId = keyHolder.getKey().intValue();
		
		return orderId;
	}

	@Override
	public void createOrderItems(Integer orderId, List<OrderItem> orderItemList) {
		String sql ="INSERT INTO order_item (order_id, product_id, quantity, amount) "
				+ "VALUES (:orderId, :productId, :quantity, :amount)";
		
		MapSqlParameterSource[] parameterSources = new MapSqlParameterSource[orderItemList.size()];
		
		for(int i = 0; i < orderItemList.size(); i++) {
			OrderItem orderItem = orderItemList.get(i);
			
			parameterSources[i] = new MapSqlParameterSource();
			parameterSources[i].addValue("orderId", orderId);
			parameterSources[i].addValue("productId", orderItem.getProductId());
			parameterSources[i].addValue("quantity", orderItem.getQuantity());
			parameterSources[i].addValue("amount", orderItem.getAmount());
		}
		namedParameterJdbcTemplate.batchUpdate(sql, parameterSources);
	}

	@Override
	public Order getOrderById(Integer orderId) {
		String sql ="SELECT * FROM order_table WHERE order_id = :orderId";
		
		Map<String, Object> map = new HashMap<>();
		map.put("orderId", orderId);
		
		List<Order> orderList = namedParameterJdbcTemplate.query(sql, map, new OrderRowMapper());
		
		if(orderList.size() > 0) {
			return orderList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<OrderItem> getOrderItemByOrderId(Integer orderId) {
		String sql = "SELECT oi.order_item_id, oi.order_id, oi.product_id, oi.quantity, oi.amount, p.product_name, p.image_url "
				+ "FROM order_item as oi LEFT JOIN product as p ON oi.product_id = p.product_id "
				+ "WHERE oi.order_id = :orderId";
		
		Map<String, Object> map = new HashMap<>();
		map.put("orderId", orderId);
		
		List<OrderItem> orderItemList = namedParameterJdbcTemplate.query(sql, map, new OrderItemRowMapper());
		
		return orderItemList;
	}

	@Override
	public Integer countOrder(OrderQueryParams orderQueryParams) {
		String sql = "SELECT COUNT(*) FROM order_table WHERE 1=1";
		
		Map<String, Object> map = new HashMap<>();
		
		sql = addFilterSql(sql, map, orderQueryParams);
		
		Integer total = namedParameterJdbcTemplate
						.queryForObject(sql, map, Integer.class);
		return total;
	}

	@Override
	public List<Order> getOrders(OrderQueryParams orderQueryParams) {
		String sql = "SELECT * FROM order_table WHERE 1=1";
		
		Map<String, Object> map = new HashMap<>();
		
		sql = addFilterSql(sql, map, orderQueryParams);
		
		sql += " ORDER BY " + orderQueryParams.getOrderBy() + " DESC";
		
		sql += " LIMIT :limit OFFSET :offset"; 
		map.put("limit", orderQueryParams.getLimit());
		map.put("offset", orderQueryParams.getOffset());
		
		List<Order> orderList = namedParameterJdbcTemplate
								.query(sql, map, new OrderRowMapper());
		
		return orderList;
	}
	
	
}
