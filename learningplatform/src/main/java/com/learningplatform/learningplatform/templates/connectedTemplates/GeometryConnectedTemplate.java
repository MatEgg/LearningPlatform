package com.learningplatform.learningplatform.templates.connectedTemplates;

import com.learningplatform.learningplatform.tasks.WordProblem;

public abstract class GeometryConnectedTemplate extends ConnectedTemplate {

	public GeometryConnectedTemplate(WordProblem wordProblem) {
		super(wordProblem);
	}
	
	public GeometryConnectedTemplate() {
		super();
	}

	@SuppressWarnings("unchecked")
	public static <T extends GeometryConnectedTemplate> T findFittingConnectedTemplate(WordProblem wordProblem) {
		return (T) new CalculateAreaOfStore(wordProblem);
	}
	
}
