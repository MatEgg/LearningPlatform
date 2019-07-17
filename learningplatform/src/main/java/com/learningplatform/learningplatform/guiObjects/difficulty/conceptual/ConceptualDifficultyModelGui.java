package com.learningplatform.learningplatform.guiObjects.difficulty.conceptual;

/**
 * Conceptual model which will be displayed in the Gui.
 *
 */
public class ConceptualDifficultyModelGui {

	private int uid;
	private int replaceDifficulty;
	private int conceptualDifficulty;
	private int differentConceptsDifficulty;
	private int questionDifficulty;
	private int distractorsDifficulty;
	private int manual;

	public ConceptualDifficultyModelGui(int uid, int replaceDifficulty, int conceptualDifficulty,
			int differentConceptsDifficulty, int questionDifficulty, int distractorsDifficulty, int manual) {
		this.uid = uid;
		this.replaceDifficulty = replaceDifficulty;
		this.conceptualDifficulty = conceptualDifficulty;
		this.differentConceptsDifficulty = differentConceptsDifficulty;
		this.questionDifficulty = questionDifficulty;
		this.distractorsDifficulty = distractorsDifficulty;
		this.manual = manual;
	}

	public int getReplaceDifficulty() {
		return replaceDifficulty;
	}

	public void setReplaceDifficulty(int replaceDifficulty) {
		this.replaceDifficulty = replaceDifficulty;
	}

	public int getConceptualDifficulty() {
		return conceptualDifficulty;
	}

	public void setConceptualDifficulty(int conceptualDifficulty) {
		this.conceptualDifficulty = conceptualDifficulty;
	}

	public int getDifferentConceptsDifficulty() {
		return differentConceptsDifficulty;
	}

	public void setDifferentConceptsDifficulty(int differentConceptsDifficulty) {
		this.differentConceptsDifficulty = differentConceptsDifficulty;
	}

	public int getQuestionDifficulty() {
		return questionDifficulty;
	}

	public void setQuestionDifficulty(int questionDifficulty) {
		this.questionDifficulty = questionDifficulty;
	}

	public int getDistractorsDifficulty() {
		return distractorsDifficulty;
	}

	public void setDistractorsDifficulty(int distractorsDifficulty) {
		this.distractorsDifficulty = distractorsDifficulty;
	}

	public int getManual() {
		return manual;
	}

	public void setManual(int manual) {
		this.manual = manual;
	}
	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}
