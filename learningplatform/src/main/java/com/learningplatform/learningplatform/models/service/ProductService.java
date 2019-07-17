package com.learningplatform.learningplatform.models.service;


import com.learningplatform.learningplatform.models.Product;
import com.learningplatform.learningplatform.models.dao.ProductDao;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for receiving product models.
 *
 */
@Service
public class ProductService implements ModelServiceRandomOnly<Product> {

	@Autowired
	ProductDao productDao;

	public List<Product> getAllProducts() {
		return productDao.findAll();
	}

	public Product getProductByID(int id) {
		return productDao.findByUid(id);
	}

	@Override
	public Product getRandomModel() {
		return productDao.findByUid(UtilitiesHelper.getRandom().nextInt(productDao.findAll().size()) + 1);
	}
}
