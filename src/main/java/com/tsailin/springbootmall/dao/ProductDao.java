package com.tsailin.springbootmall.dao;

import com.tsailin.springbootmall.model.Product;

public interface ProductDao {

	Product getProductById(Integer productId);
}
