package com.learningplatform.learningplatform.settings;

import com.learningplatform.learningplatform.models.Settings;
import com.learningplatform.learningplatform.models.User;
import com.learningplatform.learningplatform.models.difficulty.Difficulty;
import com.learningplatform.learningplatform.models.service.DifficultyService;
import com.learningplatform.learningplatform.models.service.SettingsService;
import com.learningplatform.learningplatform.models.service.UserService;
import com.learningplatform.learningplatform.user.UserHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingsHandler {

	@Autowired
	UserService userService;

	@Autowired
	DifficultyService difficultyService;

	@Autowired
	SettingsService settingsService;

	/**
	 * returns the current settings.
	 * 
	 * @return Settings
	 */
	public Settings getCurrentSettings() {
		User user = userService.findByUsername(UserHandler.getInstance().getCurrentLoggedUser());
		Settings settings = user.getSettings();
		return settings;
	}

	/**
	 * Updates the settings.
	 * 
	 * @param settings current settings
	 * @return boolean if update was successful
	 */
	public Boolean updateSettings(Settings settings) {

		Difficulty difficulty = settings.getDifficulty();
		difficultyService.save(difficulty);
		return settingsService.save(settings);
	}

}
