package com.learningplatform.learningplatform.difficulty.operationalDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyCost;

import java.util.HashMap;
import java.util.Map;

public class DecimalCountDifficultyCost extends DifficultyCost {

	/*
	 * Level 1: No Decimals Level 2: 1 Decimal Level 3: 2 Decimals Level 4: 3
	 * Decimals
	 */
	public DecimalCountDifficultyCost(int individualDifficultySettings) {
		super(individualDifficultySettings);
	}

	@Override
	public Map<Integer, Integer> initLevelCostMap() {
		Map<Integer, Integer> decimalCountCostMap = new HashMap<>();
		decimalCountCostMap.put(1, 0);
		decimalCountCostMap.put(2, 30);
		decimalCountCostMap.put(3, 20);
		decimalCountCostMap.put(4, 20);
		return decimalCountCostMap;
	}

}
