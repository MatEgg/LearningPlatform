package com.learningplatform.learningplatform.difficulty.conceptDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyInt;
import com.learningplatform.learningplatform.models.difficulty.concept.ConceptDifficultyModel;

public class MeasurementDifficulty extends DifficultyInt {

	private MeasurementDifficultyCost measurementDifficultyCost;

	public MeasurementDifficulty(ConceptDifficultyModel conceptDifficultyModel) {
		measurementDifficultyCost = new MeasurementDifficultyCost(
				conceptDifficultyModel.getMeasurementDifficulty());

		difficultyCosts.add(measurementDifficultyCost);

		float difficultyInPercent = ((float) conceptDifficultyModel.getMeasurementDifficulty()) / 100;
		int normalizedValue = ((int) (difficultyInPercent
				* measurementDifficultyCost.getTotalCost(measurementDifficultyCost.getLevelCost().size())));
		measurementDifficultyCost
				.setIndividualDifficultySettings(getLevelByCost(measurementDifficultyCost, normalizedValue));
		difficultyCosts.add(measurementDifficultyCost);

		int totalUpgradeCost = getTotalLevelCosts();
		float percentDifficulty = difficultyInPercent * (float) totalUpgradeCost;
		setDifficulty((int) percentDifficulty);
	}
	
	public MeasurementDifficulty() {
		measurementDifficultyCost = new MeasurementDifficultyCost(0);
		difficultyCosts.add(measurementDifficultyCost);
	}

	@Override
	public void determineDifficultySettings() {
		int newDifficulty = levelUpDifficultyIndividually();
		setDifficulty(newDifficulty);
	}

	public MeasurementDifficultyCost getMeasurementDifficultyCost() {
		return measurementDifficultyCost;
	}

	public void setMeasurementDifficultyCost(MeasurementDifficultyCost measurementDifficultyCost) {
		this.measurementDifficultyCost = measurementDifficultyCost;
	}

}
