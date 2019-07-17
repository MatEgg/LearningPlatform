package com.learningplatform.learningplatform.difficulty;

import com.learningplatform.learningplatform.tasks.WordProblem;

public class DifficultyHandler {

	private static DifficultyHandler difficultyHandlerInstance;
	private static DifficultyCalculated difficulty;
	WordProblem wordProblem;

	public DifficultyHandler() {
		wordProblem = WordProblem.getInstance();
	}

	/**
	 * Lazy instantiation.
	 * 
	 * @return DifficultyHandler instance
	 */
	public static DifficultyHandler getInstance() {
		if (difficultyHandlerInstance == null) {
			difficultyHandlerInstance = getNewInstance();
			return difficultyHandlerInstance;
		} else {
			return difficultyHandlerInstance;
		}
	}

	public static DifficultyHandler getNewInstance() {
		difficultyHandlerInstance = new DifficultyHandler();
		difficulty = new DifficultyCalculated(WordProblem.getInstance().getUser(),
				WordProblem.getInstance().getSettings());
		return difficultyHandlerInstance;
	}

	public DifficultyCalculated getDifficulty() {
		return difficulty;
	}

	public static void setDifficulty(DifficultyCalculated difficulty) {
		DifficultyHandler.difficulty = difficulty;
	}
}
