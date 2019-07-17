package com.learningplatform.learningplatform.difficulty.conceptualDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyCost;

import java.util.HashMap;
import java.util.Map;

public class ReplaceDifficultyCost extends DifficultyCost {

	/*
	 * Level 1: No replacements Level 2: 2 replacement Level 3: 3 replacements Level
	 * 4: 4 replacements Level 5: 5 replacements Level 6: 6 replacements Level 7: 7
	 * replacements Level 8: 8 replacements Level 9: 9 replacements Level 10: 10
	 * replacements
	 */

	public ReplaceDifficultyCost(int individualDifficultySettings) {
		super(individualDifficultySettings);
	}

	@Override
	public Map<Integer, Integer> initLevelCostMap() {
		Map<Integer, Integer> replaceDifficultyCostMap = new HashMap<>();
		replaceDifficultyCostMap.put(1, 0);
		replaceDifficultyCostMap.put(2, 10);
		replaceDifficultyCostMap.put(3, 10);
		replaceDifficultyCostMap.put(4, 10);
		replaceDifficultyCostMap.put(5, 10);
		replaceDifficultyCostMap.put(6, 10);
		replaceDifficultyCostMap.put(7, 10);
		replaceDifficultyCostMap.put(8, 10);
		replaceDifficultyCostMap.put(9, 10);
		replaceDifficultyCostMap.put(10, 10);
		return replaceDifficultyCostMap;
	}
}
