package com.learningplatform.learningplatform.models.service;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.models.Model;
import com.learningplatform.learningplatform.models.Store;
import com.learningplatform.learningplatform.models.dao.StoreDao;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for receiving Store models.
 *
 */
@Service
public class StoreService implements ModelServiceRandomOnly<Store> {

	@Autowired
	StoreDao storeDao;

	public List<Store> getAllStores() {
		return storeDao.findAll();
	}

	public Store gestStoreById(int id) {
		return storeDao.findByUid(id);
	}

	/**
	 * Retrieves a Store based on the percentage position of it in a sorted Store
	 * list.
	 * 
	 * @param percentage desired percentage of the Store in the list
	 * @return Store
	 */
	public Store getStoreAreaByPercentage(float percentage) {
		List<Store> sortedStores = storeDao.findByOrderByAreaAsc();
		float storeCount = sortedStores.size();
		float storeRandomizer = UtilitiesHelper.getRandom().nextFloat();

		// Randomizer to not always get the same store
		if (storeRandomizer < 0.25 && Math.round(percentage * (storeCount)) < sortedStores.size()) {
			return sortedStores.get(Math.round(percentage * (storeCount - 1)) + 1);

		} else if (storeRandomizer < 0.5 && Math.round(percentage * (storeCount)) != 0) {
			return sortedStores.get(Math.round(percentage * (storeCount - 1)) - 1);
		}

		return sortedStores.get(Math.round(percentage * (storeCount-1)));

	}

	@Override
	public Store getRandomModel() {
		return storeDao.findByUid(UtilitiesHelper.getRandom().nextInt(storeDao.findAll().size()) + 1);
	}
}
