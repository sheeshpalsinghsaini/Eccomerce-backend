package com.ecom.repositries;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
