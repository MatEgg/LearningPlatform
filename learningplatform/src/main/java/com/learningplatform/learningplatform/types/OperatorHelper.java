package com.learningplatform.learningplatform.types;

public class OperatorHelper {
	
	private OperatorHelper() {
		throw new IllegalStateException("Utility class");
	}

	public static enum Operators {
		ADDITION, SUBSTRACTION, MULTIPLICATION, DIVISION
	}

	public static String getOperatorText(Operators operator) {
		switch (operator) {
		case ADDITION:
			return "+";
		case SUBSTRACTION:
			return "-";
		case MULTIPLICATION:
			return "*";
		case DIVISION:
			return "/";
		default:
			return "none";
		}
	}
}
