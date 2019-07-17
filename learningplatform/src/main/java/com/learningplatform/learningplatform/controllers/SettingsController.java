package com.learningplatform.learningplatform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learningplatform.learningplatform.guiObjects.DifficultyGui;
import com.learningplatform.learningplatform.guiObjects.difficulty.concept.ConceptDifficultyModelGui;
import com.learningplatform.learningplatform.guiObjects.difficulty.conceptual.ConceptualDifficultyModelGui;
import com.learningplatform.learningplatform.guiObjects.difficulty.operational.AdditionDifficultyGui;
import com.learningplatform.learningplatform.guiObjects.difficulty.operational.DivisionDifficultyGui;
import com.learningplatform.learningplatform.guiObjects.difficulty.operational.MultiplicationDifficultyGui;
import com.learningplatform.learningplatform.guiObjects.difficulty.operational.OperationalDifficultyGui;
import com.learningplatform.learningplatform.guiObjects.difficulty.operational.SubtractionDifficultyGui;
import com.learningplatform.learningplatform.models.Settings;
import com.learningplatform.learningplatform.models.difficulty.Difficulty;
import com.learningplatform.learningplatform.models.difficulty.concept.ConceptDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.conceptual.ConceptualDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.AdditionDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.DivisionDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.MultiplicationDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.OperationalDifficultyModel;
import com.learningplatform.learningplatform.models.difficulty.operational.SubtractionDifficultyModel;
import com.learningplatform.learningplatform.settings.SettingsGui;
import com.learningplatform.learningplatform.settings.SettingsHandler;

/**
 * Controller for everything regarding settings.
 *
 */
@RestController
public class SettingsController {

	@Autowired
	SettingsHandler sh;

	/**
	 * Target method to get the settings regarding a specific student.
	 * 
	 * @return SettingsGui the settings that will be displayed on the client
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/settingsGet")
	public SettingsGui settingsGet() {
		Settings currentSettings = sh.getCurrentSettings();
		Difficulty difficulty = currentSettings.getDifficulty();

		ConceptDifficultyModel conceptDifficultyModel = difficulty.getConceptDifficultyModel();
		ConceptualDifficultyModel conceptualDifficultyModel = difficulty.getConceptualDifficultyModel();
		OperationalDifficultyModel operationalDifficultyModel = difficulty.getOperationalDifficultyModel();

		ConceptDifficultyModelGui conceptDifficultyModelGui = new ConceptDifficultyModelGui(
				conceptDifficultyModel.getUid(), conceptDifficultyModel.getComputationalRulesDifficulty(),
				conceptDifficultyModel.getMeasurementDifficulty(), conceptDifficultyModel.getProbabilityDifficulty(),
				conceptDifficultyModel.getPercentDifficulty());

		ConceptualDifficultyModelGui conceptualDifficultyModelGui = new ConceptualDifficultyModelGui(
				conceptualDifficultyModel.getUid(), conceptualDifficultyModel.getReplaceDifficulty(),
				conceptualDifficultyModel.getConceptualDifficulty(),
				conceptualDifficultyModel.getDifferentConceptsDifficulty(),
				conceptualDifficultyModel.getQuestionDifficulty(), conceptualDifficultyModel.getDistractorsDifficulty(),
				conceptualDifficultyModel.getManual());

		AdditionDifficultyModel additionDifficultyModel = operationalDifficultyModel.getAdditionDifficultyModel();
		SubtractionDifficultyModel subtractionDifficultyModel = operationalDifficultyModel
				.getSubtractionDifficultyModel();
		MultiplicationDifficultyModel multiplicationDifficultyModel = operationalDifficultyModel
				.getMultiplicationDifficultyModel();
		DivisionDifficultyModel divisionDifficultyModel = operationalDifficultyModel.getDivisionDifficultyModel();

		AdditionDifficultyGui additionDifficultyGui = new AdditionDifficultyGui(additionDifficultyModel.getUid(),
				additionDifficultyModel.getOverallDifficulty(),
				additionDifficultyModel.getNumericalSkillTypesDifficultyModel(), additionDifficultyModel.getManual());
		SubtractionDifficultyGui subtractionDifficultyGui = new SubtractionDifficultyGui(
				subtractionDifficultyModel.getUid(), subtractionDifficultyModel.getOverallDifficulty(),
				subtractionDifficultyModel.getNumericalSkillTypesDifficultyModel(),
				subtractionDifficultyModel.getManual());
		MultiplicationDifficultyGui multiplicationDifficultyGui = new MultiplicationDifficultyGui(
				multiplicationDifficultyModel.getUid(), multiplicationDifficultyModel.getOverallDifficulty(),
				multiplicationDifficultyModel.getNumericalSkillTypesDifficultyModel(),
				multiplicationDifficultyModel.getManual());
		DivisionDifficultyGui divisionDifficultyGui = new DivisionDifficultyGui(divisionDifficultyModel.getUid(),
				divisionDifficultyModel.getOverallDifficulty(),
				divisionDifficultyModel.getNumericalSkillTypesDifficultyModel(), divisionDifficultyModel.getManual());

		OperationalDifficultyGui operationalDifficultyGui = new OperationalDifficultyGui(
				operationalDifficultyModel.getUid(), additionDifficultyGui, subtractionDifficultyGui,
				multiplicationDifficultyGui, divisionDifficultyGui);

		DifficultyGui difficultyGui = new DifficultyGui(difficulty.getUid(), conceptDifficultyModelGui,
				conceptualDifficultyModelGui, operationalDifficultyGui);

		SettingsGui settingsGui = new SettingsGui(currentSettings.getUid(), difficultyGui,
				currentSettings.getDecimalEnabled(), currentSettings.getMeasurementEnabled(),
				currentSettings.getPercentageEnabled(), currentSettings.getAutomaticEnabled(),
				currentSettings.getSensibility());
		return settingsGui;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/settingsSet")
	public Boolean settingsSet(@RequestBody Settings settings) {
		return sh.updateSettings(settings);
	}

}
