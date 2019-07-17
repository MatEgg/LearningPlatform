package com.learningplatform.learningplatform.templates.supportTemplates;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.ListMultimap;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeConcept;
import com.learningplatform.learningplatform.attributes.FormularUtils.Formula;
import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentUtils;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.TemplateHandler.Templates;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.FieldElement;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.types.QuestionText.PositionType;
import com.learningplatform.learningplatform.types.QuestionText;

import learningplatform.learningplatform.Calculation.AttributeCalculation;
import learningplatform.learningplatform.Calculation.ValidCalculation;

public class ReplaceValueByOther extends SupportTemplate {
	Attribute attribute;
	Attribute replacementAttribute;
	List<Attribute> relevantNumericalAttributes;
	Random random;
	List<Element> questionElements;
	FieldElement fieldElement;
	List<Attribute> attributes;
	AttributeHandler ah;
	ListMultimap<QuestionAttribute, Element> elementsMap;

	private static final EnumMap<Templates, Boolean> templateCompatibilityMap;

	static {
		templateCompatibilityMap = new EnumMap<>(Templates.class);

	}

	public ReplaceValueByOther(WordProblem wordProblem) {
		super(wordProblem);
		ah = new AttributeHandler();
		questionElements = new ArrayList<>();
		elementsMap = wordProblem.getElementsMap();
		attributes = new ArrayList<>();
		questionText = getQuestionText();
		this.wordProblem = wordProblem;
		random = new Random();
		attribute = findAttributeToReplace();
	}

	public ReplaceValueByOther(StoreElement storeElement) {
		super();
		ah = new AttributeHandler();
		questionElements = new ArrayList<>();
		attributes = new ArrayList<>();
		questionText = getQuestionText();
		random = new Random();
		attribute = storeElement.getChosenAttribute();
	}

	@Override
	public void callFinishTemplate() {
		finishTemplate();
	}

	@Override
	public void setContainingAttr() {
		addContainingAttr();
	}

	@Override
	public void setRequiredAttr() {
		List<List<QuestionAttribute>> requiredAttributesList = new ArrayList<>();
		List<QuestionAttribute> requiredAttributes = new ArrayList<>();
		requiredAttributes.add(QuestionAttribute.AREA);
		requiredAttributesList.add(requiredAttributes);

		addRequiredAttributes(requiredAttributesList);

	}

	@Override
	public QuestionText constructQuestion() {

		QuestionText dummy = new QuestionText();
		dummy.addTextLine("dummy");

		if (attribute == null) {
			return dummy;
		}
		questionText = attribute.getElement().getTemplate().getQuestionText();

		Boolean isReplacement = false;
		while (attribute.getReplacementAttribute() != null) {
			attribute = attribute.getReplacementAttribute();
			isReplacement = true;
		}

		if (attribute.getReplacementAttribute() == null) {
			boolean replaceable = true;
			if (attribute.getIsReplacement()) {
				replaceable = DifficultyAdjustmentUtils.checkIfReplaceable(attribute);
			}
			if (replaceable) {
				replacementAttribute = ah.replaceAttributWithRandomType(attribute.getElement(), attribute,
						isReplacement);
				if (replacementAttribute != null) {
					replace(isReplacement);
					addRelevantAttributes(attributes);
					constructQuestionText();
				}
			}

		}
		return dummy;
	}

	/**
	 * Method to first find an adequate replacement for an attribute and then
	 * replace it
	 * 
	 * @param isReplacement Boolean if the current attribute is already a
	 *                      replacement
	 */
	public void replace(Boolean isReplacement) {
		attribute.setReplacementAttribute(replacementAttribute);

		attribute.setIsDistractor(false);
		attribute.getReplacementAttribute().setIsDistractor(false);
		attribute.getReplacementAttribute().setIsReplacement(true);
		attribute.getElement().getAttributeByType(attribute.getAttributeType()).setIsDistractor(false);
		attribute.getReplacementAttribute().getElement()
				.getAttributeByType(attribute.getReplacementAttribute().getAttributeType()).setIsDistractor(false);

		float newRoundedMultiplier = getNewRoundedMultiplier(attribute.getReplacementAttribute().getMultiplier());
		attribute.setMultiplier(newRoundedMultiplier);
		attribute.getElement().getTemplate().getQuestionElements()
				.add(attribute.getReplacementAttribute().getElement());

		attributes.add(attribute.getReplacementAttribute());

		ah.changeVisibility(attribute.getElement().getAttributeByType(attribute.getAttributeType()), false);

		addNewAttributeCalculation(newRoundedMultiplier);

	}

	/**
	 * Once an attribute is replaced, add a new attribute calculation.
	 * 
	 * @param newRoundedMultiplier the new desired rounded multiplier
	 */
	public void addNewAttributeCalculation(float newRoundedMultiplier) {
		AttributeCalculation attributeCalculation = new AttributeCalculation(attribute.getReplacementAttribute(),
				newRoundedMultiplier, Operators.MULTIPLICATION);
		attributeCalculation.setCorrectRoundedAnswer(
				attribute.getReplacementAttribute().getNumericalValue() * newRoundedMultiplier);
		attributeCalculation.getCalculationInformation()
				.setAttributeConcept(AttributeConcept.COMPUTATIONAL_RULES_SKILL);
		attribute.setAttributeCalculation(attributeCalculation);

		AttributeCalculation attributeCalculation2 = new AttributeCalculation(attribute.getReplacementAttribute(),
				newRoundedMultiplier, Operators.MULTIPLICATION);
		attributeCalculation2.setCorrectRoundedAnswer(
				attribute.getReplacementAttribute().getNumericalValue() * newRoundedMultiplier);
		attributeCalculation2.getCalculationInformation()
				.setAttributeConcept(AttributeConcept.COMPUTATIONAL_RULES_SKILL);
		attribute.getElement().getAttributeByType(attribute.getAttributeType())
				.setAttributeCalculation(attributeCalculation2);

		Float[] attributeValues = new Float[] { attribute.getReplacementAttribute().getNumericalValue() };
		attribute.getValidCalculations().add(new ValidCalculation(attributeValues,
				attribute.getReplacementAttribute().getAttributeType(), Formula.REPLACEMENT, Operators.MULTIPLICATION));
		attribute.getElement().getAttributeByType(attribute.getAttributeType()).getValidCalculations()
				.add(new ValidCalculation(attributeValues, attribute.getReplacementAttribute().getAttributeType(),
						Formula.REPLACEMENT, Operators.MULTIPLICATION));
	}

	private float getNewRoundedMultiplier(float multiplier) {
		return DifficultyAdjustmentUtils.roundNumber(multiplier, Operators.MULTIPLICATION);
	}

	public void constructQuestionText() {
		questionText = attribute.getElement().getTemplate().getQuestionText();

		questionText.addTextLine(" ");
		questionText.addElementLine(attribute.getElement(), PositionType.START);
		questionText.addTextLine(" hat eine ");
		questionText.addAttributeLine(attribute);
		questionText.addTextLine(" von ca. ");
		questionText.addAttributeNumericalLine(attribute);
		questionText.addTextLine(".");
	}

	private Attribute findAttributeToReplace() {
		StoreElement storeElement = (StoreElement) elementsMap.get(QuestionAttribute.STORE).get(0);
		return storeElement.getChosenAttribute();
	}

	@Override
	public void callSetTemplateType() {
		setTemplateType(Templates.REPLACE);

	}

	public static Map<Templates, Boolean> getTemplateCompatibilityMap() {
		return templateCompatibilityMap;
	}
}
