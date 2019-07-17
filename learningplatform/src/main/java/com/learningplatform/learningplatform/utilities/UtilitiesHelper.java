package com.learningplatform.learningplatform.utilities;

import java.util.Random;

import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

public class UtilitiesHelper {

	private static final Random random = new Random();

	private UtilitiesHelper() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Helper method to return the string of the given operator
	 * 
	 * @param operator the operator of which the string is sought after
	 * @return String of the operator
	 */
	public static String getOperatorString(Operators operator) {
		switch (operator) {
		case ADDITION:
			return "+";
		case DIVISION:
			return "/";
		case MULTIPLICATION:
			return "*";
		case SUBSTRACTION:
			return "-";
		default:
			break;

		}
		return null;
	}

	public static Random getRandom() {
		return random;
	}

}
