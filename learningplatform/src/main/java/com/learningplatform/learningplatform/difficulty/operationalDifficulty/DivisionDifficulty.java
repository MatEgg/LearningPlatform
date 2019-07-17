package com.learningplatform.learningplatform.difficulty.operationalDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyInt;
import com.learningplatform.learningplatform.models.difficulty.operational.DivisionDifficultyModel;

public class DivisionDifficulty extends DifficultyInt {

	DivisionDifficultyModel divisionDifficultyModel;
	DecimalCountDifficultyCost decimalCountDifficultyCost;
	DigitCountDifficultyCost digitCountDifficultyCost;
	NumberTypeDifficultyCost numberTypeDifficultyCost;
	boolean manual;
	private int decimalEnabled;

	public DivisionDifficulty(DivisionDifficultyModel divisionDifficultyModel, int decimalEnabled) {
		this.divisionDifficultyModel = divisionDifficultyModel;
		this.decimalEnabled = decimalEnabled;

		if (divisionDifficultyModel.getManual() == 0) {
			manual = false;
		} else {
			manual = true;
		}

		digitCountDifficultyCost = new DigitCountDifficultyCost(
				divisionDifficultyModel.getNumericalSkillTypesDifficultyModel().getMagnitudeDifficulty() / 10);
		difficultyCosts.add(digitCountDifficultyCost);

		numberTypeDifficultyCost = new NumberTypeDifficultyCost(
				divisionDifficultyModel.getNumericalSkillTypesDifficultyModel().getNumericalDifficulty() / 10);
		difficultyCosts.add(numberTypeDifficultyCost);

		if (decimalEnabled == 1) {
			decimalCountDifficultyCost = new DecimalCountDifficultyCost(
					divisionDifficultyModel.getNumericalSkillTypesDifficultyModel().getDecimalDifficulty() / 10);
			difficultyCosts.add(decimalCountDifficultyCost);
		}

		int totalUpgradeCost = getTotalLevelCosts();
		float difficultyInPercent = ((float) divisionDifficultyModel.getOverallDifficulty()) / 100;
		float percentDifficulty = difficultyInPercent * (float) totalUpgradeCost;
		setDifficulty((int) percentDifficulty);
	}

	public DivisionDifficulty() {
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
			divisionDifficultyModel.setOverallDifficulty(newDifficulty);
			setDifficulty(newDifficulty);

		} else {

			int newDifficulty = levelUpDifficulty();
			setDifficulty(newDifficulty);

			if (decimalEnabled == 1) {
				divisionDifficultyModel.getNumericalSkillTypesDifficultyModel()
						.setDecimalDifficulty(decimalCountDifficultyCost.getLevel() * 10);
			}

			divisionDifficultyModel.getNumericalSkillTypesDifficultyModel()
					.setMagnitudeDifficulty(digitCountDifficultyCost.getLevel() * 10);
			divisionDifficultyModel.getNumericalSkillTypesDifficultyModel()
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
