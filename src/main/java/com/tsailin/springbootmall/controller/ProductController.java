package com.tsailin.springbootmall.controller;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tsailin.springbootmall.constant.ProductCategory;
import com.tsailin.springbootmall.dto.ProductQueryParams;
import com.tsailin.springbootmall.dto.ProductRequest;
import com.tsailin.springbootmall.model.Product;
import com.tsailin.springbootmall.service.ProductService;
import com.tsailin.springbootmall.util.Page;

@RestController
@Validated
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public ResponseEntity<Page<Product>> getProducts(//Search condition
													@RequestParam(required = false) ProductCategory category,
													@RequestParam(required = false) String search,
													
													//Sort condition
													@RequestParam(defaultValue = "last_modified_date") String orderBy,
													@RequestParam(defaultValue = "DESC") String sort,
													
													//Pagination
													@RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
													@RequestParam(defaultValue = "0") @Min(0) Integer offset){
		ProductQueryParams productQueryParams = new ProductQueryParams();
		productQueryParams.setCategory(category);
		productQueryParams.setSearch(search);
		productQueryParams.setOrderBy(orderBy);
		productQueryParams.setSort(sort);
		productQueryParams.setLimit(limit);
		productQueryParams.setOffset(offset);
		
		//Pagination
		Page<Product> page = new Page<>();
		page.setLimit(limit);
		page.setOffset(offset);
		page.setTotal(productService.countProduct(productQueryParams));
		page.setResult(productService.getProducts(productQueryParams));
		
		return ResponseEntity.status(HttpStatus.OK).body(page);
	}
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
		Product product = productService.getProductById(productId);
		if (product != null) {
			return ResponseEntity.status(HttpStatus.OK).body(product);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
		Integer productId = productService.createProduct(productRequest);
		Product product = productService.getProductById(productId);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
												@RequestBody @Valid ProductRequest productRequest){
		
		Product product = productService.getProductById(productId);
		if (product != null) {
			productService.updateProduct(productId, productRequest);
			Product updateProduct = productService.getProductById(productId);
			
			return ResponseEntity.status(HttpStatus.OK).body(updateProduct);			
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
		productService.deleteProductById(productId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	}
