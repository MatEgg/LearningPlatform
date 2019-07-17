package com.learningplatform.learningplatform.types;

public class UserResult {

	private boolean isCorrect;
	private double userCalculationResult;

	public UserResult(boolean isCorrect, double userCalculationResult) {
		this.isCorrect = isCorrect;
		this.userCalculationResult = userCalculationResult;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	public double getUserCalculationResult() {
		return userCalculationResult;
	}

	public void setUserCalculationResult(double userCalculationResult) {
		this.userCalculationResult = userCalculationResult;
	}
}
