package com.learningplatform.learningplatform.templates.baseTemplates;

import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Template;

public abstract class BaseTemplate extends Template {

	public BaseTemplate(WordProblem wordProblem) {
		super(wordProblem);
	}

	public void finishTemplate() {
		setQuestionElements();
		setContainingAttr();
		setQuestionText(constructQuestion());
		setRelevantAttributes();
		callSetTemplateType();
	}
	
}
