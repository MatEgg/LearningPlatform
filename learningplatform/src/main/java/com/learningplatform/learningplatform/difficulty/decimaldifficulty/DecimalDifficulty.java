package com.learningplatform.learningplatform.difficulty.decimaldifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyInt;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.DecimalCountDifficultyCost;
import com.learningplatform.learningplatform.models.difficulty.DecimalDifficultyModel;

public class DecimalDifficulty extends DifficultyInt {

	private DecimalCountDifficultyCost decimalCountCost;
	private DecimalEnableCost decimalEnableCost;
	private DecimalDifficultyModel decimalDifficultyModel;
	boolean manual;

	public DecimalDifficulty(DecimalDifficultyModel decimalDifficultyModel) {
		this.decimalDifficultyModel = decimalDifficultyModel;

		if (decimalDifficultyModel.getManual() == 0) {
			manual = false;
		} else {
			manual = true;
		}

		decimalCountCost = new DecimalCountDifficultyCost(decimalDifficultyModel.getDecimalcountDifficulty());
		decimalEnableCost = new DecimalEnableCost(decimalDifficultyModel.getDecimalenableDifficulty());

		difficultyCosts.add(decimalCountCost);
		difficultyCosts.add(decimalEnableCost);

		int totalUpgradeCost = getTotalLevelCosts();
		float difficultyInPercent = ((float) decimalDifficultyModel.getDecimalDifficulty()) / 100;
		float percentDifficulty = difficultyInPercent * (float) totalUpgradeCost;
		setDifficulty((int) percentDifficulty);

	}

	@Override
	public void determineDifficultySettings() {
		if (manual) {
			decimalDifficultyModel.setDecimalDifficulty(levelUpDifficultyIndividually());
		} else {
			levelUpDifficulty();
			decimalDifficultyModel.setDecimalcountDifficulty(decimalCountCost.getLevel());
			decimalDifficultyModel.setDecimalenableDifficulty(decimalEnableCost.getLevel());
		}
	}

	public DecimalCountDifficultyCost getDecimalCountCost() {
		return decimalCountCost;
	}

	public void setDecimalCountCost(DecimalCountDifficultyCost decimalCountCost) {
		this.decimalCountCost = decimalCountCost;
	}

	public DecimalEnableCost getDecimalEnableCost() {
		return decimalEnableCost;
	}

	public void setDecimalEnableCost(DecimalEnableCost decimalEnableCost) {
		this.decimalEnableCost = decimalEnableCost;
	}
}
