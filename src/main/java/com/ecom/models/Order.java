package com.ecom.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int OrderId;
	@NotEmpty
	private String orderStatus;	//delivered or not
	private String paymentStatus;	//paid unpaid
	private Date orderCreated;
	private double totolAmount;
	private String billingAddress; //
	private Date orderDelivered;

	@OneToOne
	private User user;
	@OneToMany(mappedBy = "order")
	private Set<OrderItem> items = new HashSet<>();
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
