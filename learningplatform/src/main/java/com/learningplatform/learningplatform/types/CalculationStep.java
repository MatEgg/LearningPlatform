package com.learningplatform.learningplatform.types;

public class CalculationStep {
	String visualText;
	String name;
	float value;
	String operator;

	public CalculationStep(String visualText, float value, String operator) {
		this.visualText = visualText;
		this.value = value;
		this.operator = operator;
	}
	
	public CalculationStep() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVisualText() {
		return visualText;
	}

	public void setVisualText(String visualText) {
		this.visualText = visualText;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}
}
