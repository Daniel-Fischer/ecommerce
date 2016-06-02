package com.lulu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lulu.domain.PaymentInfo;

@Repository
public interface PaymentInfoDAO extends JpaRepository<PaymentInfo, Integer> {
	public List<PaymentInfo> findByUserId(Integer u);
}
