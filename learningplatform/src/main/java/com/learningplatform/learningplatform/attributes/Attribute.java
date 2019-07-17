package com.learningplatform.learningplatform.attributes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.templates.Elements.Element;

import java.util.ArrayList;
import java.util.List;

import learningplatform.learningplatform.Calculation.AttributeCalculation;
import learningplatform.learningplatform.Calculation.OptimalSolution;
import learningplatform.learningplatform.Calculation.ValidCalculation;

/**
 * Represents Attributes that are present in Elements like Length, Width, Area
 * of an Element like store. Also stores a calculation for the attribute with
 * AttributeCalculation.
 *
 */
public class Attribute {

	float multiplier;
	int id;
	String name;
	String unit;
	float numericalValue;
	Number numericalValueText;
	AttributeType attributeType;
	@JsonIgnore
	Element element;
	Attribute replacementAttribute;
	Boolean isReplacement = false;
	Boolean isVisible = true;
	Boolean isBlocked = false;
	Boolean canBeAsked = false;
	Boolean isDistractor = true;
	Boolean isShown = false;
	OptimalSolution optimalSolution;
	boolean manualHelp = false;

	private List<ValidCalculation> validCalculations;

	AttributeCalculation attributeCalculation;

	
	/**
	 * Instantiates an Attribute.
	 * @param name identifier name of the attribute
	 * @param unit unit of the attribute, e.g m^2
	 * @param numericalValue numerical value of the attribute e.g. m^2 area
	 * @param element element that holds the attribute
	 * @param attributeType Type of the attribute, e.g. Area, Width, Length
	 */
	public Attribute(String name, String unit, float numericalValue, Element element, AttributeType attributeType) {
		this.id = element.getAttributeIdMap().size() + 1;
		this.name = name;
		this.unit = unit;
		this.numericalValue = numericalValue;
		this.numericalValueText = numericalValue;
		this.element = element;
		this.attributeType = attributeType;
		validCalculations = new ArrayList<>();
		element.getAttributeIdMap().put(id, this);
		attributeCalculation = new AttributeCalculation(this);
	}

	/**
	 * Instantiates an Attribute with a multiplier showing the association to another attribute of another element.
	 * @param name identifier name of the attribute
	 * @param unit unit of the attribute, e.g m^2
	 * @param numericalValue numerical value of the attribute e.g. m^2 area
	 * @param multiplier numerical multiplier showing the ratio between this attribute and another one
	 * @param element element that holds the attribute
	 * @param attributeType Type of the attribute, e.g. Area, Width, Length
	 */
	public Attribute(String name, String unit, float numericalValue, float multiplier, Element element,
			AttributeType attributeType) {
		this.id = element.getAttributeList().size() + 1;
		this.name = name;
		this.unit = unit;
		this.numericalValue = numericalValue;
		this.multiplier = multiplier;
		this.numericalValueText = numericalValue;
		this.element = element;
		this.attributeType = attributeType;
		validCalculations = new ArrayList<>();
		attributeCalculation = new AttributeCalculation(this);
	}

	public Element getElement() {
		return element;
	}

	public AttributeType getAttributeType() {
		return attributeType;
	}

	public void setElement(Element element) {
		this.element = element;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getNumericalValue() {
		return numericalValue;
	}

	public void setNumericalValue(float numericalValue) {
		this.numericalValue = numericalValue;
	}

	public Number getNumericalValueText() {
		return numericalValueText;
	}

	public void setNumericalValueText(Number numericalValueText) {
		this.numericalValueText = numericalValueText;
	}

	public Attribute getReplacementAttribute() {
		return replacementAttribute;
	}

	public void setReplacementAttribute(Attribute replacementAttribute) {
		this.replacementAttribute = replacementAttribute;
	}

	public Boolean getIsReplacement() {
		return isReplacement;
	}

	public void setIsReplacement(Boolean isReplacement) {
		this.isReplacement = isReplacement;
	}

	public float getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(float multiplier) {
		this.multiplier = multiplier;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean getIsVisible() {
		return isVisible;
	}

	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}

	/**
	 * Attribute has to be visible for the user if there is no way to calculate it.
	 * Example: Area, length and width attribute present. No allowance of
	 * calculation of area by length and width. Length and width therefore are made
	 * invisible but aren't allowed because there are no other ways to calculate it
	 */
	public void setNotVisibleIfNotBlockedAndReplaced() {
		if (!isBlocked && getReplacementAttribute() == null) {
			this.isVisible = false;
		}
	}

	public AttributeCalculation getAttributeCalculation() {
		return attributeCalculation;
	}

	public void setAttributeCalculation(AttributeCalculation attributeCalculation) {
		this.attributeCalculation = attributeCalculation;
	}

	public Boolean getIsBlocked() {
		return isBlocked;
	}

	public void setIsBlocked(Boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public Boolean getCanBeAsked() {
		return canBeAsked;
	}

	public void setCanBeAsked(Boolean canBeAsked) {
		this.canBeAsked = canBeAsked;
	}

	public Boolean getIsDistractor() {
		return isDistractor;
	}

	public void setIsDistractor(Boolean isDistractor) {
		this.isDistractor = isDistractor;
		this.isShown = !isDistractor;
	}

	public List<ValidCalculation> getValidCalculations() {
		return validCalculations;
	}

	public void setValidCalculations(List<ValidCalculation> validCalculations) {
		this.validCalculations = validCalculations;
	}

	public Boolean getIsShown() {
		return isShown;
	}

	public void setIsShown(Boolean isShown) {
		this.isShown = isShown;
	}

	public OptimalSolution getOptimalSolution() {
		return optimalSolution;
	}

	public void setOptimalSolution(OptimalSolution optimalSolution) {
		this.optimalSolution = optimalSolution;
	}

	public boolean isManualHelp() {
		return manualHelp;
	}

	public void setManualHelp(boolean manualHelp) {
		this.manualHelp = manualHelp;
	}
}
