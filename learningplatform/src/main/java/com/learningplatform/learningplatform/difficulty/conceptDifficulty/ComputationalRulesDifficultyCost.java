package com.learningplatform.learningplatform.difficulty.conceptDifficulty;

import java.util.HashMap;
import java.util.Map;

import com.learningplatform.learningplatform.difficulty.DifficultyCost;

public class ComputationalRulesDifficultyCost extends DifficultyCost {

	public ComputationalRulesDifficultyCost(int individualDifficultySettings) {
		super(individualDifficultySettings);
	}

	@Override
	public Map<Integer, Integer> initLevelCostMap() {
		Map<Integer, Integer> computationalRulesDifficultyCostMap = new HashMap<>();
		computationalRulesDifficultyCostMap.put(1, 0);
		computationalRulesDifficultyCostMap.put(2, 10);
		computationalRulesDifficultyCostMap.put(3, 10);
		computationalRulesDifficultyCostMap.put(4, 10);
		computationalRulesDifficultyCostMap.put(5, 10);
		computationalRulesDifficultyCostMap.put(6, 10);
		computationalRulesDifficultyCostMap.put(7, 10);
		computationalRulesDifficultyCostMap.put(8, 10);
		computationalRulesDifficultyCostMap.put(9, 10);
		computationalRulesDifficultyCostMap.put(10, 10);
		return computationalRulesDifficultyCostMap;
	}

}
