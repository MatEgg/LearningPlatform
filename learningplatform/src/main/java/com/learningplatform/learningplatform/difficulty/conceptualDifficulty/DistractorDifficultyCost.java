package com.learningplatform.learningplatform.difficulty.conceptualDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyCost;

import java.util.HashMap;
import java.util.Map;

public class DistractorDifficultyCost extends DifficultyCost {

	/*
	 * Level 1: No replacements Level 2: 2 replacement Level 3: 3 replacements Level
	 * 4: 4 replacements Level 5: 5 replacements Level 6: 6 replacements Level 7: 7
	 * replacements Level 8: 8 replacements Level 9: 9 replacements Level 10: 10
	 * replacements
	 */


	public DistractorDifficultyCost(int individualDifficultySettings) {
		super(individualDifficultySettings);
	}

	@Override
	public Map<Integer, Integer> initLevelCostMap() {
		Map<Integer, Integer> distractorDifficultyCostMap = new HashMap<>();
		distractorDifficultyCostMap.put(1, 0);
		distractorDifficultyCostMap.put(2, 10);
		distractorDifficultyCostMap.put(3, 10);
		distractorDifficultyCostMap.put(4, 10);
		distractorDifficultyCostMap.put(5, 10);
		distractorDifficultyCostMap.put(6, 10);
		distractorDifficultyCostMap.put(7, 10);
		distractorDifficultyCostMap.put(8, 10);
		distractorDifficultyCostMap.put(9, 10);
		distractorDifficultyCostMap.put(10, 10);
		return distractorDifficultyCostMap;
	}
}
