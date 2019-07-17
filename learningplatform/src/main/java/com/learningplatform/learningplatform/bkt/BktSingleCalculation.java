package com.learningplatform.learningplatform.bkt;

import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

import learningplatform.learningplatform.Calculation.SingleCalculation;


/**
 * Represents a single calculation with all its difficulty determinations
 *
 */
public class BktSingleCalculation {

	private int decimalLevel;
	private int bigDigitLevel;
	private int numericalLevel;
	private SingleCalculation singleCalc;
	private Operators operator;

	/** On initialisation the levels of the single Calculation of the parameters are checked.
	 * @param singleCalc The single calculation part of a larger calculation
	 */
	public BktSingleCalculation(SingleCalculation singleCalc) {
		this.singleCalc = singleCalc;
		checkLevels();
		this.operator = singleCalc.getOperator();
	}

	/**
	 * check numerous levels of different characteristics of a single calculation.
	 */
	public void checkLevels() {
		float firstValue = singleCalc.getFirstValue();
		float secondValue = singleCalc.getSecondValue();
		Float[] firstAndSecondVal = new Float[] { firstValue, secondValue };

		this.decimalLevel = checkDecimalLevel(firstAndSecondVal);
		this.bigDigitLevel = checkBigDigitLevel(firstAndSecondVal);
		this.numericalLevel = checkNumericalLevel(firstAndSecondVal);

	}

	public int getDecimalLevel() {
		return decimalLevel;
	}

	public void setDecimalLevel(int decimalLevel) {
		this.decimalLevel = decimalLevel;
	}

	public int getBigDigitLevel() {
		return bigDigitLevel;
	}

	public void setBigDigitLevel(int bigDigitLevel) {
		this.bigDigitLevel = bigDigitLevel;
	}

	public int getNumericalLevel() {
		return numericalLevel;
	}

	public void setNumericalLevel(int numericalLevel) {
		this.numericalLevel = numericalLevel;
	}

	public SingleCalculation getSingleCalc() {
		return singleCalc;
	}

	public void setSingleCalc(SingleCalculation singleCalc) {
		this.singleCalc = singleCalc;
	}

	protected int checkDecimalLevel(Float[] firstAndSecondVal) {
		int maxLevel = 0;
		int tempLevel = 0;
		for (float value : firstAndSecondVal) {

			String[] splitValue = String.valueOf(value).split("\\.");
			
			if (Float.parseFloat(splitValue[1])  != 0) {
				tempLevel = splitValue[1].length() + 1;
			}

			if (tempLevel > maxLevel) {
				maxLevel = tempLevel;
			}
		}

		return Math.min(maxLevel, 4);
	}

	protected int checkBigDigitLevel(Float[] firstAndSecondVal) {
		int maxLevel = 0;
		for (float value : firstAndSecondVal) {
			int tempLevel = 0;

			tempLevel = calculateBigDigitLevelByValue(value);

			if (tempLevel > maxLevel) {
				maxLevel = tempLevel;
			}
		}

		return maxLevel;
	}

	protected int checkNumericalLevel(Float[] firstAndSecondVal) {

		int maxLevel = 0;
		for (float value : firstAndSecondVal) {
			int tempLevel = 0;

			tempLevel = calculateNumericalTypeLevelByValue(value);

			if (tempLevel > maxLevel) {
				maxLevel = tempLevel;
			}
		}

		return maxLevel;
	}

	private int calculateNumericalTypeLevelByValue(float value) {
		if (value == 1) {
			return 1;
		}

		if (value % 5 == 0) {
			return 2;
		}

		if (value % 2 == 0) {
			return 3;
		}
		return 4;

	}

	private int calculateBigDigitLevelByValue(float value) {
		if (value <= 2) {
			return 1;
		}

		if (value <= 5) {
			return 2;
		}

		if (value <= 10) {
			return 3;
		}

		if (value <= 15) {
			return 4;
		}

		if (value <= 20) {
			return 5;
		}

		if (value <= 30) {
			return 6;
		}

		if (value <= 40) {
			return 7;
		}

		if (value <= 50) {
			return 8;
		}

		if (value <= 99) {
			return 9;
		}

		return 10;
	}

	public Operators getOperator() {
		return operator;
	}

	public void setOperator(Operators operator) {
		this.operator = operator;
	}

}
