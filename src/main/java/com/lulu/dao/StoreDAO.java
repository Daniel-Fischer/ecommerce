package com.lulu.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lulu.domain.Store;

@Repository
public interface StoreDAO extends JpaRepository<Store, Integer>{
	public Store findById(Integer id);
}
