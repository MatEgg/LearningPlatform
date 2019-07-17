package com.learningplatform.learningplatform.guiObjects.difficulty.operational;

import com.learningplatform.learningplatform.models.difficulty.operational.NumericalSkillTypesDifficultyModel;

/**
 * Information regarding the operational difficulty settings in detail.
 *
 */
public class NumericalSkillTypesDifficultyGui {

	private int uid;
	private int decimalDifficulty;
	private int magnitudeDifficulty;
	private int numericalDifficulty;

	public NumericalSkillTypesDifficultyGui(
			NumericalSkillTypesDifficultyModel numericalSkillTypesDifficultyModel) {
		this.uid = numericalSkillTypesDifficultyModel.getUid();
		this.decimalDifficulty = numericalSkillTypesDifficultyModel.getDecimalDifficulty();
		this.magnitudeDifficulty = numericalSkillTypesDifficultyModel.getMagnitudeDifficulty();
		this.numericalDifficulty = numericalSkillTypesDifficultyModel.getNumericalDifficulty();
	}


	public int getUid() {
		return uid;
	}


	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public int getDecimalDifficulty() {
		return decimalDifficulty;
	}

	public void setDecimalDifficulty(int decimalDifficulty) {
		this.decimalDifficulty = decimalDifficulty;
	}

	public int getMagnitudeDifficulty() {
		return magnitudeDifficulty;
	}

	public void setMagnitudeDifficulty(int magnitudeDifficulty) {
		this.magnitudeDifficulty = magnitudeDifficulty;
	}

	public int getNumericalDifficulty() {
		return numericalDifficulty;
	}

	public void setNumericalDifficulty(int numericalDifficulty) {
		this.numericalDifficulty = numericalDifficulty;
	}
}
