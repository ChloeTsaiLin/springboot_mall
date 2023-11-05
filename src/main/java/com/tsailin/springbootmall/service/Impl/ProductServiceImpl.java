package com.tsailin.springbootmall.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsailin.springbootmall.dao.ProductDao;
import com.tsailin.springbootmall.model.Product;
import com.tsailin.springbootmall.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDao productDao;
	
	@Override
	public Product getProductById(Integer productId) {
		return productDao.getProductById(productId);
	}

}
