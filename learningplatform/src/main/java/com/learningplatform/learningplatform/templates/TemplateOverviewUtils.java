package com.learningplatform.learningplatform.templates;

import java.util.Random;

import com.learningplatform.learningplatform.templates.connectedTemplates.CalculateAreaOfStore;
import com.learningplatform.learningplatform.templates.connectedTemplates.IncomeThroughVisitors;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

public class TemplateOverviewUtils {

	final static Random random = new Random();

	public static int diffOver70(int templateDifficulty, CalculateAreaOfStore calculateAreaOfStore,
			int accumulatedDifficulty, int conceptDifficulty) {
		int addedDifficultyScalingArea = 0;
		int tempAddedDifficultyScalingIncomeArea = 0;

		float pick = (float) (((random.nextFloat() / 100) * 30) + 0.70);
		if (pick * 100 > templateDifficulty + 5) {
			calculateAreaOfStore.setLevel(2);
		} else {
			tempAddedDifficultyScalingIncomeArea = (100 - templateDifficulty) * 2;
			if ((accumulatedDifficulty + tempAddedDifficultyScalingIncomeArea) <= conceptDifficulty) {
				calculateAreaOfStore.setLevel(3);
				addedDifficultyScalingArea = tempAddedDifficultyScalingIncomeArea;
			} else {
				calculateAreaOfStore.setLevel(2);
			}
		}
		return addedDifficultyScalingArea;
	}

	public static int diffBetween35And75(int templateDifficulty, CalculateAreaOfStore calculateAreaOfStore,
			int accumulatedDifficulty, int conceptDifficulty) {
		int addedDifficultyScalingArea = 0;
		int tempAddedDifficultyScalingIncomeArea = 0;

		float pick = (float) (((random.nextFloat() / 100) * 35) + 0.35);

		if (pick * 100 <= templateDifficulty + 5) {

			tempAddedDifficultyScalingIncomeArea = (70 - templateDifficulty) * 2;
			if (((accumulatedDifficulty + tempAddedDifficultyScalingIncomeArea) <= conceptDifficulty)) {
				calculateAreaOfStore.setLevel(2);
				addedDifficultyScalingArea = tempAddedDifficultyScalingIncomeArea;
			}
		}
		return addedDifficultyScalingArea;
	}

	public static int diffOver70Income(int templateDifficulty, IncomeThroughVisitors incomeThroughVisitors,
			int accumulatedDifficulty, int conceptDifficulty ) {
		int addedDifficultyScalingIncome = 0;
		int tempAddedDifficultyScalingIncome = 0;

		float pick = (float) (((UtilitiesHelper.getRandom().nextFloat() / 100) * 30) + 0.70);

		if (pick * 100 > templateDifficulty) {
			incomeThroughVisitors.setLevel(2);
		} else {
			tempAddedDifficultyScalingIncome = (100 - templateDifficulty) * 2;
			if (((accumulatedDifficulty + tempAddedDifficultyScalingIncome) <= conceptDifficulty)) {
				incomeThroughVisitors.setLevel(3);
				addedDifficultyScalingIncome = tempAddedDifficultyScalingIncome;
			} else {
				incomeThroughVisitors.setLevel(2);
			}
		}
		return addedDifficultyScalingIncome;
	}

	public static int diffBetween35And70(int templateDifficulty, IncomeThroughVisitors incomeThroughVisitors,
			int accumulatedDifficulty, int conceptDifficulty) {

		int addedDifficultyScalingIncome = 0;
		int tempAddedDifficultyScalingIncome = 0;

		float pick = (float) (((UtilitiesHelper.getRandom().nextFloat() / 100) * 35) + 0.35);
		if (pick * 100 <= templateDifficulty + 5) {

			tempAddedDifficultyScalingIncome = (70 - templateDifficulty) * 2;
			if (((accumulatedDifficulty + tempAddedDifficultyScalingIncome) <= conceptDifficulty)) {
				incomeThroughVisitors.setLevel(2);
				addedDifficultyScalingIncome = tempAddedDifficultyScalingIncome;
			}
		}
		return addedDifficultyScalingIncome;
	}
}
