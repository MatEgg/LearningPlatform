package com.learningplatform.learningplatform.difficulty.operationalDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyCost;

import java.util.HashMap;
import java.util.Map;

public class NumberTypeDifficultyCost extends DifficultyCost {

	/* Level 1: Return 1
	 * Level 2: Dividable by 5
	 * Level 3: Dividable by 2
	 * Level 4: Not dividable by 5 or 2
	 */
	
	public NumberTypeDifficultyCost(int individualDifficultySettings) {
		super(individualDifficultySettings);
	}

	@Override
	public Map<Integer, Integer> initLevelCostMap() {
		Map<Integer, Integer> numberTypeCostMap = new HashMap<>();
		numberTypeCostMap.put(1, 0);
		numberTypeCostMap.put(2, 10);
		numberTypeCostMap.put(3, 30);
		numberTypeCostMap.put(4, 40);
		return numberTypeCostMap;
	}

}
