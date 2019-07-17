package com.learningplatform.learningplatform.models.service;

import com.learningplatform.learningplatform.models.Model;

/**
 * Service for receiving save only models.
 *
 */
public interface ModelServiceRandomOnly<T extends Model> extends ModelServiceAll {
	
	public T getRandomModel();
	
}
