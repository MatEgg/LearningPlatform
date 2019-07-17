package com.learningplatform.learningplatform.difficulty.conceptDifficulty;

import com.learningplatform.learningplatform.difficulty.DifficultyCost;

import java.util.HashMap;
import java.util.Map;


public class MeasurementDifficultyCost extends DifficultyCost {
	
	public MeasurementDifficultyCost(int individualDifficultySettings) {
		super(individualDifficultySettings);
	}

	@Override
	public Map<Integer, Integer> initLevelCostMap() {
		Map<Integer, Integer> measurementDifficultyCostMap = new HashMap<>();
		measurementDifficultyCostMap.put(1, 0);
		measurementDifficultyCostMap.put(2, 10);
		measurementDifficultyCostMap.put(3, 10);
		measurementDifficultyCostMap.put(4, 10);
		measurementDifficultyCostMap.put(5, 10);
		measurementDifficultyCostMap.put(6, 10);
		measurementDifficultyCostMap.put(7, 10);
		measurementDifficultyCostMap.put(8, 10);
		measurementDifficultyCostMap.put(9, 10);
		measurementDifficultyCostMap.put(10, 10);
		return measurementDifficultyCostMap;
	}

}
