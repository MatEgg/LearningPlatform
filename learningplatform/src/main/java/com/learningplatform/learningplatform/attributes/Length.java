package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.FormularUtils.Formula;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.utilities.NameOverviewUtils;

import learningplatform.learningplatform.calculation.ValidCalculation;

public class Length extends Attribute {

	public Length(float numericalValue, Element element, float areaValue, float widthValue) {
		super(NameOverviewUtils.LENGTH, NameOverviewUtils.METER, numericalValue, element, AttributeType.LENGTH);

		Float[] attributeValues = new Float[] { widthValue, areaValue };

		this.getValidCalculations().add(new ValidCalculation(attributeValues, Formula.LENGTH, Operators.DIVISION));

	}

}
