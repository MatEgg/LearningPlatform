package com.learningplatform.learningplatform.templates.connectedTemplates;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.ListMultimap;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeConcept;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.FormularUtils.Formula;
import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentUtils;
import com.learningplatform.learningplatform.difficulty.DifficultyOverviewHelper;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.question.QuestionHandler;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.NewBuildingElement;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.templates.TemplateHandler.Templates;
import com.learningplatform.learningplatform.types.QuestionText;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.utilities.QuestionTextHelper;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

import learningplatform.learningplatform.Calculation.AttributeCalculation;
import learningplatform.learningplatform.Calculation.ValidCalculation;

public class BuildThing extends GeometryConnectedTemplate {

	ListMultimap<QuestionAttribute, Element> elementsMap;
	List<Attribute> attributes;
	StoreElement storeElement;
	NewBuildingElement newBuildingElement;
	int level = 1;

	private static final EnumMap<Templates, Boolean> templateCompatibilityMap;

	static {
		templateCompatibilityMap = new EnumMap<>(Templates.class);
		templateCompatibilityMap.put(Templates.PAYING_FOR_MATERIAL, false);

	}

	public BuildThing(WordProblem wordProblem) {
		super(wordProblem);
		elementsMap = wordProblem.getElementsMap();
		newBuildingElement = new NewBuildingElement(this);
		questionText = getQuestionText();
		attributes = new ArrayList<>();
		sb = getStringBuilder();
		setRequiredAttr();
	}

	@Override
	public void callFinishTemplate() {
		storeElement = (StoreElement) elementsMap.get(QuestionAttribute.STORE).get(0);

		EnumMap<QuestionAttribute, Element> buildingAttributes = new EnumMap<>(QuestionAttribute.class);
		buildingAttributes.put(QuestionAttribute.BUILD, newBuildingElement);

		QuestionHandler.getInstance().getQuestionTypeFlags().put(QuestionHandler.QuestionType.BUILDING,
				buildingAttributes);

		newBuildingElement.getAttributeByType(AttributeType.LENGTH).setIsDistractor(false);
		newBuildingElement.getAttributeByType(AttributeType.WIDTH).setIsDistractor(false);
		newBuildingElement.getAttributeByType(AttributeType.HEIGHT).setIsDistractor(false);
		newBuildingElement.getAttributeByType(AttributeType.AREA).setIsDistractor(false);
		newBuildingElement.getAttributeByType(AttributeType.VOLUME).setIsDistractor(false);

		newBuildingElement.getAttributeByType(AttributeType.AREA).setIsVisible(false);
		newBuildingElement.getAttributeByType(AttributeType.VOLUME).setIsVisible(false);
		finishTemplate();

	}

	@Override
	public void setQuestionElements() {
		List<Element> questionElements = new ArrayList<>();
		questionElements.add(newBuildingElement);
		addQuestionElements(questionElements);

	}

	@Override
	public void setContainingAttr() {
		addContainingAttr();
	}

	@Override
	public void setRequiredAttr() {
		List<List<QuestionAttribute>> requiredAttributesList = new ArrayList<>();

		List<QuestionAttribute> requiredAttributes = new ArrayList<>();
		requiredAttributes.add(QuestionAttribute.STORE);

		requiredAttributesList.add(requiredAttributes);

		addRequiredAttributes(requiredAttributesList);
	}

	@Override
	public void setRelevantAttributes() {
		addRelevantAttributes(attributes);
	}

	private QuestionText constructQuestionText() {

		float width = newBuildingElement.getAttributeByType(AttributeType.WIDTH).getNumericalValue();
		float length = newBuildingElement.getAttributeByType(AttributeType.LENGTH).getNumericalValue();
		float height = newBuildingElement.getAttributeByType(AttributeType.HEIGHT).getNumericalValue();

		questionText = new QuestionText();

		questionText = new QuestionText();
		questionText.addTextLine(" Aufgrund starkem Nachwuchs soll nun ein neues Gehege mit den Maßen ");
		questionText.addTextLine(QuestionTextHelper.fixFloatRepresentation(length));
		questionText.addTextLine("m");
		questionText.addTextLine(" x ");
		questionText.addTextLine(QuestionTextHelper.fixFloatRepresentation(width));
		questionText.addTextLine("m ");
		questionText.addTextLine(" x ");
		questionText.addTextLine(QuestionTextHelper.fixFloatRepresentation(height));
		questionText.addTextLine("m ");
		questionText.addTextLine(" gebaut werden.");

		return questionText;
	}

