package com.learningplatform.learningplatform.difficulty.operationalDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyInt;
import com.learningplatform.learningplatform.models.difficulty.operational.SubtractionDifficultyModel;

public class SubtractionDifficulty extends DifficultyInt {

	SubtractionDifficultyModel subtractionDifficultyModel;
	DecimalCountDifficultyCost decimalCountDifficultyCost;
	DigitCountDifficultyCost digitCountDifficultyCost;
	NumberTypeDifficultyCost numberTypeDifficultyCost;
	boolean manual;
	private int decimalEnabled;

	public SubtractionDifficulty(SubtractionDifficultyModel subtractionDifficultyModel, int decimalEnabled) {
		this.subtractionDifficultyModel = subtractionDifficultyModel;
		this.decimalEnabled = decimalEnabled;

		if (subtractionDifficultyModel.getManual() == 0) {
			manual = false;
		} else {
			manual = true;
		}

		digitCountDifficultyCost = new DigitCountDifficultyCost(
				subtractionDifficultyModel.getNumericalSkillTypesDifficultyModel().getMagnitudeDifficulty() / 10);
		difficultyCosts.add(digitCountDifficultyCost);

		numberTypeDifficultyCost = new NumberTypeDifficultyCost(
				subtractionDifficultyModel.getNumericalSkillTypesDifficultyModel().getNumericalDifficulty() / 10);
		difficultyCosts.add(numberTypeDifficultyCost);

		if (decimalEnabled == 1) {
			decimalCountDifficultyCost = new DecimalCountDifficultyCost(
					subtractionDifficultyModel.getNumericalSkillTypesDifficultyModel().getDecimalDifficulty() / 10);
			difficultyCosts.add(decimalCountDifficultyCost);
		}

		int totalUpgradeCost = getTotalLevelCosts();
		float difficultyInPercent = ((float) subtractionDifficultyModel.getOverallDifficulty()) / 100;
		float percentDifficulty = difficultyInPercent * (float) totalUpgradeCost;
		setDifficulty((int) percentDifficulty);

	}

	public SubtractionDifficulty() {
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
			subtractionDifficultyModel.setOverallDifficulty(newDifficulty);
			setDifficulty(newDifficulty);
		} else {
			int newDifficulty = levelUpDifficulty();
			setDifficulty(newDifficulty);

			if (decimalEnabled == 1) {
				subtractionDifficultyModel.getNumericalSkillTypesDifficultyModel()
						.setDecimalDifficulty(decimalCountDifficultyCost.getLevel() * 10);
			}

			subtractionDifficultyModel.getNumericalSkillTypesDifficultyModel()
					.setMagnitudeDifficulty(digitCountDifficultyCost.getLevel() * 10);
			subtractionDifficultyModel.getNumericalSkillTypesDifficultyModel()
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
