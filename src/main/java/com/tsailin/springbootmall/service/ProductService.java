package com.tsailin.springbootmall.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.tsailin.springbootmall.dto.ProductRequest;
import com.tsailin.springbootmall.model.Product;

@Service
public interface ProductService {
	Product getProductById(Integer productId);

	Integer createProduct(ProductRequest productRequest);

	void updateProduct(Integer productId, @Valid ProductRequest productRequest);

	void deleteProductById(Integer productId);
}