	private Attribute chooseMultiplierElement(Attribute chosenAttribute) {
		Attribute multiplierElement = null;

		if (chosenAttribute.getAttributeType() == AttributeType.AREA) {
			multiplierElement = newBuildingElement.getAttributeByType(AttributeType.AREA);
		} else if (chosenAttribute.getAttributeType() == AttributeType.WIDTH) {
			multiplierElement = newBuildingElement.getAttributeByType(AttributeType.WIDTH);
		} else if (chosenAttribute.getAttributeType() == AttributeType.LENGTH) {
			multiplierElement = newBuildingElement.getAttributeByType(AttributeType.LENGTH);
		} else if (chosenAttribute.getAttributeType() == AttributeType.VOLUME) {
			multiplierElement = newBuildingElement.getAttributeByType(AttributeType.VOLUME);
		} else {
			multiplierElement = newBuildingElement.getAttributeByType(AttributeType.LENGTH);
		}
		return multiplierElement;
	}

	@Override
	public QuestionText constructQuestion() {
		Attribute chosenAttribute = null;
		boolean invisible = false;

		float firstVal = 1;

		if (storeElement.getAnimalPerArea() == 0) {
			if (level == 1) {
				if (UtilitiesHelper.getRandom().nextBoolean()) {
					chosenAttribute = newBuildingElement.getAttributeByType(AttributeType.LENGTH);
				} else {
					chosenAttribute = newBuildingElement.getAttributeByType(AttributeType.WIDTH);
				}
				firstVal = (int) DifficultyAdjustmentUtils.getMultiplierBasedOnDifficulty(
						DifficultyOverviewHelper.STORE_BOUND_LVL1, Operators.MULTIPLICATION);
			} else if (level == 3 && WordProblem.getInstance().getSettings().getDecimalEnabled() == 1) {
				chosenAttribute = newBuildingElement.getAttributeByType(AttributeType.VOLUME);
				firstVal = DifficultyAdjustmentUtils.getPercentageMultiplierBasedOnDifficulty(Operators.MULTIPLICATION,
						DifficultyOverviewHelper.STORE_BOUND_LVL3_PERCENT_LOWER,
						DifficultyOverviewHelper.STORE_BOUND_LVL3_PERCENT_UPPER);
			} else {
				chosenAttribute = newBuildingElement.getAttributeByType(AttributeType.AREA);
				firstVal = (int) DifficultyAdjustmentUtils.getMultiplierBasedOnDifficulty(
						DifficultyOverviewHelper.STORE_BOUND_LVL2, Operators.MULTIPLICATION);
			}
		} else {
			firstVal = storeElement.getAnimalPerArea();
			chosenAttribute = storeElement.getChosenAttribute();
			invisible = true;
		}

		Attribute multiplierElement = chooseMultiplierElement(chosenAttribute);

		Attribute animalAttribute = newBuildingElement.getAttributeByType(AttributeType.ANIMALCOUNT);

		Float[] values = new Float[] { (float) multiplierElement.getNumericalValue(), (float) firstVal };
		animalAttribute.getValidCalculations()
				.add(new ValidCalculation(values, Formula.ANIMALPERSIZE, Operators.MULTIPLICATION));
		animalAttribute.getAttributeCalculation().getCalculationInformation()
				.setAttributeConcept(AttributeConcept.COMPUTATIONAL_RULES_SKILL);

		if (chosenAttribute.getAttributeType() == AttributeType.VOLUME) {
			newBuildingElement.getAttributeByType(AttributeType.ANIMALCOUNT)
					.setNumericalValue((int) (multiplierElement.getNumericalValue() * (firstVal / 100)));
			animalAttribute.setAttributeCalculation(
					new AttributeCalculation(multiplierElement, firstVal, Operators.MULTIPLICATION));
		} else {
			newBuildingElement.getAttributeByType(AttributeType.ANIMALCOUNT)
					.setNumericalValue(multiplierElement.getNumericalValue() * firstVal);
			animalAttribute.setAttributeCalculation(
					new AttributeCalculation(multiplierElement, firstVal, Operators.MULTIPLICATION));
		}

		if (invisible) {
			animalAttribute.setIsVisible(false);
		}

		return constructQuestionText();
	}

	@Override
	public void callSetTemplateType() {
		setTemplateType(Templates.BUILDS_A_NEW_THING);
	}

	public static Map<Templates, Boolean> getTemplateCompatibilityMap() {
		return templateCompatibilityMap;
	}

}
