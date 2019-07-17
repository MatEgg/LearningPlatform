package com.learningplatform.learningplatform.difficulty.conceptDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyCost;

import java.util.HashMap;
import java.util.Map;


public class PercentCalculationDifficultyCost extends DifficultyCost {

	
	public PercentCalculationDifficultyCost(int individualDifficultySettings) {
		super(individualDifficultySettings);
	}

	@Override
	public Map<Integer, Integer> initLevelCostMap() {
		Map<Integer, Integer> percentCalculationDifficultyCost = new HashMap<>();
		percentCalculationDifficultyCost.put(1, 0);
		percentCalculationDifficultyCost.put(2, 10);
		percentCalculationDifficultyCost.put(3, 10);
		percentCalculationDifficultyCost.put(4, 10);
		percentCalculationDifficultyCost.put(5, 10);
		percentCalculationDifficultyCost.put(6, 10);
		percentCalculationDifficultyCost.put(7, 10);
		percentCalculationDifficultyCost.put(8, 10);
		percentCalculationDifficultyCost.put(9, 10);
		percentCalculationDifficultyCost.put(10, 10);
		return percentCalculationDifficultyCost;
	}

}
