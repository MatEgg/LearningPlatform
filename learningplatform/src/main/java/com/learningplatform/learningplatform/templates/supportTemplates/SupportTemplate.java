package com.learningplatform.learningplatform.templates.supportTemplates;

import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Template;

public abstract class SupportTemplate extends Template {

	public SupportTemplate(WordProblem wordProblem) {
		super(wordProblem);
	}
	
	public SupportTemplate() {
		super();
	}

	public void constructTemplateQuestion() {
		// do nothing if not overriden
	}

	public void finishTemplate() {
		setQuestionElements();
		setContainingAttr();
		setQuestionText(constructQuestion());
		setRelevantAttributes();
		constructTemplateQuestion();
		callSetTemplateType();
	}

}
