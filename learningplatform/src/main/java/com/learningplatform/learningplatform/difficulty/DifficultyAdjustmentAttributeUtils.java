package com.learningplatform.learningplatform.difficulty;

import java.util.Comparator;
import java.util.List;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.difficulty.conceptDifficulty.ComputationalRulesDifficulty;
import com.learningplatform.learningplatform.difficulty.conceptDifficulty.MeasurementDifficulty;
import com.learningplatform.learningplatform.difficulty.conceptDifficulty.PercentCalculationDifficulty;
import com.learningplatform.learningplatform.models.Settings;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

public class DifficultyAdjustmentAttributeUtils {

	/**
	 * Find an attribute based on difficulty.
	 * 
	 * @param possibleAttributes a list of all possible attributes that could be
	 *                           chosen
	 * @return Attribute that was chosen
	 */
	public static Attribute chooseAttributeByDifficulty(List<Attribute> possibleAttributes,
			DifficultyCalculated difficultyCalculated) {
		if (possibleAttributes.size() == 1) {
			return possibleAttributes.get(0);
		}
		float numberMagnitudePercentage = 1;

		numberMagnitudePercentage = (float) difficultyCalculated.getComputationalRulesDifficulty().getDifficulty()
				/ 100;

		possibleAttributes.sort(Comparator.comparingDouble(i -> (float) i.getNumericalValue()));

		int targetValue = Math.round((float) (possibleAttributes.size() - 1) * numberMagnitudePercentage);

		float randomFloat = UtilitiesHelper.getRandom().nextFloat();
		if (targetValue == 0) {
			if (randomFloat <= 0.8f) {
				return possibleAttributes.get(0);
			} else {
				return possibleAttributes.get(1);
			}
		}

		if (targetValue == possibleAttributes.size() - 1) {
			if (randomFloat <= 0.8f) {
				return possibleAttributes.get(targetValue);
			} else {
				return possibleAttributes.get(targetValue - 1);
			}
		}

		if (randomFloat <= 0.2f) {
			return possibleAttributes.get(targetValue - 1);
		} else if (randomFloat <= 0.8f) {
			return possibleAttributes.get(targetValue);
		} else {
			return possibleAttributes.get(targetValue + 1);
		}
	}

	/**
	 * Get the average concept difficulty by taking an average of all concept
	 * skills.
	 * 
	 * @return average of concept difficulties
	 */
	public static float getAverageConceptDifficulty(DifficultyCalculated difficultyCalculated, boolean measurement,
			boolean percentage) {
		ComputationalRulesDifficulty computationalRulesDifficulty = difficultyCalculated
				.getComputationalRulesDifficulty();
		MeasurementDifficulty measurementDifficulty = difficultyCalculated.getMeasurementDifficulty();
		PercentCalculationDifficulty percentCalculationDifficulty = difficultyCalculated
				.getPercentCalculationDifficulty();

		int combinedDifficulty = computationalRulesDifficulty.getDifficulty();
		int count = 1;

		if (measurement) {
			combinedDifficulty += measurementDifficulty.getDifficulty();
			count++;
		}

		if (percentage) {
			combinedDifficulty += percentCalculationDifficulty.getDifficulty();
			count++;
		}

		return combinedDifficulty / count;

	}

}
