package com.learningplatform.learningplatform.difficulty.conceptualDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyInt;
import com.learningplatform.learningplatform.models.difficulty.conceptual.ConceptualDifficultyModel;

public class ConceptualDifficulty extends DifficultyInt {

	private ReplaceDifficultyCost replaceDifficultyCost;
	private DifferentConceptsDifficultyCost differentConceptsDifficultyCost;
	private QuestionDifficultyDifficultyCost questionDifficultyDifficultyCost;
	private DistractorDifficultyCost distractorDifficultyCost;
	private ConceptualDifficultyModel conceptualDifficultyModel;
	boolean manual;

	public ConceptualDifficulty(ConceptualDifficultyModel conceptualDifficultyModel) {
		this.conceptualDifficultyModel = conceptualDifficultyModel;

		if (conceptualDifficultyModel.getManual() == 0) {
			manual = false;
		} else {
			manual = true;
		}

		replaceDifficultyCost = new ReplaceDifficultyCost(conceptualDifficultyModel.getReplaceDifficulty() / 10);
		differentConceptsDifficultyCost = new DifferentConceptsDifficultyCost(
				conceptualDifficultyModel.getDifferentConceptsDifficulty() / 10);
		questionDifficultyDifficultyCost = new QuestionDifficultyDifficultyCost(
				conceptualDifficultyModel.getQuestionDifficulty() / 10);
		distractorDifficultyCost = new DistractorDifficultyCost(
				conceptualDifficultyModel.getDistractorsDifficulty() / 10);

		difficultyCosts.add(replaceDifficultyCost);
		difficultyCosts.add(differentConceptsDifficultyCost);
		difficultyCosts.add(questionDifficultyDifficultyCost);
		difficultyCosts.add(distractorDifficultyCost);

		int totalUpgradeCost = getTotalLevelCosts();
		float difficultyInPercent = ((float) conceptualDifficultyModel.getConceptualDifficulty()) / 100;
		float percentDifficulty = difficultyInPercent * (float) totalUpgradeCost;
		setDifficulty((int) percentDifficulty);
	}

	public ConceptualDifficulty() {
		replaceDifficultyCost = new ReplaceDifficultyCost(0);
		differentConceptsDifficultyCost = new DifferentConceptsDifficultyCost(0);
		questionDifficultyDifficultyCost = new QuestionDifficultyDifficultyCost(0);
		distractorDifficultyCost = new DistractorDifficultyCost(0);

		difficultyCosts.add(replaceDifficultyCost);
		difficultyCosts.add(differentConceptsDifficultyCost);
		difficultyCosts.add(questionDifficultyDifficultyCost);
		difficultyCosts.add(distractorDifficultyCost);
	}

	@Override
	public void determineDifficultySettings() {
		if (manual) {
			int newDifficulty = levelUpDifficultyIndividually();
			setDifficulty(newDifficulty);
			conceptualDifficultyModel.setConceptualDifficulty(newDifficulty);
		} else {
			int newDifficulty = levelUpDifficulty();
			setDifficulty(newDifficulty);
			conceptualDifficultyModel.setReplaceDifficulty(replaceDifficultyCost.getLevel() * 10);
			conceptualDifficultyModel.setDifferentConceptsDifficulty(differentConceptsDifficultyCost.getLevel() * 10);
			conceptualDifficultyModel.setQuestionDifficulty(questionDifficultyDifficultyCost.getLevel() * 10);
			conceptualDifficultyModel.setDistractorsDifficulty(distractorDifficultyCost.getLevel() * 10);
		}
	}

	public ReplaceDifficultyCost getReplaceDifficultyCost() {
		return replaceDifficultyCost;
	}

	public void setReplaceDifficultyCost(ReplaceDifficultyCost replaceDifficultyCost) {
		this.replaceDifficultyCost = replaceDifficultyCost;
	}

	public DifferentConceptsDifficultyCost getDifferentConceptsDifficultyCost() {
		return differentConceptsDifficultyCost;
	}

	public void setDifferentConceptsDifficultyCost(DifferentConceptsDifficultyCost differentConceptsDifficultyCost) {
		this.differentConceptsDifficultyCost = differentConceptsDifficultyCost;
	}

	public QuestionDifficultyDifficultyCost getQuestionDifficultyDifficultyCost() {
		return questionDifficultyDifficultyCost;
	}

	public void setQuestionDifficultyDifficultyCost(QuestionDifficultyDifficultyCost questionDifficultyDifficultyCost) {
		this.questionDifficultyDifficultyCost = questionDifficultyDifficultyCost;
	}

	public DistractorDifficultyCost getDistractorDifficultyCost() {
		return distractorDifficultyCost;
	}

	public void setDistractorDifficultyCost(DistractorDifficultyCost distractorDifficultyCost) {
		this.distractorDifficultyCost = distractorDifficultyCost;
	}
}
