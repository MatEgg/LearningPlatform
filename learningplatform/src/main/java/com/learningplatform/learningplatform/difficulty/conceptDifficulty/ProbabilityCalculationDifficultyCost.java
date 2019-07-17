package com.learningplatform.learningplatform.difficulty.conceptDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyCost;

import java.util.HashMap;
import java.util.Map;

public class ProbabilityCalculationDifficultyCost extends DifficultyCost {

	public ProbabilityCalculationDifficultyCost(int individualDifficultySettings) {
		super(individualDifficultySettings);
	}

	@Override
	public Map<Integer, Integer> initLevelCostMap() {
		Map<Integer, Integer> probabilityCalculationDifficultyCost = new HashMap<>();
		probabilityCalculationDifficultyCost.put(1, 0);
		probabilityCalculationDifficultyCost.put(2, 10);
		probabilityCalculationDifficultyCost.put(3, 10);
		probabilityCalculationDifficultyCost.put(4, 10);
		probabilityCalculationDifficultyCost.put(5, 10);
		probabilityCalculationDifficultyCost.put(6, 10);
		probabilityCalculationDifficultyCost.put(7, 10);
		probabilityCalculationDifficultyCost.put(8, 10);
		probabilityCalculationDifficultyCost.put(9, 10);
		probabilityCalculationDifficultyCost.put(10, 10);
		return probabilityCalculationDifficultyCost;
	}

}
