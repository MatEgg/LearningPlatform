package com.learningplatform.learningplatform.guiObjects;

import learningplatform.learningplatform.calculation.OptimalSolution;

public class AttributeGui {

	int id;

	String text;
	String unit;
	Number value;
	Boolean isVisible;

	OptimalSolution optimalSolution;

	public AttributeGui(int id, String text, String unit, Number value, Boolean isVisible, OptimalSolution optimalSolution) {
		this.id = id;
		this.text = text;
		this.unit = unit;
		this.value = value;
		this.isVisible = isVisible;
		this.optimalSolution = optimalSolution;
	}

	public String getText() {
		return text;
	}
	
	public String getUnit() {
		return unit;
	}

	public Number getValue() {
		return value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}
	
	public OptimalSolution getOptimalSolution() {
		return optimalSolution;
	}

	public void setOptimalSolution(OptimalSolution optimalSolution) {
		this.optimalSolution = optimalSolution;
	}

}
