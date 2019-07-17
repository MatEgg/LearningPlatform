package com.learningplatform.learningplatform.difficulty.conceptualDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyCost;


import java.util.HashMap;
import java.util.Map;

public class QuestionDifficultyDifficultyCost extends DifficultyCost {

	/*
	 * Level 1: No replacements Level 2: 2 replacement Level 3: 3 replacements Level
	 * 4: 4 replacements Level 5: 5 replacements Level 6: 6 replacements Level 7: 7
	 * replacements Level 8: 8 replacements Level 9: 9 replacements Level 10: 10
	 * replacements
	 */

	public QuestionDifficultyDifficultyCost(int individualDifficultySettings) {
		super(individualDifficultySettings);
	}

	@Override
	public Map<Integer, Integer> initLevelCostMap() {
		Map<Integer, Integer> questionDifficultyCostMap = new HashMap<>();
		questionDifficultyCostMap.put(1, 0);
		questionDifficultyCostMap.put(2, 10);
		questionDifficultyCostMap.put(3, 10);
		questionDifficultyCostMap.put(4, 10);
		questionDifficultyCostMap.put(5, 10);
		questionDifficultyCostMap.put(6, 10);
		questionDifficultyCostMap.put(7, 10);
		questionDifficultyCostMap.put(8, 10);
		questionDifficultyCostMap.put(9, 10);
		questionDifficultyCostMap.put(10, 10);
		return questionDifficultyCostMap;
	}
}
