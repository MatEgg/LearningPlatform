package com.learningplatform.learningplatform.difficulty;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.difficulty.conceptDifficulty.ComputationalRulesDifficulty;
import com.learningplatform.learningplatform.difficulty.conceptDifficulty.MeasurementDifficulty;
import com.learningplatform.learningplatform.difficulty.conceptDifficulty.PercentCalculationDifficulty;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.DecimalCountDifficultyCost;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.DigitCountDifficultyCost;
import com.learningplatform.learningplatform.difficulty.operationalDifficulty.NumberTypeDifficultyCost;
import com.learningplatform.learningplatform.models.Field;
import com.learningplatform.learningplatform.models.Settings;
import com.learningplatform.learningplatform.models.difficulty.Difficulty;
import com.learningplatform.learningplatform.models.service.FieldService;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.types.Question;
import com.learningplatform.learningplatform.utilities.NumberCreation;
import com.learningplatform.learningplatform.utilities.QuestionComparator;
import com.learningplatform.learningplatform.utilities.QuestionDifficultyComparator;
import com.learningplatform.learningplatform.utilities.QuestionTextHelper;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles the adjustment of difficulty processes.
 *
 */
public class DifficultyAdjustmentUtils {

	private DifficultyAdjustmentUtils() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Round number based on difficulty settings
	 * 
	 * @param number   the number to be rounded
	 * @param operator the operator used with the number
	 * @return rounded number
	 */
	public static float roundNumber(float number, Operators operator) {
		DifficultyCalculated difficultyCalculated = DifficultyHandler.getInstance().getDifficulty();
		int decimalLevel = 1;
		if (difficultyCalculated.getDecimalenabled()) {
			switch (operator) {
			case ADDITION:
				decimalLevel = QuestionTextHelper.getDecimalCountByDifficulty(
						difficultyCalculated.getAdditionDifficulty().getDecimalCountDifficultyCost().getLevel());
				break;
			case SUBSTRACTION:
				decimalLevel = QuestionTextHelper.getDecimalCountByDifficulty(
						difficultyCalculated.getSubtractionDifficulty().getDecimalCountDifficultyCost().getLevel());
				break;
			case MULTIPLICATION:
				decimalLevel = QuestionTextHelper.getDecimalCountByDifficulty(
						difficultyCalculated.getMultiplicationDifficulty().getDecimalCountDifficultyCost().getLevel());
				break;
			case DIVISION:
				decimalLevel = QuestionTextHelper.getDecimalCountByDifficulty(
						difficultyCalculated.getDivisionDifficulty().getDecimalCountDifficultyCost().getLevel());
				break;
			default:
				break;

			}
		}

		if (number < 1 && decimalLevel == 1) {
			return 1;
		}
		return roundNumber(number, decimalLevel);
	}

	private static float roundNumber(float number, int decimalLevel) {
		return QuestionTextHelper.roundNumber(number, decimalLevel);
	}

	public static float getMultiplierBasedOnDifficulty(int bound, Operators operator) {
		float createdNumber = NumberCreation.createNumberWithParameters(bound, operator, false, 1, 0,
				DifficultyHandler.getInstance().getDifficulty());
		return roundNumber(createdNumber, 1);
	}

	public static float getPercentageMultiplierBasedOnDifficulty(Operators operator, int lowerBound, int upperBound) {
		return getPercentageMultiplierBasedOnDifficulty(upperBound, operator, lowerBound, upperBound);
	}

	public static float getPercentageMultiplierBasedOnDifficulty(int bound, Operators operator, int lowerBound,
			int upperBound) {
		float createdNumber = NumberCreation.createNumberWithParameters(bound, operator, true, lowerBound, upperBound,
				DifficultyHandler.getInstance().getDifficulty());
		return roundNumber(createdNumber, 1);
	}

	public static float createMultiplier(int bound) {
		return (float) UtilitiesHelper.getRandom().nextInt(bound);
	}

	public static Attribute chooseAttributeByDifficulty(List<Attribute> possibleAttributes) {
		return DifficultyAdjustmentAttributeUtils.chooseAttributeByDifficulty(possibleAttributes,
				DifficultyHandler.getInstance().getDifficulty());
	}

	public static float getAverageConceptDifficulty() {
		return DifficultyAdjustmentAttributeUtils.getAverageConceptDifficulty(
				DifficultyHandler.getInstance().getDifficulty(),
				WordProblem.getInstance().getSettings().getMeasurementEnabled() == 1,
				WordProblem.getInstance().getSettings().getPercentageEnabled() == 1);
	}

	/**
	 * Check if an attribute is replaceable or not
	 * 
	 * @param attribute potential replaced attribute
	 * @return boolean if replacement is possible
	 */
	public static boolean checkIfReplaceable(Attribute attribute) {
		FieldService fieldService = (FieldService) attribute.getElement().getModelService();
		Field field = (Field) fieldService.getMinimumValue(attribute.getAttributeType());
		if (!field.getName().equals(attribute.getElement().getElementText())) {
			return true;
		}
		return false;
	}

}
