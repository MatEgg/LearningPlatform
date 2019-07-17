package com.learningplatform.learningplatform.difficulty.operationalDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyInt;
import com.learningplatform.learningplatform.models.difficulty.operational.MultiplicationDifficultyModel;

public class MultiplicationDifficulty extends DifficultyInt {

	MultiplicationDifficultyModel multiplicationDifficultyModel;
	DecimalCountDifficultyCost decimalCountDifficultyCost;
	DigitCountDifficultyCost digitCountDifficultyCost;
	NumberTypeDifficultyCost numberTypeDifficultyCost;
	boolean manual;
	private int decimalEnabled;

	public MultiplicationDifficulty(MultiplicationDifficultyModel multiplicationDifficultyModel, int decimalEnabled) {
		this.decimalEnabled = decimalEnabled;
		this.multiplicationDifficultyModel = multiplicationDifficultyModel;

		if (multiplicationDifficultyModel.getManual() == 0) {
			manual = false;
		} else {
			manual = true;
		}

		digitCountDifficultyCost = new DigitCountDifficultyCost(
				multiplicationDifficultyModel.getNumericalSkillTypesDifficultyModel().getMagnitudeDifficulty() / 10);
		difficultyCosts.add(digitCountDifficultyCost);

		numberTypeDifficultyCost = new NumberTypeDifficultyCost(
				multiplicationDifficultyModel.getNumericalSkillTypesDifficultyModel().getNumericalDifficulty() / 10);
		difficultyCosts.add(numberTypeDifficultyCost);

		if (decimalEnabled == 1) {
			decimalCountDifficultyCost = new DecimalCountDifficultyCost(
					multiplicationDifficultyModel.getNumericalSkillTypesDifficultyModel().getDecimalDifficulty() / 10);
			difficultyCosts.add(decimalCountDifficultyCost);
		}

		int totalUpgradeCost = getTotalLevelCosts();
		float difficultyInPercent = ((float) multiplicationDifficultyModel.getOverallDifficulty()) / 100;
		float percentDifficulty = difficultyInPercent * (float) totalUpgradeCost;
		setDifficulty((int) percentDifficulty);
	}

	public MultiplicationDifficulty() {
		this.digitCountDifficultyCost = new DigitCountDifficultyCost(0);
		this.decimalCountDifficultyCost = new DecimalCountDifficultyCost(0);
		this.numberTypeDifficultyCost = new NumberTypeDifficultyCost(0);
		difficultyCosts.add(digitCountDifficultyCost);
		difficultyCosts.add(decimalCountDifficultyCost);
		difficultyCosts.add(numberTypeDifficultyCost);
	}

	@Override
	public void determineDifficultySettings() {
		if (manual) {
			int newDifficulty = levelUpDifficultyIndividually();
			multiplicationDifficultyModel.setOverallDifficulty(newDifficulty);
			setDifficulty(newDifficulty);
		} else {
			int newDifficulty = levelUpDifficulty();
			setDifficulty(newDifficulty);
			if (decimalEnabled == 1) {
				multiplicationDifficultyModel.getNumericalSkillTypesDifficultyModel()
						.setDecimalDifficulty(decimalCountDifficultyCost.getLevel() * 10);
			}

			multiplicationDifficultyModel.getNumericalSkillTypesDifficultyModel()
					.setMagnitudeDifficulty(digitCountDifficultyCost.getLevel() * 10);
			multiplicationDifficultyModel.getNumericalSkillTypesDifficultyModel()
					.setNumericalDifficulty(numberTypeDifficultyCost.getLevel() * 10);
		}

	}

	public DecimalCountDifficultyCost getDecimalCountDifficultyCost() {
		return decimalCountDifficultyCost;
	}

	public void setDecimalCountDifficultyCost(DecimalCountDifficultyCost decimalCountDifficultyCost) {
		this.decimalCountDifficultyCost = decimalCountDifficultyCost;
	}

	public DigitCountDifficultyCost getDigitCountDifficultyCost() {
		return digitCountDifficultyCost;
	}

	public void setDigitCountDifficultyCost(DigitCountDifficultyCost digitCountDifficultyCost) {
		this.digitCountDifficultyCost = digitCountDifficultyCost;
	}

	public NumberTypeDifficultyCost getNumberTypeDifficultyCost() {
		return numberTypeDifficultyCost;
	}

	public void setNumberTypeDifficultyCost(NumberTypeDifficultyCost numberTypeDifficultyCost) {
		this.numberTypeDifficultyCost = numberTypeDifficultyCost;
	}

}
