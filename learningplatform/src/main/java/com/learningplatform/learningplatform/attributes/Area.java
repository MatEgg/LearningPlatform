package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.FormularUtils.Formula;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.utilities.NameOverviewUtils;

import learningplatform.learningplatform.Calculation.ValidCalculation;

public class Area extends Attribute {

	/**
	 * Instantiates an Area attribute.
	 * 
	 * @param numericalValue numerical value of the attribute e.g. m^2 area
	 * @param element        element that holds the attribute
	 * @param widthValue     width value used to calculate the area
	 * @param lengthValue    length value used to calculate the area
	 */
	public Area(float numericalValue, Element element, float widthValue, float lengthValue) {

		super(NameOverviewUtils.AREA, NameOverviewUtils.SQUARE_METER, numericalValue, element, AttributeType.AREA);

		Float[] attributeValues = new Float[] { widthValue, lengthValue };

		this.getValidCalculations().add(new ValidCalculation(attributeValues, Formula.AREA, Operators.MULTIPLICATION));
	}
}
