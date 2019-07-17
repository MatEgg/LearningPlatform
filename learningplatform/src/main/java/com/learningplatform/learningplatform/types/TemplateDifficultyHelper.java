package com.learningplatform.learningplatform.types;

public class TemplateDifficultyHelper {

	private int difficultyLevel;
	private boolean levelScalable;

	public TemplateDifficultyHelper(int difficultyLevel, boolean levelScalable) {
		this.difficultyLevel = difficultyLevel;
		this.levelScalable = levelScalable;
	}

	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public boolean isLevelScalable() {
		return levelScalable;
	}

	public void setLevelScalable(boolean levelScalable) {
		this.levelScalable = levelScalable;
	}

}
