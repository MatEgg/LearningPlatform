package com.learningplatform.learningplatform.controllers;

import com.learningplatform.learningplatform.models.service.AnimalService;
import com.learningplatform.learningplatform.models.service.CityService;
import com.learningplatform.learningplatform.models.service.DifficultyService;
import com.learningplatform.learningplatform.models.service.FieldService;
import com.learningplatform.learningplatform.models.service.ProductService;
import com.learningplatform.learningplatform.models.service.StoreService;
import com.learningplatform.learningplatform.models.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DaoFactory {

	@Autowired
	AnimalService animalService;

	@Autowired
	ProductService productService;

	@Autowired
	FieldService fieldService;

	@Autowired
	StoreService storeService;

	@Autowired
	CityService cityService;

	@Autowired
	UserService userService;

	@Autowired
	DifficultyService difficultyService;

	public AnimalService getAnimalService() {
		return animalService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public FieldService getFieldService() {
		return fieldService;
	}

	public StoreService getStoreService() {
		return storeService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public DifficultyService getDifficultyService() {
		return difficultyService;
	}

	public void setDifficultyService(DifficultyService difficultyService) {
		this.difficultyService = difficultyService;
	}

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

}
