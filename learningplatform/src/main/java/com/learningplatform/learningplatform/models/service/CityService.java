package com.learningplatform.learningplatform.models.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.models.City;
import com.learningplatform.learningplatform.models.Model;
import com.learningplatform.learningplatform.models.dao.CityDao;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

/**
 * Service for receiving city models.
 *
 */
@Service
public class CityService implements ModelServiceRandomOnly<City> {

	@Autowired
	CityDao cityDao;

	@Override
	public City getRandomModel() {
		return cityDao.findByUid(UtilitiesHelper.getRandom().nextInt(cityDao.findAll().size()) + 1);
	}

	/**
	 * Retrieves a city based on the percentage position of it in a sorted City
	 * list.
	 * 
	 * @param percentage desired percentage of the city in the list
	 * @return City
	 */
	public City getCityByPercentage(float percentage) {
		List<City> sortedCities = cityDao.findByOrderByPopulationAsc();
		float cityCount = sortedCities.size();

		float storeRandomizer = UtilitiesHelper.getRandom().nextFloat();

		if (storeRandomizer < 0.25 && Math.round(percentage * (cityCount)) < sortedCities.size()) {
			return sortedCities.get(Math.round(percentage * (cityCount - 1)) + 1);

		} else if (storeRandomizer < 0.5 && Math.round(percentage * (cityCount)) != 0) {
			return sortedCities.get(Math.round(percentage * (cityCount - 1)) - 1);
		}

		return sortedCities.get(Math.round(percentage * (cityCount - 1)));
	}

	public List<City> getAllCities() {
		return cityDao.findAll();
	}

	public City getCityById(int id) {
		return cityDao.findByUid(id);
	}

}
