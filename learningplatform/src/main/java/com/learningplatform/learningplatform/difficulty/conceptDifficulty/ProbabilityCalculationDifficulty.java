package com.learningplatform.learningplatform.difficulty.conceptDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyInt;
import com.learningplatform.learningplatform.models.difficulty.concept.ConceptDifficultyModel;

public class ProbabilityCalculationDifficulty extends DifficultyInt {

	private ProbabilityCalculationDifficultyCost probabilityCalculationDifficultyCost;

	public ProbabilityCalculationDifficulty(ConceptDifficultyModel conceptDifficultyModel) {
		probabilityCalculationDifficultyCost = new ProbabilityCalculationDifficultyCost(
				conceptDifficultyModel.getProbabilityDifficulty());

		difficultyCosts.add(probabilityCalculationDifficultyCost);

		float difficultyInPercent = ((float) conceptDifficultyModel.getProbabilityDifficulty()) / 100;
		int normalizedValue = ((int) (difficultyInPercent * probabilityCalculationDifficultyCost
				.getTotalCost(probabilityCalculationDifficultyCost.getLevelCost().size())));
		probabilityCalculationDifficultyCost
				.setIndividualDifficultySettings(getLevelByCost(probabilityCalculationDifficultyCost, normalizedValue));
		difficultyCosts.add(probabilityCalculationDifficultyCost);

		int totalUpgradeCost = getTotalLevelCosts();
		float percentDifficulty = difficultyInPercent * (float) totalUpgradeCost;
		setDifficulty((int) percentDifficulty);
	}

	public ProbabilityCalculationDifficulty() {
		probabilityCalculationDifficultyCost = new ProbabilityCalculationDifficultyCost(0);
		difficultyCosts.add(probabilityCalculationDifficultyCost);
	}

	@Override
	public void determineDifficultySettings() {
		int newDifficulty = levelUpDifficultyIndividually();
		setDifficulty(newDifficulty);
	}

	public ProbabilityCalculationDifficultyCost getProbabilityCalculationDifficultyCost() {
		return probabilityCalculationDifficultyCost;
	}

	public void setProbabilityCalculationDifficultyCost(
			ProbabilityCalculationDifficultyCost probabilityCalculationDifficultyCost) {
		this.probabilityCalculationDifficultyCost = probabilityCalculationDifficultyCost;
	}

}
