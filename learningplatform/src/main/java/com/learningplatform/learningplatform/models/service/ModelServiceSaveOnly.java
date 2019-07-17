package com.learningplatform.learningplatform.models.service;

import com.learningplatform.learningplatform.models.Model;

/**
 * Service for receiving save only models.
 *
 */
public interface ModelServiceSaveOnly<T extends Model> extends ModelServiceAll {
	
	public Boolean save(T model);
	
}
