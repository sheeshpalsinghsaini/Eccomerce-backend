package com.ecom.services;

import com.ecom.payload.CartDto;
import com.ecom.payload.ItemRequest;

public interface CartService {

	//add item to card
	//we will check the availability of cart, if cart is available then we will
	//add item to cart otherwise we will create new cart and add the item to it.
	
	CartDto addItem(ItemRequest item, String username);
	//get card of user
	CartDto get(String userName);
	//remove item from cart
//	CartDto removeItem(String userName,int itemId);
	CartDto removeItem(String userName,int productId);
}
