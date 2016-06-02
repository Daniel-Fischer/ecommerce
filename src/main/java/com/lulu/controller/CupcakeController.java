package com.lulu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lulu.domain.Ingredient;
import com.lulu.service.CupcakeService;

@RestController
public class CupcakeController {
	
	@Autowired
	private CupcakeService cupcakeService;
	
	@RequestMapping(value="options", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	public List<Ingredient> customizationOptions(){
		return cupcakeService.getAllCupcakeIngredients();
	}
}

