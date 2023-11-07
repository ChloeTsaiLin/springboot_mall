package com.tsailin.springbootmall.dao;

import java.util.List;

import javax.validation.Valid;

import com.tsailin.springbootmall.dto.ProductRequest;
import com.tsailin.springbootmall.model.Product;

public interface ProductDao {

	Product getProductById(Integer productId);

	Integer createProduct(ProductRequest productRequest);

	void updateProduct(Integer productId, @Valid ProductRequest productRequest);

	void deleteProductById(Integer productId);

	List<Product> getProducts();
}
