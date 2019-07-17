package com.learningplatform.learningplatform.difficulty.numericaldifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyInt;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.NumberTypeDifficultyCost;
import com.learningplatform.learningplatform.models.difficulty.NumericalDifficultyModel;

public class NumericalDifficulty extends DifficultyInt {

	private NumberTypeDifficultyCost numberTypeDifficulty;
	private NumericalDifficultyModel numericalDifficultyModel;
	boolean manual;

	public NumericalDifficulty(NumericalDifficultyModel numericalDifficultyModel) {
		this.numericalDifficultyModel = numericalDifficultyModel;

		if (numericalDifficultyModel.getManual() == 0) {
			manual = false;
		} else {
			manual = true;
		}

		numberTypeDifficulty = new NumberTypeDifficultyCost(numericalDifficultyModel.getNumbertypeDifficulty());

		difficultyCosts.add(numberTypeDifficulty);

		int totalUpgradeCost = getTotalLevelCosts();
		float difficultyInPercent = ((float) numericalDifficultyModel.getNumericalDifficulty()) / 100;
		float percentDifficulty = difficultyInPercent * (float) totalUpgradeCost;
		setDifficulty((int) percentDifficulty);
	}

	@Override
	public void determineDifficultySettings() {
		if (manual) {
			numericalDifficultyModel.setNumericalDifficulty(levelUpDifficultyIndividually());
		} else {
			levelUpDifficulty();
			numericalDifficultyModel.setNumbertypeDifficulty(numberTypeDifficulty.getLevel());
		}
	}

	public NumberTypeDifficultyCost getNumberTypeDifficulty() {
		return numberTypeDifficulty;
	}

	public void setNumberTypeDifficulty(NumberTypeDifficultyCost numberTypeDifficulty) {
		this.numberTypeDifficulty = numberTypeDifficulty;
	}

}
