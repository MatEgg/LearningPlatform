package com.learningplatform.learningplatform.bkt;

import learningplatform.learningplatform.calculation.MostDifficultyCalculation;

public class BktHandlerUtils {

	static final float TRANSIT = 0.09f;
	static final float SLIP = 0.09f;
	static final float GUESS = 0.14f;

	/**
	 * Checks if the level of the calculation is higher or lower than the student's
	 * skill level
	 * 
	 * @param oldDecimalLevel               decimal level of student
	 * @param oldBigNumberLevel             big number level of student
	 * @param oldNumericalTypeLevel         numerical type level of the student
	 * @param bktPotentialOperationalUpdate potential update packet
	 * @return bktUpdateOperational new update packet
	 */
	public static BktUpdateOperational checkIfLevelHigher(int oldDecimalLevel, int oldBigNumberLevel,
			int oldNumericalTypeLevel, BktPotentialOperationalUpdate bktPotentialOperationalUpdate) {
		boolean isCorrect = bktPotentialOperationalUpdate.isCorrect();
		MostDifficultyCalculation mostDifficultyCalculation = bktPotentialOperationalUpdate
				.getMostDifficultyCalculation();
		boolean decimal;
		boolean bigNumber;
		boolean numericalType;
		if (isCorrect) {
			decimal = mostDifficultyCalculation.getDecimalLevel() >= oldDecimalLevel;
			bigNumber = mostDifficultyCalculation.getBigDigitLevel() >= oldBigNumberLevel;
			numericalType = mostDifficultyCalculation.getNumericalLevel() >= oldNumericalTypeLevel;
		} else {

			decimal = mostDifficultyCalculation.getDecimalLevel() != 0;
			bigNumber = true;
			numericalType = true;
		}

		if (mostDifficultyCalculation.getDecimalLevel() == 0) {
			decimal = false;
		}

		boolean update = (decimal || bigNumber || numericalType);

		BktUpdateOperational bktUpdateOperational = new BktUpdateOperational(BktTypeOperational.ADDITION_SKILL, decimal,
				bigNumber, numericalType, bktPotentialOperationalUpdate.isCorrect(), update);
		return bktUpdateOperational;

	}

	/**
	 * Extracts level of the current skill
	 * 
	 * @param skillLevel current complete skill level e.g. 423
	 * @return level of the skill
	 */
	public static int extractLevel(int skillLevel) {
		if (skillLevel >= 1000) {
			return 10;
		} else if (skillLevel < 100) {
			return 1;
		}
		return String.valueOf(skillLevel).charAt(0) - '0';
	}

	/**
	 * Calculates new complete level value
	 * 
	 * @param level          current level
	 * @param updatedMastery new mastery
	 * @param upperLimit     upper limit of the mastery that is possible for the
	 *                       skill
	 * @return new complete value
	 */
	public static int calculateNewValue(int level, int updatedMastery, int upperLimit, int sensibility,
			boolean result) {
		int levelUpLimit = getLevelUpLimit(sensibility);
		int levelDownLimit = getLevelDownLimit(sensibility);

		if (updatedMastery > levelUpLimit) {
			if (level < upperLimit) {
				return (level + 1) * 100 + 30;
			}
		} else if (updatedMastery < levelDownLimit && level > 1 && !result) {
			return (level - 1) * 100 + 70;
		}
		return level * 100 + updatedMastery;
	}

	/**
	 * Calculate the level up threshold.
	 * 
	 * @param sensibility sensibility settings that determine the thresholds
	 * @return the determined limit
	 */
	public static int getLevelUpLimit(int sensibility) {
		if (sensibility == 1) {
			return 98;
		} else if (sensibility == 2) {
			return 90;
		} else if (sensibility == 3) {
			return 80;
		} else if (sensibility == 4) {
			return 70;
		} else if (sensibility < 1) {
			return 98;
		}
		return 70;
	}

	/**
	 * Calculate the level down threshold.
	 * 
	 * @param sensibility sensibility settings that determine the thresholds
	 * @return the determined limit
	 */
	public static int getLevelDownLimit(int sensibility) {
		if (sensibility == 1) {
			return 11;
		} else if (sensibility == 2) {
			return 15;
		} else if (sensibility == 3) {
			return 20;
		} else if (sensibility == 4) {
			return 25;
		} else if (sensibility < 1) {
			return 11;
		}
		return 25;
	}

	/**
	 * if the skill was applied correctly, update bkt like this
	 * 
	 * @param mastery current mastery
	 * @param slip    current slip probability
	 * @param guess   current guess probability
	 * @return new mastery
	 */
	private static float appliesSkillCorrectly(float mastery, float slip, float guess) {
		float top = mastery * (1 - slip);
		float bottom = top + ((1 - mastery) * guess);
		return top / bottom;
	}

	/**
	 * if the skill was applied wrongly, update bkt like this
	 * 
	 * @param mastery current mastery
	 * @param slip    current slip probability
	 * @param guess   current guess probability
	 * @return new mastery
	 */
	private static float appliesSkillWrongly(float mastery, float slip, float guess) {
		float top = mastery * slip;
		float bottom = top + ((1 - mastery) * (1 - guess));

		return top / bottom;
	}

	/**
	 * updates the mastery of the student with bkt
	 * 
	 * @param proficiency probability of proficiency of the student
	 * @param isResult    correctness of the result
	 * @param slip        probability of the student slipping
	 * @param guess       probability of the student guessing
	 * @return new mastery
	 */
	public static int updateMastery(int proficiency, boolean isResult, float slip, float guess, float transit) {
		float initialMastery = ((float) proficiency % 100) / 100;
		int updatedMastery;
		if (isResult) {
			updatedMastery = (int) (updateMastery(appliesSkillCorrectly(initialMastery, slip, guess), transit) * 100);
		} else {
			updatedMastery = (int) (updateMastery(appliesSkillWrongly(initialMastery, slip, guess), transit) * 100);
		}
		return updatedMastery;
	}

	private static float updateMastery(float obs, float transit) {
		return obs + ((1 - obs) * transit);
	}
}
