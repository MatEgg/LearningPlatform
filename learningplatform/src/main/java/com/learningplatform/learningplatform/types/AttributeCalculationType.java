package com.learningplatform.learningplatform.types;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.learningplatform.learningplatform.question.AttributeAnswerCheck;

import learningplatform.learningplatform.calculation.SingleCalculation;
import learningplatform.learningplatform.calculation.ValidCalculation;

public class AttributeCalculationType {

	List<SingleCalculation> singleCalculations;
	AttributeAnswerCheck attributeAnswerCheck;
	List<CalculationStep> calculationSteps;
	ResultStep resultStep;
	TargetAttribute targetAttribute;
	List<TargetAttribute> containedAttributes;

	float userResult;

	@JsonCreator
	public AttributeCalculationType(@JsonProperty("calculationSteps$") List<CalculationStep> calculationSteps,
			@JsonProperty("resultStep$") ResultStep resultStep,
			@JsonProperty("targetAttribute$") TargetAttribute targetAttribute,
			@JsonProperty("containedAttributes$") List<TargetAttribute> containedAttributes) {
		this.calculationSteps = calculationSteps;
		this.resultStep = resultStep;
		this.targetAttribute = targetAttribute;
		this.containedAttributes = containedAttributes;
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

	public TargetAttribute getTargetAttribute() {
		return targetAttribute;
	}

	public void setTargetAttribute(TargetAttribute targetAttribute) {
		this.targetAttribute = targetAttribute;
	}

	public AttributeAnswerCheck getAttributeAnswerCheck() {
		return attributeAnswerCheck;
	}

	public void setAttributeAnswerCheck(AttributeAnswerCheck attributeAnswerCheck) {
		this.attributeAnswerCheck = attributeAnswerCheck;
	}

	public float getUserResult() {
		return userResult;
	}

	public void setUserResult(float userResult) {
		this.userResult = userResult;
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

}
