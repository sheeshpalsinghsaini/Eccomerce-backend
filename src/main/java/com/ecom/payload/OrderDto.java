package com.ecom.payload;

import java.util.Date;



public class OrderDto {

	private int OrderId;
	private String orderStatus;	//delivered or not
	private String paymentStatus;	//paid unpaid
	private Date orderCreated;
	private double totolAmount;
	private String billingAddress; //
	private Date orderDelivered;
	public OrderDto(int orderId, String orderStatus, String paymentStatus, Date orderCreated, double totolAmount,
			String billingAddress, Date orderDelivered) {
		super();
		OrderId = orderId;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
		this.orderCreated = orderCreated;
		this.totolAmount = totolAmount;
		this.billingAddress = billingAddress;
		this.orderDelivered = orderDelivered;
	}
	public OrderDto() {
		super();
	}
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Date getOrderCreated() {
		return orderCreated;
	}
	public void setOrderCreated(Date orderCreated) {
		this.orderCreated = orderCreated;
	}
	public double getTotolAmount() {
		return totolAmount;
	}
	public void setTotolAmount(double totolAmount) {
		this.totolAmount = totolAmount;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public Date getOrderDelivered() {
		return orderDelivered;
	}
	public void setOrderDelivered(Date orderDelivered) {
		this.orderDelivered = orderDelivered;
	}
	
	
}
