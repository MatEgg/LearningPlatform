package com.learningplatform.learningplatform.utilities;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class QuestionTextHelper {
	
	private QuestionTextHelper() {
		throw new IllegalStateException("Utility class");
	}
	
	/**
	 * rounds a number
	 * 
	 * @param number            number to be rounded
	 * @param decimalCountLevel decimal count that is desired
	 * @return rounded number
	 */
	public static float roundNumber(float number, int decimalCountLevel) {
		int minimumDecimalCount = Math.max(checkMinimalDecimalCount(number),
				getDecimalCountByDifficulty(decimalCountLevel));
		String decimalSigns = generateDecimalSigns(minimumDecimalCount);
		Locale locale = new Locale("en", "UK");
		DecimalFormat df = (DecimalFormat) NumberFormat.getNumberInstance(locale);
		String pattern = "#." + decimalSigns;
		df.applyPattern(pattern);
		return Float.parseFloat(df.format((double) number));

	}

	// generates decimal signs that are desired based on the parameter
	private static String generateDecimalSigns(int decimalCount) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < decimalCount; i++) {
			sb.append("#");
		}
		return sb.toString();
	}

	/**
	 * fixes the float representation and returns a String
	 * 
	 * @param number number that is to be returned as a string
	 * @return String of the float
	 */
	public static String fixFloatRepresentation(float number) {
		if (checkIfHasDecimals(number)) {
			return Integer.toString((int) number);
		} else {
			return Float.toString(number);
		}
	}

	private static boolean checkIfHasDecimals(float number) {

		String[] splitter = Float.toString(number).split("\\.");
		return splitter[1].length() == 1 && splitter[1].equals("0");
	}

	/**
	 * Check the minimal decimal count that is needed for the number
	 * 
	 * @param number the number that is being analysed in regards to minimum decimal
	 *               places needed
	 * @return minimum decimal count
	 */
	public static int checkMinimalDecimalCount(float number) {
		if (number < 1) {
			String[] splitter = Float.toString(number).split("\\.");
			String numberString = splitter[1];

			if (numberString.length() == 0) {
				return 0;
			}

			int count = 0;
			while (numberString.length() > count && numberString.charAt(count) == '0') {
				count++;
			}
			return count + 1;
		}
		return 0;
	}

	/**
	 * checks the difficulty count level and returns a decimal count based on it
	 * 
	 * @param decimalCountLevel the decimal count related difficulty level
	 * @return the decimal count
	 */
	public static int getDecimalCountByDifficulty(int decimalCountLevel) {
		switch (decimalCountLevel) {
		case 1:
			return 0;
		case 2:
			return 1;
		case 3:
			return 2;
		case 4:
			return 3;
		default:
			return 0;
		}
	}

}
