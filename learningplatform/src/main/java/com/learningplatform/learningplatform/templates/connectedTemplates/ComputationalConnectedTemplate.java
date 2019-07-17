package com.learningplatform.learningplatform.templates.connectedTemplates;

import com.learningplatform.learningplatform.tasks.WordProblem;

public abstract class ComputationalConnectedTemplate extends ConnectedTemplate {

	public ComputationalConnectedTemplate(WordProblem wordProblem) {
		super(wordProblem);
	}

	@SuppressWarnings("unchecked")
	public static <T extends ComputationalConnectedTemplate> T findFittingConnectedTemplate(WordProblem wordProblem) {
		return (T) new ProductTimeUntilDepletion(wordProblem);
	}
	
}
