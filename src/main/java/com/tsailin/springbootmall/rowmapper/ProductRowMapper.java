package com.tsailin.springbootmall.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tsailin.springbootmall.model.Product;

public class ProductRowMapper implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setProductId(rs.getInt("product_id"));
		product.setProductName(rs.getString("product_name"));
		product.setCategory(rs.getString("category"));
		product.setImageUrl(rs.getString("image_url"));
		product.setPrice(rs.getInt("price"));
		product.setStock(rs.getInt("stock"));
		product.setProductDesc(rs.getString("product_desc"));
		product.setCreatedDate(rs.getDate("created_date"));
		product.setLastModifiedDate(rs.getDate("last_modified_date"));
		return product;
	}

	
}
