package com.tsailin.springbootmall.dao;

import java.util.List;

import javax.validation.Valid;

import com.tsailin.springbootmall.dto.ProductQueryParams;
import com.tsailin.springbootmall.dto.ProductRequest;
import com.tsailin.springbootmall.model.Product;

public interface ProductDao {
	
	Integer countProduct(ProductQueryParams productQueryParams);

	List<Product> getProducts(ProductQueryParams productQueryParams);

	Product getProductById(Integer productId);

	Integer createProduct(ProductRequest productRequest);

	void updateProduct(Integer productId, @Valid ProductRequest productRequest);
	
	void updateStock(Integer productId, Integer stock);

	void deleteProductById(Integer productId);

}
