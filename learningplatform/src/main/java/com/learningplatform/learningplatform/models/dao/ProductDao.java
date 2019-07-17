package com.learningplatform.learningplatform.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface ProductDao extends CrudRepository<Product, Integer> {

	public List<Product> findAll();

	public Product findByUid(Integer uid);

}
