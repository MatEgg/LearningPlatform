package com.learningplatform.learningplatform.difficulty.conceptDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyInt;
import com.learningplatform.learningplatform.models.difficulty.concept.ConceptDifficultyModel;

public class ComputationalRulesDifficulty extends DifficultyInt {

	private ComputationalRulesDifficultyCost computationalRulesDifficultyCost;

	public ComputationalRulesDifficulty(ConceptDifficultyModel conceptDifficultyModel) {

		computationalRulesDifficultyCost = new ComputationalRulesDifficultyCost(
				conceptDifficultyModel.getComputationalRulesDifficulty());

		float difficultyInPercent = ((float) conceptDifficultyModel.getComputationalRulesDifficulty()) / 100;
		int normalizedValue = ((int) (difficultyInPercent * computationalRulesDifficultyCost
				.getTotalCost(computationalRulesDifficultyCost.getLevelCost().size())));
		computationalRulesDifficultyCost
				.setIndividualDifficultySettings(getLevelByCost(computationalRulesDifficultyCost, normalizedValue));
		difficultyCosts.add(computationalRulesDifficultyCost);

		int totalUpgradeCost = getTotalLevelCosts();
		float percentDifficulty = difficultyInPercent * (float) totalUpgradeCost;
		setDifficulty((int) percentDifficulty);

	}

	public ComputationalRulesDifficulty() {
		computationalRulesDifficultyCost = new ComputationalRulesDifficultyCost(0);
		difficultyCosts.add(computationalRulesDifficultyCost);
	}

	@Override
	public void determineDifficultySettings() {
		int newDifficulty = levelUpDifficultyIndividually();
		setDifficulty(newDifficulty);
	}

	public ComputationalRulesDifficultyCost getComputationalRulesDifficultyCost() {
		return computationalRulesDifficultyCost;
	}

	public void setComputationalRulesDifficultyCost(ComputationalRulesDifficultyCost computationalRulesDifficultyCost) {
		this.computationalRulesDifficultyCost = computationalRulesDifficultyCost;
	}

}
