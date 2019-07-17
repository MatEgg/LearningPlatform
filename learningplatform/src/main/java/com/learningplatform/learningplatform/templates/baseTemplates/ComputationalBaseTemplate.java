package com.learningplatform.learningplatform.templates.baseTemplates;

import com.learningplatform.learningplatform.tasks.WordProblem;

public abstract class ComputationalBaseTemplate extends BaseTemplate {

	public ComputationalBaseTemplate(WordProblem wordProblem) {
		super(wordProblem);
	}

	@SuppressWarnings("unchecked")
	public static <T extends ComputationalBaseTemplate> T findFittingBaseTemplate(WordProblem wordProblem) {
		return (T) new StoreProductAnimalTemplate(wordProblem);
	}

}
