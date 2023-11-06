package com.tsailin.springbootmall.dao;

import com.tsailin.springbootmall.dto.ProductRequest;
import com.tsailin.springbootmall.model.Product;

public interface ProductDao {

	Product getProductById(Integer productId);

	Integer createProduct(ProductRequest productRequest);
}
