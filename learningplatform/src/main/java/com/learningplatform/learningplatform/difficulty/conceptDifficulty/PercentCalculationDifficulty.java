package com.learningplatform.learningplatform.difficulty.conceptDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyInt;
import com.learningplatform.learningplatform.models.difficulty.concept.ConceptDifficultyModel;

public class PercentCalculationDifficulty extends DifficultyInt {

	private PercentCalculationDifficultyCost percentCalculationDifficultyCost;

	public PercentCalculationDifficulty(ConceptDifficultyModel conceptDifficultyModel) {
		percentCalculationDifficultyCost = new PercentCalculationDifficultyCost(
				conceptDifficultyModel.getPercentDifficulty());

		difficultyCosts.add(percentCalculationDifficultyCost);

		setDifficulty(conceptDifficultyModel.getPercentDifficulty());

		float difficultyInPercent = ((float) conceptDifficultyModel.getPercentDifficulty()) / 100;
		int normalizedValue = ((int) (difficultyInPercent * percentCalculationDifficultyCost
				.getTotalCost(percentCalculationDifficultyCost.getLevelCost().size())));
		percentCalculationDifficultyCost
				.setIndividualDifficultySettings(getLevelByCost(percentCalculationDifficultyCost, normalizedValue));
		difficultyCosts.add(percentCalculationDifficultyCost);

		int totalUpgradeCost = getTotalLevelCosts();
		float percentDifficulty = difficultyInPercent * (float) totalUpgradeCost;
		setDifficulty((int) percentDifficulty);

	}

	public PercentCalculationDifficulty() {
		percentCalculationDifficultyCost = new PercentCalculationDifficultyCost(0);
		difficultyCosts.add(percentCalculationDifficultyCost);
	}

	@Override
	public void determineDifficultySettings() {
		int newDifficulty = levelUpDifficultyIndividually();
		setDifficulty(newDifficulty);
	}

	public PercentCalculationDifficultyCost getPercentCalculationDifficultyCost() {
		return percentCalculationDifficultyCost;
	}

	public void setPercentCalculationDifficultyCost(PercentCalculationDifficultyCost percentCalculationDifficultyCost) {
		this.percentCalculationDifficultyCost = percentCalculationDifficultyCost;
	}

}
