package com.learningplatform.learningplatform.utilities;

import com.learningplatform.learningplatform.difficulty.DifficultyCalculated;
import com.learningplatform.learningplatform.difficulty.DifficultyHandler;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.DecimalCountDifficultyCost;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.DigitCountDifficultyCost;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.NumberTypeDifficultyCost;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

public class NumberCreation {

	/**
	 * Creates a number based on certain parameters and difficulty parameters
	 * 
	 * @param bound      maximum number
	 * @param difficulty difficulty settings
	 * @param operator   operator that is involved
	 * @param decimal    boolean if decimals are allowed
	 * @param lowerBound lower bound of the number
	 * @param upperBound upper bound of the number
	 * @return created number
	 */
	public static float createNumberWithParameters(int bound, Operators operator, boolean decimal, int lowerBound,
			int upperBound, DifficultyCalculated difficultyCalculated) {

		DigitCountDifficultyCost digitCountDifficultyCost;
		NumberTypeDifficultyCost numberTypeDifficultyCost;
		DecimalCountDifficultyCost decimalDifficultyCost;

		switch (operator) {
		case ADDITION:
			digitCountDifficultyCost = difficultyCalculated.getAdditionDifficulty().getDigitCountDifficultyCost();
			numberTypeDifficultyCost = difficultyCalculated.getAdditionDifficulty().getNumberTypeDifficultyCost();
			decimalDifficultyCost = difficultyCalculated.getAdditionDifficulty().getDecimalCountDifficultyCost();

			break;
		case SUBSTRACTION:
			digitCountDifficultyCost = difficultyCalculated.getSubtractionDifficulty().getDigitCountDifficultyCost();
			numberTypeDifficultyCost = difficultyCalculated.getSubtractionDifficulty().getNumberTypeDifficultyCost();
			decimalDifficultyCost = difficultyCalculated.getSubtractionDifficulty().getDecimalCountDifficultyCost();
			break;
		case MULTIPLICATION:

			digitCountDifficultyCost = difficultyCalculated.getMultiplicationDifficulty().getDigitCountDifficultyCost();
			numberTypeDifficultyCost = difficultyCalculated.getMultiplicationDifficulty().getNumberTypeDifficultyCost();
			decimalDifficultyCost = difficultyCalculated.getMultiplicationDifficulty().getDecimalCountDifficultyCost();

			break;
		case DIVISION:
			digitCountDifficultyCost = difficultyCalculated.getDivisionDifficulty().getDigitCountDifficultyCost();
			numberTypeDifficultyCost = difficultyCalculated.getDivisionDifficulty().getNumberTypeDifficultyCost();
			decimalDifficultyCost = difficultyCalculated.getDivisionDifficulty().getDecimalCountDifficultyCost();
			break;
		default:
			digitCountDifficultyCost = difficultyCalculated.getMultiplicationDifficulty().getDigitCountDifficultyCost();
			numberTypeDifficultyCost = difficultyCalculated.getMultiplicationDifficulty().getNumberTypeDifficultyCost();
			decimalDifficultyCost = difficultyCalculated.getMultiplicationDifficulty().getDecimalCountDifficultyCost();
			break;

		}
		if (!decimal) {
			return determineEasyToCalculateNumbers(bound, numberTypeDifficultyCost, digitCountDifficultyCost, null,
					false, lowerBound, upperBound);
		} else {
			return determineEasyToCalculateNumbers(bound, null, null, decimalDifficultyCost, true, lowerBound,
					upperBound);
		}
	}

