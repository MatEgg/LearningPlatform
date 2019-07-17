package com.learningplatform.learningplatform.models.service;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.models.Model;
import com.learningplatform.learningplatform.models.Settings;
import com.learningplatform.learningplatform.models.dao.SettingsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service for receiving settings models.
 *
 */
@Service
public class SettingsService implements ModelService<Settings> {

	@Autowired
	SettingsDao settingsDao;

	@Override
	public Settings getRandomModel() {
		return null;
	}

	@Override
	public Model getMinimumValue(AttributeType attributeType) {
		return null;
	}

	@Override
	public Boolean save(Settings model) {
		return (settingsDao.save(model) != null);
	}

}
