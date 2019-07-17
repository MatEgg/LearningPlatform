package com.learningplatform.learningplatform.models.service;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.difficulty.DifficultyCalculated;
import com.learningplatform.learningplatform.models.Field;
import com.learningplatform.learningplatform.models.Model;
import com.learningplatform.learningplatform.models.dao.FieldDao;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for receiving Field models.
 *
 */
@Service
public class FieldService implements ModelService<Field> {

	@Autowired
	FieldDao fieldDao;

	private final String ERROR_MESSAGE = "No field found.";

	public List<Field> getAllFields() {
		return fieldDao.findAll();
	}

	public Field getFieldByID(int id) {
		return fieldDao.findByUid(id);
	}

	@Override
	public Field getRandomModel() {
		return fieldDao.findByUid(UtilitiesHelper.getRandom().nextInt(fieldDao.findAll().size()) + 1);
	}

	private Optional<Field> getClosestFieldByArea(List<Field> fields, double value,
			boolean decimalEnabled) {
		List<Field> filteredFields = new ArrayList<Field>(fields);
		if (!decimalEnabled) {
			filteredFields = fields.stream().filter(i -> i.getArea() % 1 == 0).collect(Collectors.toList());
		}
		return filteredFields.stream().min(Comparator.comparingDouble(i -> Math.abs(i.getArea() - value)));
	}

	private Optional<Field> getClosestFieldByLength(List<Field> fields, double value,
			boolean decimalEnabled) {
		List<Field> filteredFields = new ArrayList<Field>(fields);
		if (!decimalEnabled) {
			filteredFields = fields.stream().filter(i -> i.getLength() % 1 == 0).collect(Collectors.toList());
		}
		return filteredFields.stream().min(Comparator.comparingDouble(i -> Math.abs(i.getLength() - value)));

	}

	private Optional<Field> getClosestFieldByWidth(List<Field> fields, double value,
			boolean decimalEnabled) {
		List<Field> filteredFields = new ArrayList<Field>(fields);
		if (!decimalEnabled) {
			filteredFields = fields.stream().filter(i -> i.getWidth() % 1 == 0).collect(Collectors.toList());
		}
		return filteredFields.stream().min(Comparator.comparingDouble(i -> Math.abs(i.getWidth() - value)));

	}

	private Optional<Field> getClosestFieldByVolume(List<Field> fields, double value,
			boolean decimalEnabled) {
		List<Field> filteredFields = new ArrayList<Field>(fields);
		if (!decimalEnabled) {
			filteredFields = fields.stream().filter(i -> i.getWidth() % 1 == 0).collect(Collectors.toList());
		}
		return  filteredFields.stream()
				.min(Comparator.comparingDouble(i -> Math.abs((i.getWidth() * i.getLength() * i.getHeight()) - value)));

	}

	/**
	 * Retrieves a field that is closest to the desired field value.
	 * 
	 * @param elementText          element text of the element that is given as a
	 *                             parameter
	 * @param attributeType        type of the attribute
	 * @param value                desired value that the new field should have
	 * @param difficultyCalculated difficulty settings
	 * @return new field with desired values
	 */
	public Optional<Field> getClosestFieldByParam(String elementText, AttributeType attributeType, double value,
			boolean decimalEnabled) {
		double newValue = value;
		if (!decimalEnabled && value < 1.0) {
			newValue = 1.0;
		}

		List<Field> fields = fieldDao.findAll().stream().filter(i -> !i.getName().equals(elementText))
				.collect(Collectors.toList());
		List<Field> filteredUsedFields = fields.stream().filter(i -> i.getUsed() == 0).collect(Collectors.toList());

		switch (attributeType) {
		case AREA:
			return getClosestFieldByArea(filteredUsedFields, newValue, decimalEnabled);
		case LENGTH:
			return getClosestFieldByLength(filteredUsedFields, newValue, decimalEnabled);
		case WIDTH:
			return getClosestFieldByWidth(filteredUsedFields, newValue, decimalEnabled);
		case VOLUME:
			return getClosestFieldByVolume(filteredUsedFields, newValue, decimalEnabled);
		default:
			return getClosestFieldByLength(filteredUsedFields, newValue, decimalEnabled);
		}
	}

	@Override
	public Model getMinimumValue(AttributeType attributeType) {

		switch (attributeType) {
		case AREA:
			return getMinimumValueArea();
		case LENGTH:
			return getMinimumValueLength();
		case WIDTH:
			return getMinimumValueWidth();
		case VOLUME:
			return getMinimumValueWidth();
		default:
			break;
		}
		return null;
	}

	/**
	 * Retrieves the area with minimum value
	 * 
	 * @return field with minimal area value
	 */
	public Model getMinimumValueArea() {
		Optional<Field> field = getAllFields().stream().min(Comparator.comparingDouble(i -> i.getArea()));
		if (field.isPresent()) {
			return field.get();
		} else {
			throw new NoSuchElementException(ERROR_MESSAGE);
		}
	}

	/**
	 * Retrieves the length with minimum value
	 * 
	 * @return field with minimal length value
	 */
	public Model getMinimumValueLength() {
		Optional<Field> field = getAllFields().stream().min(Comparator.comparingDouble(i -> i.getLength()));

		if (field.isPresent()) {
			return field.get();
		} else {
			throw new NoSuchElementException(ERROR_MESSAGE);
		}
	}

	/**
	 * Retrieves the width with minimum value
	 * 
	 * @return field with minimal width value
	 */
	public Model getMinimumValueWidth() {
		Optional<Field> field = getAllFields().stream().min(Comparator.comparingDouble(i -> i.getWidth()));

		if (field.isPresent()) {
			return field.get();
		} else {
			throw new NoSuchElementException(ERROR_MESSAGE);
		}
	}

	/**
	 * Retrieves the volume with minimum value
	 * 
	 * @return field with minimal volume value
	 */
	public Model getMinimumValueVolume() {
		Optional<Field> field = getAllFields().stream()
				.min(Comparator.comparingDouble(i -> i.getLength() * i.getWidth() * i.getHeight()));

		if (field.isPresent()) {
			return field.get();
		} else {
			throw new NoSuchElementException(ERROR_MESSAGE);
		}
	}

	@Override
	public Boolean save(Field model) {
		return fieldDao.save(model) != null;
	}

}
