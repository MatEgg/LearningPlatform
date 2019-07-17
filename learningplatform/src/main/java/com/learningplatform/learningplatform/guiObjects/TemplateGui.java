package com.learningplatform.learningplatform.guiObjects;

import java.util.ArrayList;
import java.util.List;

import com.learningplatform.learningplatform.templates.Template;
import com.learningplatform.learningplatform.templates.Elements.Element;

/**
 * Gui representation of a template containing elements and attributes.
 *
 */
public class TemplateGui {

	String questionText;
	List<ElementGui> elements;

	public TemplateGui(String questionText, Template template) {
		elements = new ArrayList<>();
		for (Element element : template.getQuestionElements()) {
			if (!element.getAlreadyInList()) {
				elements.add(new ElementGui(element.getId(), element.getElementText(), element));
				element.setAlreadyInList(true);
				template.getWordProblem().elementID++;
			}
		}
		this.questionText = questionText;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public List<ElementGui> getElements() {
		return elements;
	}

}
