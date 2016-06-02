package com.lulu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lulu.domain.Order;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {
	public List<Order> findByPaymentId(Integer id);
}
