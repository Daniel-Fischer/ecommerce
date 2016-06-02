package com.lulu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lulu.dao.IngredientDAO;
import com.lulu.domain.Ingredient;

@Service
public class CupcakeService {
	
	@Autowired
	private IngredientDAO ingredientDao;
	
	public List<Ingredient> getAllCupcakeIngredients(){
		return ingredientDao.findAll();
	}
}
