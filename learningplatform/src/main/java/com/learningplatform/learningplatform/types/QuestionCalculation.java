package com.learningplatform.learningplatform.types;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import learningplatform.learningplatform.Calculation.SingleCalculation;

public class QuestionCalculation {

	List<SingleCalculation> singleCalculations;
	List<CalculationStep> calculationSteps;
	ResultStep resultStep;
	List<TargetAttribute> containedAttributes;
	boolean alreadyUpdated;
	int questionId;

	@JsonCreator
	public QuestionCalculation(@JsonProperty("calculationSteps$") List<CalculationStep> calculationSteps,
			@JsonProperty("resultStep$") ResultStep resultStep, @JsonProperty("questionId$") int questionId,
			@JsonProperty("containedAttributes$") List<TargetAttribute> containedAttributes,
			@JsonProperty("alreadyUpdated") boolean alreadyUpdated) {
		this.calculationSteps = calculationSteps;
		this.resultStep = resultStep;
		this.questionId = questionId;
		this.containedAttributes = containedAttributes;
		this.alreadyUpdated = alreadyUpdated;
		singleCalculations = new ArrayList<>();
	}

	public List<CalculationStep> getCalculationSteps() {
		return calculationSteps;
	}

	public void setCalculationSteps(List<CalculationStep> calculationSteps) {
		this.calculationSteps = calculationSteps;
	}

	public ResultStep getResultStep() {
		return resultStep;
	}

	public void setResultStep(ResultStep resultStep) {
		this.resultStep = resultStep;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public List<SingleCalculation> getSingleCalculations() {
		return singleCalculations;
	}

	public void setSingleCalculations(List<SingleCalculation> singleCalculations) {
		this.singleCalculations = singleCalculations;
	}

	public List<TargetAttribute> getContainedAttributes() {
		return containedAttributes;
	}

	public void setContainedAttributes(List<TargetAttribute> containedAttributes) {
		this.containedAttributes = containedAttributes;
	}

	public boolean isAlreadyUpdated() {
		return alreadyUpdated;
	}

	public void setAlreadyUpdated(boolean alreadyUpdated) {
		this.alreadyUpdated = alreadyUpdated;
	}

}
