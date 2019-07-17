package com.learningplatform.learningplatform.question;

import com.learningplatform.learningplatform.types.UserResult;

import learningplatform.learningplatform.calculation.OptimalSolution;

public class QuestionAnswerCheck {

	private Boolean correctQuestionAnswer;
	private Boolean correctQuestionCalculation;
	private Float correctQuestionComponents;
	private OptimalSolution optimalSolution;
	private double calculationResult;
	private boolean correctFormular;
	private boolean correctValues;
	private boolean attributeOnly;
	private boolean wrongCalculationButCorrect;

	public QuestionAnswerCheck(Boolean correctQuestionAnswer, UserResult userResult, Float correctQuestionComponents,
			OptimalSolution optimalSolution, boolean wrongCalculationButCorrect) {
		this.correctQuestionAnswer = correctQuestionAnswer;
		this.correctQuestionCalculation = userResult.isCorrect();
		this.correctQuestionComponents = correctQuestionComponents;
		this.optimalSolution = optimalSolution;
		this.calculationResult = userResult.getUserCalculationResult();
		this.wrongCalculationButCorrect = wrongCalculationButCorrect;
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

	public Boolean getCorrectQuestionAnswer() {
		return correctQuestionAnswer;
	}

	public void setCorrectQuestionAnswer(Boolean correctQuestionAnswer) {
		this.correctQuestionAnswer = correctQuestionAnswer;
	}

	public Boolean getCorrectQuestionCalculation() {
		return correctQuestionCalculation;
	}

	public void setCorrectQuestionCalculation(Boolean correctQuestionCalculation) {
		this.correctQuestionCalculation = correctQuestionCalculation;
	}

	public Float getCorrectQuestionComponents() {
		return correctQuestionComponents;
	}

	public void setCorrectQuestionComponents(Float correctQuestionComponents) {
		this.correctQuestionComponents = correctQuestionComponents;
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
	
	public boolean isAttributeOnly() {
		return attributeOnly;
	}

	public void setAttributeOnly(boolean attributeOnly) {
		this.attributeOnly = attributeOnly;
	}

	public void setCalculationResult(double calculationResult) {
		this.calculationResult = calculationResult;
	}
	public boolean isWrongCalculationButCorrect() {
		return wrongCalculationButCorrect;
	}

	public void setWrongCalculationButCorrect(boolean wrongCalculationButCorrect) {
		this.wrongCalculationButCorrect = wrongCalculationButCorrect;
	}
}
