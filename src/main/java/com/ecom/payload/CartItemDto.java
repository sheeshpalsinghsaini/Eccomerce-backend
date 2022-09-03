

package com.ecom.payload;
import com.ecom.models.Product;


public class CartItemDto {

	private int cardItemId;
	
	private Product product;
	
	private int quantity;
	
	private double totalProductPrice;
	
	
	public int getCardItemId() {
		return cardItemId;
	}
	public void setCardItemId(int cardItemId) {
		this.cardItemId = cardItemId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalProductPrice() {
		return totalProductPrice;
	}
	public void setTotalProductPrice(double totalProductPrice) {
		this.totalProductPrice = totalProductPrice;
	}
	
	
	
	
	
}
