package com.ecom.services;



//import com.ecom.models.Product;
import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;

public interface ProductService {

	public ProductDto createProduct(ProductDto productDto,int categoryId);
	public ProductDto updateProduct(ProductDto newProduct,int productId);
	public void deleteProduct(int productId);
	public ProductDto getProduct(int productId);
	public ProductResponse getAllProducts(int pageNumber,int pageSize,String sortBy,String sortDir);
	public ProductResponse getProductsByCategory(int categoryId,int pageNumber,int pageSize);
}
