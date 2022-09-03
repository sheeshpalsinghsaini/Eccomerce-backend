package com.ecom.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.models.Product;
import com.ecom.payload.ApiResponse;
import com.ecom.payload.ProductDto;
import com.ecom.services.ProductService;
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	//localhost:8080/product/create-product
	@PostMapping(value="/")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
		ProductDto createdProduct = productService.createProduct(product);
		return new ResponseEntity<ProductDto>(createdProduct,HttpStatus.CREATED);
	}
	
	
	//getting all the products
	@GetMapping("/")
	public List<ProductDto> listAllProducts(){
		List<ProductDto> allProducts = productService.getAllProducts();
		return allProducts;
	}
	
	//update product
	//send URL with productId -> localhost:8080/product/update-product/12
	@PutMapping(value="/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") int pid,@RequestBody ProductDto newProduct) {
		ProductDto updateProduct = productService.updateProduct(newProduct, pid);
		return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.OK);
	}
	
	
	
	//delete product
	@DeleteMapping(value="/{productId}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("productId")int pid) {
		productService.deleteProduct(pid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Product delete successfully!!",false),HttpStatus.OK);
	}
	
	//fetch single product...
	@GetMapping(value="/{productId}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable("productId")int pid) {
		ProductDto product = productService.getProduct(pid);
		return new ResponseEntity<ProductDto>(product,HttpStatus.OK);
	}
	
	
	
	
}
