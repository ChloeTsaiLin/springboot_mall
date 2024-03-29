package com.tsailin.springbootmall.controller;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsailin.springbootmall.constant.ProductCategory;
import com.tsailin.springbootmall.dto.ProductRequest;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    
	
    //Get Product
	@Test
	void testGetProduct_success() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/products/{productId}", 1);
		
		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.productName").value("Apple"))
			.andExpect(jsonPath("$.category").value("FRESH_PRODUCE"))
			.andExpect((ResultMatcher) jsonPath("$.imageUrl", notNullValue()))
			.andExpect((ResultMatcher) jsonPath("$.price", notNullValue()))
			.andExpect((ResultMatcher) jsonPath("$.stock", notNullValue()))
			.andExpect((ResultMatcher) jsonPath("$.productDesc", notNullValue()))
			.andExpect((ResultMatcher) jsonPath("$.createdDate", notNullValue()))
			.andExpect((ResultMatcher) jsonPath("$.lastModifiedDate", notNullValue()));
				
	}
	
	@Test
	void testGetProduct_notFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/products/{productId}", 2000);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
	}
	

	// Create Product
	@Test
	void testCreateProduct_success() throws Exception {
		ProductRequest productRequest = new ProductRequest();
		productRequest.setProductName("test create product");
		productRequest.setCategory(ProductCategory.BEVERAGES);
		productRequest.setImageUrl("http://testCreateProduct.jpg");
		productRequest.setPrice(222);
		productRequest.setStock(20);
		productRequest.setProductDesc("test create product: description.");
		
		String json = objectMapper.writeValueAsString(productRequest);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);
		
		mockMvc.perform(requestBuilder)
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.productName").value("test create product"))
			.andExpect(jsonPath("$.category").value("BEVERAGES"))
			.andExpect((ResultMatcher) jsonPath("$.imageUrl", notNullValue()))
			.andExpect((ResultMatcher) jsonPath("$.price").value(222))
			.andExpect((ResultMatcher) jsonPath("$.stock").value(20))
			.andExpect((ResultMatcher) jsonPath("$.productDesc").value("test create product: description."))
			.andExpect((ResultMatcher) jsonPath("$.createdDate", notNullValue()))
			.andExpect((ResultMatcher) jsonPath("$.lastModifiedDate", notNullValue()));
		
	}
	
	@Test
	void testCreateProduct_illegalArgument() throws Exception {
		ProductRequest productRequest = new ProductRequest();
		productRequest.setProductName("test create product");
		productRequest.setPrice(222);
		productRequest.setStock(20);
		
		String json = objectMapper.writeValueAsString(productRequest);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);
		
		mockMvc.perform(requestBuilder)
			.andExpect(status().isBadRequest());
	}

	
	//Update Product
	@Test
	void testUpdateProduct_success() throws Exception{
		ProductRequest productRequest = new ProductRequest();
		productRequest.setProductName("test update product");
		productRequest.setCategory(ProductCategory.BEVERAGES);
		productRequest.setImageUrl("http://testCreateProduct.jpg");
		productRequest.setPrice(222);
		productRequest.setStock(20);
		productRequest.setProductDesc("test update product: description.");
		
		String json = objectMapper.writeValueAsString(productRequest);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/products/{productId}", 2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);
		
		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.productName").value("test update product"))
			.andExpect(jsonPath("$.category").value("BEVERAGES"))
			.andExpect(jsonPath("$.imageUrl").value("http://testCreateProduct.jpg"))
			.andExpect(jsonPath("$.price").value(222))
			.andExpect(jsonPath("$.stock").value(20))
			.andExpect(jsonPath("$.productDesc").value("test update product: description."))
			.andExpect((ResultMatcher) jsonPath("$.createdDate", notNullValue()))
			.andExpect((ResultMatcher) jsonPath("$.lastModifiedDate", notNullValue()));
	}

	@Test
	void testUpdateProduct_illegalArgument() throws Exception{
		ProductRequest productRequest = new ProductRequest();
		productRequest.setProductName("test update product");
		
		String json = objectMapper.writeValueAsString(productRequest);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/products/{productId}", 3)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);
		
		mockMvc.perform(requestBuilder)
			.andExpect(status().isBadRequest());
	}

	@Test
	void testUpdateProduct_productNotFound() throws Exception{
		ProductRequest productRequest = new ProductRequest();
		productRequest.setProductName("test update product");
		
		String json = objectMapper.writeValueAsString(productRequest);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/products/{productId}", 20000)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json);
		
		mockMvc.perform(requestBuilder)
			.andExpect(status().isBadRequest());
	}
	
	//Delete Product
	@Test
	void testDeleteProduct_success() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/products/{productId}", 4);
		
		mockMvc.perform(requestBuilder)
			.andExpect(status().isNoContent());
	}
	
	@Test
	void testDeleteProduct_NonExistingProduct() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/products/{productId}", 2000);
		
		mockMvc.perform(requestBuilder)
			.andExpect(status().isNoContent());
	}
	
    //Get Product-list
  @Test
  void testGetProducts_default() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/products");
		
		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.limit").value(5))
			.andExpect(jsonPath("$.offset").value(0))
			.andExpect(jsonPath("$.total").isNotEmpty())
			.andExpect(jsonPath("$.results.length()").value(5))
			.andExpect(jsonPath("$.results[0].productId").isNotEmpty())
			.andExpect(jsonPath("$.results[4].productId").isNotEmpty());
  }

  @Test
  void testGetProducts_search() throws Exception{
	  RequestBuilder requestBuilder = MockMvcRequestBuilders
			  .get("/products")
			  .param("category", "FRESH_PRODUCE")
			  .param("search", "Avocados");
	  
	  mockMvc.perform(requestBuilder)
	  .andDo(print())
	  .andExpect(status().isOk())
	  .andExpect(jsonPath("$.limit").value(5))
	  .andExpect(jsonPath("$.offset").value(0))
	  .andExpect(jsonPath("$.total").isNotEmpty())
	  .andExpect(jsonPath("$.results.length()").value(2))
	  .andExpect(jsonPath("$.results[0].category").value("FRESH_PRODUCE"))
	  .andExpect(jsonPath("$.results[1].category").value("FRESH_PRODUCE"));
  }
  
  @Test
  void testGetProducts_sort() throws Exception{
	  RequestBuilder requestBuilder = MockMvcRequestBuilders
			  .get("/products")
			  .param("orderBy", "product_id");
	  
	  mockMvc.perform(requestBuilder)
	  .andDo(print())
	  .andExpect(status().isOk())
	  .andExpect(jsonPath("$.limit").value(5))
	  .andExpect(jsonPath("$.offset").value(0))
	  .andExpect(jsonPath("$.total").isNotEmpty())
	  .andExpect(jsonPath("$.results.length()").value(5))
	  .andExpect(jsonPath("$.results[0].productId").value(7))
	  .andExpect(jsonPath("$.results[1].productId").value(6));
  }
  
  @Test
  void testGetProducts_pagination() throws Exception{
	  RequestBuilder requestBuilder = MockMvcRequestBuilders
			  .get("/products")
			  .param("limit", "2")
			  .param("offset", "1");
	  
	  mockMvc.perform(requestBuilder)
	  .andDo(print())
	  .andExpect(status().isOk())
	  .andExpect(jsonPath("$.limit").value(2))
	  .andExpect(jsonPath("$.offset").value(1))
	  .andExpect(jsonPath("$.total").isNotEmpty())
	  .andExpect(jsonPath("$.results.length()").value(2))
	  .andExpect(jsonPath("$.results[0].productId").isNotEmpty())
	  .andExpect(jsonPath("$.results[1].productId").isNotEmpty());
  }

}
