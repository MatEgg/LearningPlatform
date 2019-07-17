package com.learningplatform.learningplatform.templates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.ListMultimap;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.TemplateHandler.Templates;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.types.Question;
import com.learningplatform.learningplatform.types.QuestionText;

public abstract class Template {

	@JsonIgnore
	protected List<Element> elems;
	List<QuestionAttribute> containingAttr;
	List<List<QuestionAttribute>> requiredAttr;
	protected StringBuilder sb;
	List<Question> questions;
	protected QuestionText questionText;
	protected WordProblem wordProblem;
	Templates templateType;

	public Template(WordProblem wordProblem) {
		elems = new ArrayList<>();
		this.wordProblem = wordProblem;
		questions = new ArrayList<>();
		sb = new StringBuilder();
		setRequiredAttr();
	}
	
	public Template() {
		elems = new ArrayList<>();
		questions = new ArrayList<>();
		sb = new StringBuilder();
		setRequiredAttr();
	}

	public void addQuestionElements(List<Element> elements) {
		elems = elements;
	}

	public List<Element> getQuestionElements() {
		return elems;
	}

	/**
	 * adds all the attributes contained in individual templates together
	 */
	public void addContainingAttr() {
		List<QuestionAttribute> questionAttributes = new ArrayList<>();

		for (Element element : getQuestionElements()) {
			for (QuestionAttribute questionAttribute : element.getQuestionAttributes()) {
				questionAttributes.add(questionAttribute);

			}

		}

		containingAttr = questionAttributes;
	}

	/**
	 * Create map that stores all attributes with their type in a multimap
	 * 
	 * @param attributes List of attributes in a template
	 */
	public void addRelevantAttributes(List<Attribute> attributes) {
		ListMultimap<AttributeType, Attribute> relevantAttributeMap = WordProblem.getInstance().getRelevantAttributes();

		for (Attribute attribute : attributes) {
			relevantAttributeMap.put(attribute.getAttributeType(), attribute);
		}
	}

	public void addRequiredAttributes(List<List<QuestionAttribute>> requiredAttributes) {
		requiredAttr = requiredAttributes;
	}

	public abstract void callFinishTemplate();

	public abstract void setContainingAttr();

	public void setQuestionElements() {
		// do nothing if not overriden
	}

	public void setRequiredAttr() {
		// do nothing if not overriden
	}

	public void setRelevantAttributes() {
		// do nothing if not overriden
	}

	public void callSetTemplateType() {
		// do nothing if not overriden
	}

	public abstract QuestionText constructQuestion();

	public void addQuestion(Question question) {
		questions.add(question);
	}

	public List<Question> getTemplateQuestions() {
		return questions;
	}

	public List<QuestionAttribute> getContainingAttributes() {
		return containingAttr;
	}

	public List<List<QuestionAttribute>> getRequiredAttributes() {
		return requiredAttr;
	}

	public StringBuilder getStringBuilder() {
		return sb;
	}

	public QuestionText getQuestionText() {
		return questionText;
	}

	public void setQuestionText(QuestionText questionText) {
		this.questionText = questionText;
	}

	public WordProblem getWordProblem() {
		return wordProblem;
	}

	public void setWordProblem(WordProblem wordProblem) {
		this.wordProblem = wordProblem;
	}

	public Templates getTemplateType() {
		return templateType;
	}

	public void setTemplateType(Templates templateType) {
		this.templateType = templateType;
	}

}
