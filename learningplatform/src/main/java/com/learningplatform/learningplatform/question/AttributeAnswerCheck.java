package com.learningplatform.learningplatform.question;

import com.learningplatform.learningplatform.types.UserResult;

import learningplatform.learningplatform.calculation.OptimalSolution;

public class AttributeAnswerCheck {
	private Boolean correctAnswer;
	private Boolean correctRoundedAnswer;
	private Boolean correctCalculation;
	private Float correctComponents;
	private OptimalSolution optimalSolution;
	private double calculationResult;
	private boolean correctFormular;
	private boolean correctValues;
	private boolean wrongCalculationButCorrect;


	public AttributeAnswerCheck(Boolean correctAnswer, Boolean correctRoundedAnswer, UserResult userResult,
			Float correctComponents, OptimalSolution optimalSolution, boolean wrongCalculationButCorrect) {
		this.correctAnswer = correctAnswer;
		this.correctRoundedAnswer = correctRoundedAnswer;
		this.correctCalculation = userResult.isCorrect();
		this.correctComponents = correctComponents;
		this.optimalSolution = optimalSolution;
		this.calculationResult = userResult.getUserCalculationResult();
		this.wrongCalculationButCorrect = wrongCalculationButCorrect;
	}

	public Boolean getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(Boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	public Boolean getCorrectRoundedAnswer() {
		return correctRoundedAnswer;
	}

	public void setCorrectRoundedAnswer(Boolean correctRoundedAnswer) {
		this.correctRoundedAnswer = correctRoundedAnswer;
	}

	public Boolean getCorrectCalculation() {
		return correctCalculation;
	}

	public void setCorrectCalculation(Boolean correctCalculation) {
		this.correctCalculation = correctCalculation;
	}

	public Float getCorrectComponents() {
		return correctComponents;
	}

	public void setCorrectComponents(Float correctComponents) {
		this.correctComponents = correctComponents;
	}

	public OptimalSolution getOptimalSolution() {
		return optimalSolution;
	}

	public void setOptimalSolution(OptimalSolution optimalSolution) {
		this.optimalSolution = optimalSolution;
	}

	public double getCalculationResult() {
		return calculationResult;
	}

	public void setCalculationResult(double calculationResult) {
		this.calculationResult = calculationResult;
	}

	public boolean isCorrectFormular() {
		return correctFormular;
	}

	public void setCorrectFormular(boolean correctFormular) {
		this.correctFormular = correctFormular;
	}

	public boolean isCorrectValues() {
		return correctValues;
	}

	public void setCorrectValues(boolean correctValues) {
		this.correctValues = correctValues;
	}
	
	public boolean isWrongCalculationButCorrect() {
		return wrongCalculationButCorrect;
	}

	public void setWrongCalculationButCorrect(boolean wrongCalculationButCorrect) {
		this.wrongCalculationButCorrect = wrongCalculationButCorrect;
	}

}
