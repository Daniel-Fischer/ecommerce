package com.lulu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lulu.domain.Order;
import com.lulu.domain.OrderType;

@Repository
public interface OrderTypeDAO extends JpaRepository<OrderType, Integer> {
	public OrderType findByType(String type);
}
