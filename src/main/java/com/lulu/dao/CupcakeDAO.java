package com.lulu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lulu.domain.Cupcake;

@Repository
public interface CupcakeDAO extends JpaRepository<Cupcake, Integer> {
	List<Cupcake> findByOrderId(Integer id);
}
