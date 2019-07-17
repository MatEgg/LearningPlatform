package com.learningplatform.learningplatform.user;

import com.learningplatform.learningplatform.tasks.WordProblem;

public class UserHandler {

	WordProblem wordProblem;
	static private UserHandler userHandlerInstance;
	private String currentLoggedUser = "Schlecht";

	/**
	 * Lazy instantiation
	 * @return UserHandler instance
	 */
	public static UserHandler getInstance() {
		if (userHandlerInstance == null) {
			userHandlerInstance = new UserHandler();
			return userHandlerInstance;
		} else {
			return userHandlerInstance;
		}
	}

	public UserHandler() {
		wordProblem = WordProblem.getInstance();
	}

	public String getCurrentLoggedUser() {
		return currentLoggedUser;
	}

	public void setCurrentLoggedUser(String currentLoggedUser) {
		this.currentLoggedUser = currentLoggedUser;
	}

}
