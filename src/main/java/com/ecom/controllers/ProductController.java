package com.ecom.controllers;



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
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.ecom.payload.ApiResponse;
import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;
import com.ecom.services.ProductService;
@RestController
@RequestMapping("/")
public class ProductController {

	@Autowired
	private ProductService productService;
	//localhost:8080/product/create-product
	
	
	//create product
	//http://localhost:800/categories/14/products
	@PostMapping("/categories/{categoryId}/products")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product,@PathVariable int categoryId) {
		ProductDto createdProduct = productService.createProduct(product,categoryId);
		return new ResponseEntity<ProductDto>(createdProduct,HttpStatus.CREATED);
	}
	
	
	//getting all the products
	/*
	 * http://localhost:8080/products-> default size
	 * http://localhost:8080/products?pageNumber=2&pageSize=2 -> url with parameter
	 */
	//get all products
	@GetMapping("/products")
	public ProductResponse listAllProducts(
			@RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "3",required = false) int pageSize
			){
		ProductResponse response = productService.getAllProducts(pageNumber,pageSize);
		return response;
	}
	
	//fetch product category by.
		@GetMapping("/categories/{categoryId}/products")
		public ResponseEntity<ProductResponse> getProductOfCategory(@PathVariable int categoryId,
				@RequestParam(value = "pageNumber", defaultValue = "0",required = false) int pageNumber,
				@RequestParam(value = "pageSize", defaultValue = "3",required = false) int pageSize
				){
			ProductResponse listOfProducts = productService.getProductsByCategory(categoryId,pageNumber,pageSize);
			return new ResponseEntity<ProductResponse>(listOfProducts,HttpStatus.OK);
		}
	
	//update product
	//send URL with productId -> localhost:8080/product/update-product/12
	@PutMapping("/products/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable("productId") int pid,@RequestBody ProductDto newProduct) {
		ProductDto updateProduct = productService.updateProduct(newProduct, pid);
		return new ResponseEntity<ProductDto>(updateProduct,HttpStatus.OK);
	}
	
	
	
	//delete product
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<ApiResponse> deleteProduct(@PathVariable("productId")int pid) {
		productService.deleteProduct(pid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Product delete successfully!!",false),HttpStatus.OK);
	}
	
	//fetch single product...
	//http://localhost:8080/products/12
	@GetMapping("/products/{productId}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable("productId")int pid) {
		ProductDto product = productService.getProduct(pid);
		return new ResponseEntity<ProductDto>(product,HttpStatus.OK);
	}
	
	
	
	
}
