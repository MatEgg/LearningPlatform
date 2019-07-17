package com.learningplatform.learningplatform.attributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentUtils;
import com.learningplatform.learningplatform.difficulty.DifficultyOverviewHelper;
import com.learningplatform.learningplatform.models.difficulty.Difficulty;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.ElementHandler;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.TargetAttribute;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

import learningplatform.learningplatform.calculation.AttributeCalculation;

/**
 * Handles issues belonging to the treatment of attributes.
 */
public class AttributeHandler {

	Random random;
	float amountOfAnimalsPer = 0;

	static AttributeHandler attributeHandlerInstance;

	public static enum AttributeType {
		AREA, WIDTH, LENGTH, HEIGHT, VOLUME, FOODREQUIREMENT, ANIMALCOUNT, PRODUCT, PRICE, INCOME, EXPENSEFOOD,
		EXPENSECONSTRUCTION, POPULATION, INCOMEVISITORS, CUSTOMERSPENDING
	}

	public static enum AttributeConcept {
		COMPUTATIONAL_RULES_SKILL, AREA_CALCULATION_SKILL, CALCULATE_PROBABILITIES_SKILL, PERCENTAGE_CALCULATION_SKILL;
	}

	public AttributeHandler() {
	}

	/**
	 * Lazy instantiation of the AttributeHandler.
	 * 
	 * @return returns instance of the AttributeHandler
	 */
	public static AttributeHandler getInstance() {
		if (attributeHandlerInstance == null) {
			attributeHandlerInstance = new AttributeHandler();
			return attributeHandlerInstance;
		} else {
			return attributeHandlerInstance;
		}
	}

	public static AttributeHandler getNewInstance() {
		attributeHandlerInstance = new AttributeHandler();
		return attributeHandlerInstance;
	}

	/**
	 * Returns a random Attribute from a new element that is of the same type as the
	 * parameter Attribute.
	 * 
	 * @param oldElement    the element the attribute to be replaced is part of
	 * @param attribute     the attribute that is to be replaced
	 * @param isReplacement boolean check if the attribute is already used as an
	 *                      replacement for another attribute
	 * @return attribute that will be used to replace another attribute
	 */
	public Attribute replaceAttributWithRandomType(Element oldElement, Attribute attribute, boolean isReplacement) {
		// find a multiplier that is suitable according to difficulty demands. Will act
		// as the ratio between two attributes.
		float wishMultiplier = DifficultyAdjustmentUtils
				.getMultiplierBasedOnDifficulty(DifficultyOverviewHelper.MAX_MULTIPLIER, Operators.MULTIPLICATION);
		AttributeType oldAttributeType = attribute.getAttributeType();

		Element newElement = ElementHandler.getNewRandomElementSameType(attribute.getElement(),
				attribute.getElement().getTemplate(), attribute.getNumericalValue() / wishMultiplier, oldAttributeType);
		if (newElement == null) {
			return null;
		}
		float newValue = newElement.getAttributeMap().get(oldAttributeType).getNumericalValue();
		float oldValue = attribute.getNumericalValue();
		float multiplier = (oldValue / newValue);
		Attribute newAttribute = new Attribute(attribute.getName(), attribute.getUnit(), newValue, multiplier,
				newElement, attribute.getAttributeType());
		

		if (oldElement instanceof StoreElement) {
			changeVisibilityBecauseReplaced(newAttribute, (StoreElement) oldElement);
		}
		newAttribute.setIsReplacement(true);

		return newAttribute;
	}

	public void changeVisibility(Attribute attribute, Boolean bool) {

		attribute.setIsVisible(bool);
	}

	// Change the visibility of certain attributes so that the student is forced to
	// calculate the replacement by calculating the ratio in 50% of cases.
	private void changeVisibilityBecauseReplaced(Attribute attribute, StoreElement storeElement) {
		if (getRandom().nextBoolean()) {
			if (attribute.getAttributeType() == AttributeType.AREA) {
				storeElement.getAttributeByType(AttributeType.LENGTH).setIsVisible(false);
			} else if (attribute.getAttributeType() == AttributeType.LENGTH) {
				storeElement.getAttributeByType(AttributeType.AREA).setIsVisible(false);

			} else if (attribute.getAttributeType() == AttributeType.WIDTH) {
				storeElement.getAttributeByType(AttributeType.AREA).setIsVisible(false);

			} else if (attribute.getAttributeType() == AttributeType.VOLUME) {
				int temp = getRandom().nextInt(3);
				if (temp == 0) {
					storeElement.getAttributeByType(AttributeType.LENGTH).setIsVisible(false);
				} else if (temp == 1) {
					storeElement.getAttributeByType(AttributeType.WIDTH).setIsVisible(false);
				} else {
					storeElement.getAttributeByType(AttributeType.HEIGHT).setIsVisible(false);
				}

			}
		}

	}

	public float getAmountOfAnimalsPer() {
		return amountOfAnimalsPer;
	}

	public void setAmountOfAnimalsPer(float amountOfAnimalsPer) {
		this.amountOfAnimalsPer = amountOfAnimalsPer;
	}

	protected Random getRandom() {
		if (random == null) {
			random = new Random();
		}
		return random;
	}
}
