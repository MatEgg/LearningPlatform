package com.learningplatform.learningplatform.difficulty.conceptualDifficulty;


import com.learningplatform.learningplatform.difficulty.DifficultyCost;


import java.util.HashMap;
import java.util.Map;

public class DifferentConceptsDifficultyCost extends DifficultyCost {

	/*
	 * Level 1: No replacements Level 2: 2 replacement Level 3: 3 replacements Level
	 * 4: 4 replacements Level 5: 5 replacements Level 6: 6 replacements Level 7: 7
	 * replacements Level 8: 8 replacements Level 9: 9 replacements Level 10: 10
	 * replacements
	 */

	public DifferentConceptsDifficultyCost(int individualDifficultySettings) {
		super(individualDifficultySettings);
	}

	@Override
	public Map<Integer, Integer> initLevelCostMap() {
		Map<Integer, Integer> differentConceptsDifficultyCostMap = new HashMap<>();
		differentConceptsDifficultyCostMap.put(1, 0);
		differentConceptsDifficultyCostMap.put(2, 10);
		differentConceptsDifficultyCostMap.put(3, 10);
		differentConceptsDifficultyCostMap.put(4, 10);
		differentConceptsDifficultyCostMap.put(5, 10);
		differentConceptsDifficultyCostMap.put(6, 10);
		differentConceptsDifficultyCostMap.put(7, 10);
		differentConceptsDifficultyCostMap.put(8, 10);
		differentConceptsDifficultyCostMap.put(9, 10);
		differentConceptsDifficultyCostMap.put(10, 10);
		return differentConceptsDifficultyCostMap;
	}
}
