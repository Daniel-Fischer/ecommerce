/**
 * 
 */
package com.lulu.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lulu.domain.Ingredient;

/**
 * @author Daniel Fischer
 *
 */
@Repository
public interface IngredientDAO extends JpaRepository<Ingredient, Integer> {
	public List<Ingredient> findAll();
}
