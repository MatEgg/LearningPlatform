package com.learningplatform.learningplatform.types;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.Utilities;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentUtils;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.utilities.QuestionTextHelper;

public class QuestionText {
	
	String questionWording;
	StringBuilder sb;
	Map<Integer, Attribute> attributeOccurences;
	Map<Integer, Attribute> attributeNumericalOccurences;
	Map<Integer, String> textOccurences;
	Map<Integer, Element> elementOccurences;
	String[] textCollection;
	int count = 0;

	public enum PositionType {
		START, NORMAL, PLURAL
	}

	public QuestionText() {
		sb = new StringBuilder();
		questionWording = sb.toString();
		textOccurences = new HashMap<>();
		attributeOccurences = new HashMap<>();
		attributeNumericalOccurences = new HashMap<>();
		elementOccurences = new HashMap<>();
	}

	public String getQuestionText() {
		questionWording = sb.toString();
		return questionWording;
	}

	public void setQuestionWording(String questionWording) {
		this.questionWording = questionWording;
	}

	public void addTextLine(String textLine) {
		textOccurences.put(count, textLine);
		count++;
	}

	public void addAttributeLine(Attribute attribute) {
		attributeOccurences.put(count, attribute);
		count++;
	}

	/**
	 * Add a line to the template that has an attribute in it.
	 * 
	 * @param attribute attribute that is part of the template addition
	 */
	public void addAttributeNumericalLine(Attribute attribute) {

		attributeNumericalOccurences.put(count, attribute);
		count++;
		addPlaceholder();
	}

	public void addPlaceholder() {
		addTextLine("");
	}

	/**
	 * Add a line to the template that has an attribute in it.
	 * 
	 * @param element      element that is part of the template addition
	 * @param positionType describes what position the element is at
	 */
	public void addElementLine(Element element, PositionType positionType) {
		if (positionType.equals(PositionType.START)) {
			element.setActiveString(element.getElementTextStart());
		} else if (positionType.equals(PositionType.NORMAL)) {
			element.setActiveString(element.getElementText());
		} else {
			element.setActiveString(element.getElementTextPlural());
		}
		elementOccurences.put(count, element);
		count++;
	}

	/**
	 * Constructs the template by using the collected text passages.
	 */
	public void fillStringBuilder() {
		textCollection = new String[textOccurences.size() + attributeOccurences.size() + elementOccurences.size()
				+ attributeNumericalOccurences.size()];

		for (Entry<Integer, Attribute> entry : attributeOccurences.entrySet()) {
			textCollection[entry.getKey()] = attributeOccurences.get(entry.getKey()).getName();
		}
		
		for (Entry<Integer, String> entry : textOccurences.entrySet()) {
			textCollection[entry.getKey()] = textOccurences.get(entry.getKey());
		}
		
		for (Entry<Integer, Element> entry : elementOccurences.entrySet()) {
			textCollection[entry.getKey()] = elementOccurences.get(entry.getKey()).getActiveString();
		}
		
		for (Entry<Integer, Attribute> entry : attributeNumericalOccurences.entrySet()) {
			Attribute tempAttr = attributeNumericalOccurences.get(entry.getKey());
			float multiplier = DifficultyAdjustmentUtils.roundNumber(tempAttr.getMultiplier(), Operators.MULTIPLICATION);
			textCollection[entry.getKey()] = QuestionTextHelper.fixFloatRepresentation(multiplier);
			if (tempAttr.getReplacementAttribute() != null) {
				textCollection[entry.getKey() + 1] = " " + tempAttr.getReplacementAttribute().getElement().getReplacementString();
			}
		}

		for (String str : textCollection) {
			sb.append(str);
		}
	}

	public Map<Integer, Attribute> getAttributeOccurences() {
		return attributeOccurences;
	}
}
