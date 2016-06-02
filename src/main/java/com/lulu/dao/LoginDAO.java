package com.lulu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lulu.domain.User;

@Repository
public interface LoginDAO extends JpaRepository<User, Integer> {
	public User findByUsername(String username);

}
