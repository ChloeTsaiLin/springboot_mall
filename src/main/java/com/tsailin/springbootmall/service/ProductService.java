package com.tsailin.springbootmall.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.tsailin.springbootmall.dto.ProductQueryParams;
import com.tsailin.springbootmall.dto.ProductRequest;
import com.tsailin.springbootmall.model.Product;

public interface ProductService {
	
	Integer countProduct(ProductQueryParams productQueryParams);

	List<Product> getProducts(ProductQueryParams productQueryParams);
	
	Product getProductById(Integer productId);

	Integer createProduct(ProductRequest productRequest);

	void updateProduct(Integer productId, @Valid ProductRequest productRequest);

	void deleteProductById(Integer productId);

}
