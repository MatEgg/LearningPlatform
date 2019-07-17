package com.learningplatform.learningplatform.difficulty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

/**
 * Class to calculate level up costs.
 *
 */
public abstract class DifficultyInt {

	protected List<DifficultyCost> potentialUpgrades;
	protected List<DifficultyCost> difficultyCosts;
	protected Map<DifficultyCost, Integer> individualDifficultySettings;

	public DifficultyInt() {
		potentialUpgrades = new ArrayList<>();
		difficultyCosts = new ArrayList<>();
		individualDifficultySettings = new HashMap<>();
	}

	private int difficulty = 0;
	protected int currentCost = 0;

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public abstract void determineDifficultySettings();

	protected List<DifficultyCost> findAllOptionsUnder(int ceiling, List<DifficultyCost> difficultyCosts,
			int currentCost) {

		potentialUpgrades = new ArrayList<>();

		for (DifficultyCost difficultyCost : difficultyCosts) {
			int upgradeCost = difficultyCost.getUpgradeCost();
			if (upgradeCost != -1 && upgradeCost <= ceiling - currentCost) {
				potentialUpgrades.add(difficultyCost);
			}
		}

		return potentialUpgrades;
	}

	/**
	 * levels up the difficulty of a skill. Looks for potential upgrades and then ups the accumulated cost.
	 * @return total cost of upgrading the skill
	 */
	public int levelUpDifficulty() {
		potentialUpgrades = findAllOptionsUnder(getDifficulty(), difficultyCosts, currentCost);
		int cost = currentCost;

		while (potentialUpgrades.size() != 0) {
			int nextUpgrade = UtilitiesHelper.getRandom().nextInt(potentialUpgrades.size());
			potentialUpgrades.get(nextUpgrade).upgradeLevel();
			cost = cost + potentialUpgrades.get(nextUpgrade).getCostOfCurrentLevel();
			potentialUpgrades = findAllOptionsUnder(getDifficulty(), difficultyCosts, cost);
		}
		float maximumCost = getTotalLevelCosts();
		return (int) (cost / maximumCost * 100);
	}

	
	/**
	 * Calculates the level that can be achieved with a certain amount of capacity.
	 * @param diffCost Difficulty Cost object that stores the upgrade information
	 * @param capacity maximum cost that can be used for upgrading
	 * @return
	 */
	public int getLevelByCost(DifficultyCost diffCost, int capacity) {
		int usedCost = 0;
		for (int level = 1; usedCost <= capacity; level++) {
			if (level + 1 > diffCost.getLevelCost().size()) {
				return level;
			}
			usedCost = usedCost + diffCost.getCostofLevel(level + 1);
			if (usedCost > capacity) {
				return level;
			}
		}
		return 1;
	}

	
	/**
	 * levels up the skill based on the individual difficulty settings.
	 * @return the actual cost that was used for upgrading
	 */
	public int levelUpDifficultyIndividually() {
		float usedCost = 0;
		for (DifficultyCost diffCost : difficultyCosts) {
			diffCost.setLevel(diffCost.getIndividualDifficultySettings());
			usedCost = usedCost + diffCost.getTotalCost(diffCost.getLevel());
		}

		float maximumCost = getTotalLevelCosts();
		return (int) (usedCost / maximumCost * 100);

	}

	/**
	 * calculate the total cost of levels.
	 * @return total cost of all levels combined
	 */
	public int getTotalLevelCosts() {
		int total = 0;
		for (DifficultyCost diffCost : difficultyCosts) {
			total = total + diffCost.getTotalCost(diffCost.getLevelCost().size());
		}
		return total;
	}

	/**
	 * determines the total cost of upgrading.
	 * @return total cost of upgrading
	 */
	public int determineManualDifficulty() {

		float usedCost = 0;
		for (DifficultyCost diffCost : difficultyCosts) {
			usedCost = usedCost + diffCost.getTotalCost(diffCost.getLevel());
		}
		float maximumCost = getTotalLevelCosts();
		return (int) (usedCost / maximumCost * 100);
	}

}
