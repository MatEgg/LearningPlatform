package com.learningplatform.learningplatform.tasks;

import java.util.List;

import com.learningplatform.learningplatform.templates.Template;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.types.ConceptEnum;

public interface TaskInterface {

	public String getQuestionText();

	public List<Element> getQuestionElements();

	public void createTask();
}
