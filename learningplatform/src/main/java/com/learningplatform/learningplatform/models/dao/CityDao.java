package com.learningplatform.learningplatform.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.learningplatform.learningplatform.models.City;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface CityDao extends CrudRepository<City, Integer> {
	
	public List<City> findAll();
	
	public List<City> findByOrderByPopulationAsc();

	public City findByUid(Integer uid);
}
