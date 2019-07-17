package com.learningplatform.learningplatform.difficulty.decimaldifficulty;

import java.util.HashMap;
import java.util.Map;

import com.learningplatform.learningplatform.difficulty.DifficultyCost;

public class DecimalEnableCost extends DifficultyCost {

	/*
	 * Level 1: No Decimal Objects Level 2: Decimal Objects allowed
	 */
	

	public DecimalEnableCost(int individualDifficultySettings) {
		super(individualDifficultySettings);
	}

	@Override
	public Map<Integer, Integer> initLevelCostMap() {
		Map<Integer, Integer> decimalEnableCostMap = new HashMap<>();
		decimalEnableCostMap.put(1, 0);
		decimalEnableCostMap.put(2, 30);
		return decimalEnableCostMap;
	}

}
