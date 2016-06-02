package com.lulu.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lulu.domain.ShippingInfo;

public interface ShippingInfoDAO extends JpaRepository<ShippingInfo, Integer> {
	public List<ShippingInfo> findByUserId(Integer id);
}