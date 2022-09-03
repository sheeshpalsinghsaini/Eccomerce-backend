package com.ecom.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.ResourceClosedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecom.exception.ResourceNotFoundException;
import com.ecom.models.Category;
import com.ecom.models.Product;
import com.ecom.payload.ProductDto;
import com.ecom.payload.ProductResponse;
import com.ecom.repositries.CategoryRepository;
import com.ecom.repositries.ProductRepository;
import com.ecom.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

//	private static final Object ProductDto = null;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	
	//create product
	
	@Override
	public ProductDto createProduct(ProductDto productDto,int categoryId) {
		/*
		 * now we need again to convert dto to product and product to dto
		 * but now we are using model mapper library.
		 */
		Product product = this.mapper.map(productDto, Product.class);
		Category cat = this.categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Given category is not found"));
		product.setCategory(cat);
		Product savedProduct = this.productRepository.save(product);
		return this.mapper.map(savedProduct, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto newProduct, int productId) {
		Product product = this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product with "+productId+" not found on server !!"));
		product.setProductName(newProduct.getProductName());
		product.setProductDesc(newProduct.getProductDesc());
		product.setProductPrice(newProduct.getProductPrice());
		product.setLive(newProduct.isLive());
		product.setStock(newProduct.isStock());
		product.setImageName(newProduct.getImageName());
		Product updatedProduct = this.productRepository.save(product);
		return this.mapper.map(updatedProduct, ProductDto.class);
	}

	@Override
	public void deleteProduct(int productId) {
		Product product = this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product with "+productId+" not found on server !!"));
		this.productRepository.delete(product);
	}

	@Override
	public ProductDto getProduct(int productId) {
		Product product = this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product with "+productId+" not found on server !!"));
		return this.mapper.map(product, ProductDto.class);
	}

	//get all products
	@Override
	public ProductResponse getAllProducts(int pageNumber, int pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<Product> page = this.productRepository.findAll(pageable);
		List<Product> all = page.getContent();
		List<ProductDto> dtos = all.stream().map(product->this.mapper.map(product, ProductDto.class)).collect(Collectors.toList());
		
		ProductResponse response = new ProductResponse();
		response.setContent(dtos);
		//remaining all information has page.
		response.setPageNumber(page.getNumber());
		response.setPageSize(page.getSize());
		response.setTotalElements(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		response.setLastPage(page.isLast());
		return response ;
	}

	
	//get product by category
	@Override
	public ProductResponse getProductsByCategory(int categoryId,int pageNumber,int pageSize) {
		Category category = this.categoryRepository.findById(categoryId).orElseThrow(()-> new ResourceClosedException("Given category is not found"));
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Product> page = this.productRepository.findByCategory(category, pageable);
		List<Product> categories = page.getContent();
		List<ProductDto> dtos = categories.stream().map((product)->this.mapper.map(product, ProductDto.class)).collect(Collectors.toList());
		
		ProductResponse response = new ProductResponse();
		response.setContent(dtos);
		//remaining all information has page.
		response.setPageNumber(page.getNumber());
		response.setPageSize(page.getSize());
		response.setTotalElements(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		response.setLastPage(page.isLast());
		return response ;
		
	}



}
