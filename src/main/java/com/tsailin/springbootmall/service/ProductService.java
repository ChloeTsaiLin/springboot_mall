package com.tsailin.springbootmall.service;

import org.springframework.stereotype.Service;

import com.tsailin.springbootmall.model.Product;

@Service
public interface ProductService {
	Product getProductById(Integer productId);
}
