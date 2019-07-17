package com.learningplatform.learningplatform.difficulty;

import java.util.Map;

/**
 * Represents the cost of upgrading a skill and what each level entails.
 *
 */
public abstract class DifficultyCost {

	private int level = 1;

	private Map<Integer, Integer> levelCost;
	private int individualDifficultySettings;

	public DifficultyCost(int individualDifficultySettings) {
		levelCost = initLevelCostMap();
		this.individualDifficultySettings = individualDifficultySettings;
	}

	public int getLevel() {
		return level;
	}

	/** 
	 * Sets the level to 1 if it is 0.
	 * @param level set level to
	 */
	public void setLevel(int level) {
		if (level == 0) {
			this.level = 1;
		} else {
			this.level = level;
		}
	}

	public void upgradeLevel() {
		level++;
	}

	/** 
	 * returns the upgrade cost of a higher level.
	 * @return upgrade cost
	 */
	public int getUpgradeCost() {
		if (level == levelCost.size()) {
			return -1;
		} else {
			return levelCost.get(level + 1);
		}
	}

	/** 
	 * returns the total upgrade cost for a certain level.
	 * @param level the level of a skill
	 * @return total cost
	 */
	public int getTotalCost(int level) {
		int cost = 0;
		for (int i = 1; i <= level; i++) {
			cost = cost + levelCost.get(i);
		}
		return cost;
	}

	public Map<Integer, Integer> getLevelCost() {
		return levelCost;
	}

	public int getCostofLevel(int level) {
		return levelCost.get(level);
	}

	public int getCostOfCurrentLevel() {
		return levelCost.get(level);
	}

	public void setLevelCost(Map<Integer, Integer> levelCost) {
		this.levelCost = levelCost;
	}

	public int getIndividualDifficultySettings() {
		return individualDifficultySettings;
	}

	public void setIndividualDifficultySettings(int individualDifficultySettings) {
		this.individualDifficultySettings = individualDifficultySettings;
	}

	public abstract Map<Integer, Integer> initLevelCostMap();

}
