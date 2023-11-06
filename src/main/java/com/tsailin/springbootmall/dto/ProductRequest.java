package com.tsailin.springbootmall.dto;


import javax.validation.constraints.NotNull;

import com.tsailin.springbootmall.constant.ProductCategory;

public class ProductRequest {
	
	@NotNull
	private String productName;
	
	@NotNull
	private ProductCategory category;
	private String imageUrl;
	
	@NotNull
	private Integer price;	
	
	@NotNull
	private Integer stock;		
	private String productDesc;
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public ProductCategory getCategory() {
		return category;
	}
	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}	

	
}
