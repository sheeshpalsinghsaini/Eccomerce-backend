package com.ecom.services;

import java.util.List;


//import com.ecom.models.Product;
import com.ecom.payload.ProductDto;

public interface ProductService {

	public ProductDto createProduct(ProductDto productDto);
	public ProductDto updateProduct(ProductDto newProduct,int productId);
	public void deleteProduct(int productId);
	public ProductDto getProduct(int productId);
	public List<ProductDto> getAllProducts();
}
