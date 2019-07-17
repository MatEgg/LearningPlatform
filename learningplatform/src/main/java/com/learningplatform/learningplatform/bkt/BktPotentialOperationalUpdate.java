package com.learningplatform.learningplatform.bkt;

import learningplatform.learningplatform.calculation.MostDifficultyCalculation;

public class BktPotentialOperationalUpdate {

	boolean correct;
	MostDifficultyCalculation mostDifficultyCalculation;

	public BktPotentialOperationalUpdate(boolean correct, MostDifficultyCalculation mostDifficultyCalculation) {
		this.correct = correct;
		this.mostDifficultyCalculation = mostDifficultyCalculation;

	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}

	public MostDifficultyCalculation getMostDifficultyCalculation() {
		return mostDifficultyCalculation;
	}

	public void setMostDifficultyCalculation(MostDifficultyCalculation mostDifficultyCalculation) {
		this.mostDifficultyCalculation = mostDifficultyCalculation;
	}

}
