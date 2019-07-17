package com.learningplatform.learningplatform.guiObjects;

import java.util.ArrayList;
import java.util.List;

import com.learningplatform.learningplatform.tasks.WordProblem;

/**
 * Gui representation of a word problem containing the question text, templates
 * and elements.
 *
 */
public class WordproblemGui {

	String questionText;

	List<ElementGui> elements;
	List<TemplateGui> templates;
	List<AttributeGui> attributes;
	List<QuestionGui> questions;
	Boolean allVisible = true;

	public Boolean getAllVisible() {
		setMakeAllVisible();
		return allVisible;
	}

	public void setAllVisible(Boolean allVisible) {
		this.allVisible = allVisible;
	}

	WordProblem wordProblem;

	public WordproblemGui(WordProblem wordproblem) {

		this.wordProblem = wordproblem;
		this.questionText = wordproblem.getQuestionText();
		this.templates = wordproblem.getTemplateList();
		this.questions = wordproblem.getQuestionList();

		fillElements();
		fillAttributes();

	}

	public void setMakeAllVisible() {
		for (ElementGui elementGui : elements) {
			elementGui.setIsClickVisible(!allVisible);
		}
		allVisible = !allVisible;
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

	private void fillElements() {
		elements = new ArrayList<>();
		for (TemplateGui templateGui : wordProblem.getTemplateList()) {
			for (ElementGui elementGui : templateGui.getElements()) {
				elements.add(elementGui);
			}
		}
	}

	private void fillAttributes() {
		attributes = new ArrayList<>();
		for (ElementGui elementGui : elements) {
			for (AttributeGui attributeGui : elementGui.getAttributes()) {
				attributes.add(attributeGui);
			}
		}
	}

	public List<TemplateGui> getTemplates() {
		return templates;
	}

	public void setTemplates(List<TemplateGui> templates) {
		this.templates = templates;
	}

	public List<AttributeGui> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<AttributeGui> attributes) {
		this.attributes = attributes;
	}

	public void setElements(List<ElementGui> elements) {
		this.elements = elements;
	}

	public List<QuestionGui> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionGui> questions) {
		this.questions = questions;
	}

}