	/**
	 * Creates a number based on difficulty settings
	 * 
	 * @param bound                 maximum number
	 * @param numberTypeDifficulty  difficulty of number types
	 * @param digitCountDifficulty  difficulty of digit counts
	 * @param decimalDifficultyCost difficulty of decimal count
	 * @param decimal               boolean if decimal calculations are allowed or
	 *                              not
	 * @param manualLowerBound      lower bound
	 * @param manualUpperBound      upper bound
	 * @return
	 */
	public static float determineEasyToCalculateNumbers(int bound, NumberTypeDifficultyCost numberTypeDifficulty,
			DigitCountDifficultyCost digitCountDifficulty, DecimalCountDifficultyCost decimalDifficultyCost,
			boolean decimal, int manualLowerBound, int manualUpperBound) {

		int digitCountLevel = 1;
		int numberTypeLevel = 1;
		int upperBound = 1;
		int lowerBound = 1;

		if (!decimal) {
			digitCountLevel = digitCountDifficulty.getLevel();
			numberTypeLevel = numberTypeDifficulty.getLevel();

			upperBound = digitCountDifficulty.getHigherBoundValueByLevel(digitCountLevel);
			lowerBound = getMinLowerBound(bound, digitCountLevel, digitCountDifficulty);
			
		} else {
			upperBound = manualUpperBound;
			lowerBound = manualLowerBound;
			numberTypeLevel = decimalDifficultyCost.getLevel();
		}

		if (numberTypeLevel == 0) {
			return 2;
		} else if (numberTypeLevel == 1) {
			return manualLowerBound;

		} else if (numberTypeLevel == 2) {

			// if numberTypeLevel == 2, find a number that is between the lower and upper
			// bound that has a remainder of 0 if divided by 5
			return level2Determination(bound, lowerBound, upperBound);

		} else if (numberTypeLevel == 3) {

			// if numberTypeLevel == 3, find a number that is between the lower and upper
			// bound that has a remainder of 0 if divided by 2
			return level3Determination(bound, lowerBound, upperBound);

		} else if (numberTypeLevel == 4) {
			// if numberTypeLevel == 4, find a number that is between the lower and upper
			// bound that has a remainder of !0 if divided by 2 and 5
			return level4Determination(bound, lowerBound, upperBound);
		}
		return 1;
	}

	private static float level2Determination(int bound, int lowerBound, int upperBound) {
		int upperBoundFives = Math.min(bound / 5, upperBound / 5);
		int lowerBoundFives = Math.max(lowerBound / 5, 1);

		if (upperBoundFives == lowerBoundFives) {
			return lowerBoundFives * 5;
		}
		if (upperBoundFives == 0) {
			if (UtilitiesHelper.getRandom().nextBoolean()) {
				return 2;
			} else {
				return 5;
			}
		}
		return (lowerBoundFives + UtilitiesHelper.getRandom().nextInt(upperBoundFives - lowerBoundFives + 1)) * 5;
	}

	private static float level3Determination(int bound, int lowerBound, int upperBound) {
		int upperBoundTwos = Math.min(bound / 2, upperBound / 2);
		int lowerBoundTwos = lowerBound / 2;
		if (upperBoundTwos == lowerBoundTwos) {
			return lowerBoundTwos * 2;
		}
		int resultDivByTwo = 5;

		while (resultDivByTwo % 5 == 0) {
			resultDivByTwo = (lowerBoundTwos + UtilitiesHelper.getRandom().nextInt(upperBoundTwos - lowerBoundTwos + 1))
					* 2;
		}

		return resultDivByTwo;

	}

	private static float level4Determination(int bound, int lowerBound, int upperBound) {
		int upperBoundComplex = Math.min(bound, upperBound);

		if (upperBoundComplex == lowerBound) {
			return upperBoundComplex;
		}

		float complexResult = 1;

		while (complexResult % 5 == 0 || complexResult % 2 == 0 || complexResult == 1) {
			complexResult = (lowerBound + UtilitiesHelper.getRandom().nextInt(upperBoundComplex - lowerBound + 1));
		}
		return complexResult;

	}

	private static int getMinLowerBound(int bound, int level, DigitCountDifficultyCost digitCountDifficulty) {
		int currentBound = digitCountDifficulty.getLowerBoundValueByLevel(level);
		int currentLevel = level;

		while (bound < currentBound) {
			currentBound = digitCountDifficulty.getLowerBoundValueByLevel(currentLevel);
			currentLevel--;
		}
		return currentBound;
	}
}
