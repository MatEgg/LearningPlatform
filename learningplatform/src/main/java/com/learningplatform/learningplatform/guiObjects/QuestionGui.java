package com.learningplatform.learningplatform.guiObjects;

import learningplatform.learningplatform.Calculation.OptimalSolution;

/**
 * Overview of the questions that are used with a Word Problem.
 *
 */
public class QuestionGui {

	String question;
	int id;
	float result;
	OptimalSolution optimalSolution;
	boolean procent;

	public QuestionGui(String question, int id, float result, OptimalSolution optimalSolution, boolean procent) {
		this.question = question;
		this.id = id;
		this.result = result;
		this.optimalSolution = optimalSolution;
		this.procent = procent;
	}

	public String getQuestion() {
		return question;
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

	public OptimalSolution getOptimalSolution() {
		return optimalSolution;
	}

	public void setOptimalSolution(OptimalSolution optimalSolution) {
		this.optimalSolution = optimalSolution;
	}
	
	public boolean isProcent() {
		return procent;
	}

	public void setProcent(boolean procent) {
		this.procent = procent;
	}
}
