package com.learningplatform.learningplatform.difficulty.numbermagnitudedifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyInt;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.DigitCountDifficultyCost;
import com.learningplatform.learningplatform.models.difficulty.NumberMagnitudeDifficultyModel;

public class NumberMagnitudeDifficulty extends DifficultyInt {

	private DigitCountDifficultyCost digitCountDifficulty;
	private NumberMagnitudeDifficultyModel numberMagnitudeDifficultyModel;
	boolean manual;

	public NumberMagnitudeDifficulty(NumberMagnitudeDifficultyModel numberMagnitudeDifficultyModel) {
		this.numberMagnitudeDifficultyModel = numberMagnitudeDifficultyModel;
		setDifficulty(numberMagnitudeDifficultyModel.getNumbermagnitudeDifficulty());

		if (numberMagnitudeDifficultyModel.getManual() == 0) {
			manual = false;
		} else {
			manual = true;
		}

		digitCountDifficulty = new DigitCountDifficultyCost(numberMagnitudeDifficultyModel.getDigitcountDifficulty());

		difficultyCosts.add(digitCountDifficulty);

		int totalUpgradeCost = getTotalLevelCosts();
		float difficultyInPercent = ((float) numberMagnitudeDifficultyModel.getNumbermagnitudeDifficulty()) / 100;
		float percentDifficulty = difficultyInPercent * (float) totalUpgradeCost;
		setDifficulty((int) percentDifficulty);
	}

	@Override
	public void determineDifficultySettings() {
		if (manual) {
			numberMagnitudeDifficultyModel.setNumbermagnitudeDifficulty(levelUpDifficultyIndividually());

		} else {
			levelUpDifficulty();
			numberMagnitudeDifficultyModel.setDigitcountDifficulty(digitCountDifficulty.getLevel());
		}
	}

	public DigitCountDifficultyCost getDigitCountDifficulty() {
		return digitCountDifficulty;
	}

	public void setDigitCountDifficulty(DigitCountDifficultyCost digitCountDifficulty) {
		this.digitCountDifficulty = digitCountDifficulty;
	}

}
