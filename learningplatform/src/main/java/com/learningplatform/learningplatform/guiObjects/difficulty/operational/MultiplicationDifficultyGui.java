package com.learningplatform.learningplatform.guiObjects.difficulty.operational;

import com.learningplatform.learningplatform.models.difficulty.operational.NumericalSkillTypesDifficultyModel;

/**
 * Multiplication difficulty settings that will be displayed in the Gui.
 *
 */
public class MultiplicationDifficultyGui {

	private int uid;
	private int overallSkill;
	private NumericalSkillTypesDifficultyGui numericalSkillTypesDifficultyGui;
	private int manual;

	public MultiplicationDifficultyGui(int uid, int overallSkill,
			NumericalSkillTypesDifficultyModel numericalSkillTypesDifficultyModel, int manual) {
		this.uid = uid;
		this.overallSkill = overallSkill;
		this.numericalSkillTypesDifficultyGui = new NumericalSkillTypesDifficultyGui(
				numericalSkillTypesDifficultyModel);
		this.manual = manual;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getManual() {
		return manual;
	}

	public void setManual(int manual) {
		this.manual = manual;
	}

	public int getOverallSkill() {
		return overallSkill;
	}

	public void setOverallSkill(int overallSkill) {
		this.overallSkill = overallSkill;
	}

	public NumericalSkillTypesDifficultyGui getNumericalSkillTypesDifficultyGui() {
		return numericalSkillTypesDifficultyGui;
	}

	public void setNumericalSkillTypesDifficultyGui(NumericalSkillTypesDifficultyGui numericalSkillTypesDifficultyGui) {
		this.numericalSkillTypesDifficultyGui = numericalSkillTypesDifficultyGui;
	}

}
