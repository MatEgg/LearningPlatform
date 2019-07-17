package com.learningplatform.learningplatform.difficulty.operationalDifficulty;

import java.util.HashMap;
import java.util.Map;

import com.learningplatform.learningplatform.difficulty.DifficultyCost;

public class DigitCountDifficultyCost extends DifficultyCost {

	/*
	 * Level 1: 1 Digit 1-2 Level 2: 1 Digit 3-5 Level 3: 2 Digit 6-10 Level 4: 2
	 * Digit 11-15 Level 5: 2 Digit 16-20 Level 6: 3 Digit 21-30 Level 7: 3 Digit
	 * 31-40 Level 8: 3 Digit 41-50 Level 9: 4 Digit 51-99 Level 10: 4 Digit
	 * 100-1000
	 */

	public DigitCountDifficultyCost(int individualDifficultySettings) {
		super(individualDifficultySettings);
	}

	@Override
	public Map<Integer, Integer> initLevelCostMap() {
		Map<Integer, Integer> digitCountCostMap = new HashMap<>();
		digitCountCostMap.put(1, 0);
		digitCountCostMap.put(2, 10);
		digitCountCostMap.put(3, 10);
		digitCountCostMap.put(4, 10);
		digitCountCostMap.put(5, 10);
		digitCountCostMap.put(6, 10);
		digitCountCostMap.put(7, 10);
		digitCountCostMap.put(8, 10);
		digitCountCostMap.put(9, 10);
		digitCountCostMap.put(10, 10);
		return digitCountCostMap;
	}

	/**
	 * returns the lower bound of possible numbers based on the level of the skill.
	 * 
	 * @param level level of the skill
	 * @return lower bound
	 */
	public int getLowerBoundValueByLevel(int level) {
		switch (level) {
		case 1:
			return 1;
		case 2:
			return 3;
		case 3:
			return 6;
		case 4:
			return 11;
		case 5:
			return 16;
		case 6:
			return 21;
		case 7:
			return 31;
		case 8:
			return 41;
		case 9:
			return 51;
		case 10:
			return 100;
		default:
			return 1;
		}
	}

	/**
	 * returns the higher bound of possible numbers based on the level of the skill.
	 * 
	 * @param level of the skill
	 * @return higher bound
	 */
	public int getHigherBoundValueByLevel(int level) {
		switch (level) {
		case 1:
			return 2;
		case 2:
			return 5;
		case 3:
			return 10;
		case 4:
			return 15;
		case 5:
			return 20;
		case 6:
			return 30;
		case 7:
			return 40;
		case 8:
			return 50;
		case 9:
			return 99;
		case 10:
			return 1000;
		default:
			return 1;
		}
	}
}
