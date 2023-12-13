package com.tsailin.springbootmall.service.Impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tsailin.springbootmall.dao.ProductDao;
import com.tsailin.springbootmall.dto.ProductQueryParams;
import com.tsailin.springbootmall.dto.ProductRequest;
import com.tsailin.springbootmall.model.Product;
import com.tsailin.springbootmall.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	
	
	@Override
	public Integer countProduct(ProductQueryParams productQueryParams) {
		return productDao.countProduct(productQueryParams);
	}

	@Override
	public List<Product> getProducts(ProductQueryParams productQueryParams) {
		return productDao.getProducts(productQueryParams);
	}
	
	@Override
	public Product getProductById(Integer productId) {
		return productDao.getProductById(productId);
	}

	@Override
	public Integer createProduct(ProductRequest productRequest) {
		return productDao.createProduct(productRequest);
	}

	@Override
	public void updateProduct(Integer productId, @Valid ProductRequest productRequest) {
		productDao.updateProduct(productId, productRequest);
	}

	@Override
	public void deleteProductById(Integer productId) {
		productDao.deleteProductById(productId);
	}
	
}
