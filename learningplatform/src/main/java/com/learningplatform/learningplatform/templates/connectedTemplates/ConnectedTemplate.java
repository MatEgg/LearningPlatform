package com.learningplatform.learningplatform.templates.connectedTemplates;

import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Template;

public abstract class ConnectedTemplate extends Template {
	
	public ConnectedTemplate(WordProblem wordProblem) {
		super(wordProblem);
	}
	
	public ConnectedTemplate() {
		super();
	}

	public abstract void callFinishTemplate();
	
	public void finishTemplate() {
		setContainingAttr();
		setQuestionText(constructQuestion());
		setQuestionElements();
		setRelevantAttributes();
		callSetTemplateType();
	}
}
