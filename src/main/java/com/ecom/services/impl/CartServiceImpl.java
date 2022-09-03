package com.ecom.services.impl;

import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.ResourceNotFoundException;
import com.ecom.models.Cart;
import com.ecom.models.CartItems;
import com.ecom.models.Product;
import com.ecom.models.User;
import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;
import com.ecom.repositries.CartRepository;
import com.ecom.repositries.ProductRepository;
import com.ecom.repositries.UserRepository;
import com.ecom.services.CartService;


@Service
public class CartServiceImpl implements CartService {
 
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ProductRepository productRepository;
	
	
	//addItems in cart
	
	@Override
	public CartDto addItem(ItemRequest item, String username) {
		int productId = item.getProductId();	//we can fetch product by id
		int quantity = item.getQuantity();
		
		if(quantity<=0) {
			throw new ResourceNotFoundException("Invalid Quantity");
		}
		
		//get the user
		User user = this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFoundException());
		
		//get the product from db: need productRepository
		Product product = this.productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product not found"));
		if(!product.isStock()) {
			throw new ResourceNotFoundException("Product is out of Stock!!!");
		}
		System.out.println(product.getProductPrice());
		//create new cartItem : with product and quantity
		CartItems cartItems = new CartItems();
		cartItems.setProduct(product);
		cartItems.setQuantity(quantity);
		cartItems.setTotalProductPrice();
		
		//getting cart form user
		Cart cart = user.getCart();
		
		//if cart is null that means does not have any cart
		if(cart==null) {
			//we will create new cart
			cart = new Cart();
		}
		cart.setUser(user);
		
		
		Set<CartItems> items = cart.getItems();
		
		
		
		
		AtomicReference<Boolean> flag = new AtomicReference<>(false);
		
		Set<CartItems> newItems = items.stream().map((i)->{
			//changes
			if(i.getProduct().getProductId()==product.getProductId())
			{
				i.setQuantity(quantity);
				i.setTotalProductPrice();
				flag.set(true);
			}
			return i;
		}).collect(Collectors.toSet());
		
		//check before add item that its already exist or not.
		if(flag.get()) {
			items.clear();
			items.addAll(newItems);
		}else {
			//add items in cart.
			cartItems.setCart(cart);
			items.add(cartItems);
		}
		
		
		
		
		Cart updatedCart = this.cartRepository.save(cart);
		return this.mapper.map(updatedCart, CartDto.class);
	}

	
	//get cart from user
	@Override
	public CartDto get(String userName) {
		User user = this.userRepository.findByEmail(userName).
				orElseThrow(()-> new ResourceNotFoundException("User not found withgive username!!!"));
		Cart cart = this.cartRepository.findByUser(user).orElseThrow(()-> new ResourceNotFoundException("Cart not found"));	
		return this.mapper.map(cart, CartDto.class);
	}
	
	
	//remove item from cart.

	@Override
	public CartDto removeItem(String userName, int productId) {
		User user = this.userRepository.findByEmail(userName).
				orElseThrow(()-> new ResourceNotFoundException("User not found withgive username!!!"));
		Cart cart = user.getCart();
		Set<CartItems> items = cart.getItems();
		
		boolean result = items.removeIf((item)-> item.getProduct().getProductId()==productId);
		System.out.println(result);
		Cart updatedCart = cartRepository.save(cart);
		
		
		return this.mapper.map(updatedCart, CartDto.class);
	}

}
