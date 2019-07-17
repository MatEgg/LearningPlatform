package com.learningplatform.learningplatform.models.service;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.models.Animal;
import com.learningplatform.learningplatform.models.Model;
import com.learningplatform.learningplatform.models.dao.AnimalDao;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for receiving animal models.
 *
 */
@Service
public class AnimalService implements ModelServiceRandomOnly<Animal> {

	@Autowired
	AnimalDao animalDao;

	public List<Animal> getAllAnimals() {
		return animalDao.findAll();
	}

	public Animal getAnimalByID(int id) {
		return animalDao.findByUid(id);
	}

	/**
	 * Retrieves an animal that has a food requirement that is close to the required
	 * one.
	 * 
	 * @param value desired food requirement
	 * @return Animal
	 */
	public Animal getClosestAnimalByFoodReq(double value) {
		Optional<Animal> animal = getAllAnimals().stream()
				.min(Comparator.comparingDouble(i -> Math.abs(i.getFoodReq() - value)));

		if (animal.isPresent()) {
			return animal.get();
		} else {
			throw new NoSuchElementException("No Animal found");
		}
	}

	@Override
	public Animal getRandomModel() {
		return animalDao.findByUid(UtilitiesHelper.getRandom().nextInt(animalDao.findAll().size()) + 1);
	}
}
