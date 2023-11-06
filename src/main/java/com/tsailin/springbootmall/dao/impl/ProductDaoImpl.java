package com.tsailin.springbootmall.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.tsailin.springbootmall.dao.ProductDao;
import com.tsailin.springbootmall.dto.ProductRequest;
import com.tsailin.springbootmall.model.Product;
import com.tsailin.springbootmall.rowmapper.ProductRowMapper;

@Component
public class ProductDaoImpl implements ProductDao{
	@Autowired
	private NamedParameterJdbcTemplate nameParameterJdbcTemplate;
	
	@Override
	public Product getProductById(Integer productId) {
		String sql = "SELECT * FROM product WHERE product_id = :productId";
		
		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);
		
		List<Product> productList = nameParameterJdbcTemplate
									.query(sql, map, new ProductRowMapper());
		if(productList.size() > 0) {
			return productList.get(0);
		} else {
			return null;			
		}
	}

	@Override
	public Integer createProduct(ProductRequest productRequest) {
		String sql = "INSERT INTO product(product_name, category, image_url, "
				+ "price, stock, product_desc, created_date, last_modified_date) "
				+ "VALUES (:productName, :category, :imageUrl, :price, :stock, "
				+ ":productDesc, :createdDate, :lastModifiedDate)";
		
		Map<String, Object> map = new HashMap<>();
		map.put("productName", productRequest.getProductName());
		map.put("category", productRequest.getCategory().toString());
		map.put("imageUrl", productRequest.getImageUrl());
		map.put("price", productRequest.getPrice());
		map.put("stock", productRequest.getStock());
		map.put("productDesc", productRequest.getProductDesc());
		
		Date now = new Date();
		map.put("createdDate", now);
		map.put("lastModifiedDate", now);
		
		KeyHolder keyholder = new GeneratedKeyHolder();
		nameParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyholder);
		int productId = keyholder.getKey().intValue();
		
		return productId;
	}

	@Override
	public void updateProduct(Integer productId, @Valid ProductRequest productRequest) {
		String sql = "UPDATE product SET product_name = :productName, category = :category, "
				+ "image_url = :imageUrl, price = :price, stock = :stock, "
				+ "product_desc = :productDesc, last_modified_date = :lastModifiedDate "
				+ "WHERE product_id = :productId";
		
		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);
		
		map.put("productName", productRequest.getProductName());
		map.put("category", productRequest.getCategory().toString());
		map.put("imageUrl", productRequest.getImageUrl());
		map.put("price", productRequest.getPrice());
		map.put("stock", productRequest.getStock());
		map.put("productDesc", productRequest.getProductDesc());

		map.put("lastModifiedDate", new Date());
		
		nameParameterJdbcTemplate.update(sql, map);
	}
	

}
