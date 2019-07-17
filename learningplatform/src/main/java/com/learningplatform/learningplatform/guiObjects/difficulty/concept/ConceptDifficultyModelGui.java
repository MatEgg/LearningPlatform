package com.learningplatform.learningplatform.guiObjects.difficulty.concept;

public class ConceptDifficultyModelGui {

	private int uid;
	private int computationalRulesDifficulty;
	private int measurementDifficulty;
	private int probabilityDifficulty;
	private int percentDifficulty;

	public ConceptDifficultyModelGui(int uid, int computationalRulesDifficulty, int measurementDifficulty,
			int probabilityDifficulty, int percentDifficulty) {

		this.uid = uid;
		this.computationalRulesDifficulty = computationalRulesDifficulty;
		this.measurementDifficulty = measurementDifficulty;
		this.probabilityDifficulty = probabilityDifficulty;
		this.percentDifficulty = percentDifficulty;

	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getComputationalRulesDifficulty() {
		return computationalRulesDifficulty;
	}

	public void setComputationalRulesDifficulty(int computationalRulesDifficulty) {
		this.computationalRulesDifficulty = computationalRulesDifficulty;
	}

	public int getMeasurementDifficulty() {
		return measurementDifficulty;
	}

	public void setMeasurementDifficulty(int measurementDifficulty) {
		this.measurementDifficulty = measurementDifficulty;
	}

	public int getProbabilityDifficulty() {
		return probabilityDifficulty;
	}

	public void setProbabilityDifficulty(int probabilityDifficulty) {
		this.probabilityDifficulty = probabilityDifficulty;
	}

	public int getPercentDifficulty() {
		return percentDifficulty;
	}

	public void setPercentDifficulty(int percentDifficulty) {
		this.percentDifficulty = percentDifficulty;
	}

}
