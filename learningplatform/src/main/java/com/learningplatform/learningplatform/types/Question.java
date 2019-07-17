package com.learningplatform.learningplatform.types;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.tasks.WordProblem;

import learningplatform.learningplatform.Calculation.CalculationInformation;
import learningplatform.learningplatform.Calculation.OptimalSolution;
import learningplatform.learningplatform.Calculation.ValidCalculation;

public class Question implements Comparable<Question> {

	int id;
	float result;

	CalculationInformation calculationInformation;
	String questionText;
	Attribute targetAttribute;
	String name;
	WordProblem wordProblem;
	List<Attribute> relevantAttributes;
	OptimalSolution optimalSolution;
	Boolean attributeOnly = false;
	List<ValidCalculation> validCalculations;
	boolean procent = false;
	int difficulty = 1;

	public Question(String questionText, Attribute targetAttribute) {
		this.questionText = questionText;
		this.targetAttribute = targetAttribute;
		relevantAttributes = new ArrayList<>();
		id = WordProblem.getInstance().questionID++;
		calculationInformation = new CalculationInformation();
		validCalculations = new ArrayList<>();
	}

	public Question(String questionText, Number result, int difficulty) {
		this.result = result.floatValue();
		this.questionText = questionText;
		this.difficulty = difficulty;
		relevantAttributes = new ArrayList<>();
		id = WordProblem.getInstance().questionID++;
		calculationInformation = new CalculationInformation();
		validCalculations = new ArrayList<>();
	}

	public Question(String questionText, Number result, int difficulty, int id) {
		this.result = result.floatValue();
		this.questionText = questionText;
		this.difficulty = difficulty;
		relevantAttributes = new ArrayList<>();
		this.id = id;
		calculationInformation = new CalculationInformation();
		validCalculations = new ArrayList<>();
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public Attribute getTargetAttribute() {
		return targetAttribute;
	}

	public void setTargetAttribute(Attribute targetAttribute) {
		this.targetAttribute = targetAttribute;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getResult() {
		return result;
	}

	public void setResult(float result) {
		this.result = result;
	}

	public List<Attribute> getRelevantAttributes() {
		return relevantAttributes;
	}

	public void setRelevantAttributes(List<Attribute> relevantAttributes) {
		this.relevantAttributes = relevantAttributes;
	}

	public OptimalSolution getOptimalSolution() {
		return optimalSolution;
	}

	public void setOptimalSolution(OptimalSolution optimalSolution) {
		this.optimalSolution = optimalSolution;
	}

	public CalculationInformation getCalculationInformation() {
		return calculationInformation;
	}

	public void setCalculationInformation(CalculationInformation calculationInformation) {
		this.calculationInformation = calculationInformation;
	}

	public Boolean getAttributeOnly() {
		return attributeOnly;
	}

	public void setAttributeOnly(Boolean attributeOnly) {
		this.attributeOnly = attributeOnly;
	}

	public List<ValidCalculation> getValidCalculations() {
		return validCalculations;
	}

	public void setValidCalculations(List<ValidCalculation> validCalculations) {
		this.validCalculations = validCalculations;
	}

	@Override
	public int compareTo(Question o) {
		return Integer.compare(getOptimalSolution().getCalculationStats().getStepsNeeded(),
				o.getOptimalSolution().getCalculationStats().getStepsNeeded());
	}

	public boolean isProcent() {
		return procent;
	}

	public void setProcent(boolean procent) {
		this.procent = procent;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

}
