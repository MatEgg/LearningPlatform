package com.learningplatform.learningplatform.models.service;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.models.Model;

/**
 * Service for receiving models.
 *
 */
public interface ModelService<T extends Model> extends ModelServiceAll {

	public T getRandomModel();

	Model getMinimumValue(AttributeType attributeType);

	public Boolean save(T model);

}
