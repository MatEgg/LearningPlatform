package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.FormularUtils.Formula;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.utilities.NameOverviewUtils;

import learningplatform.learningplatform.Calculation.ValidCalculation;

public class Width extends Attribute {

	public Width(float numericalValue, Element element, float areaValue, float lengthValue) {
		super(NameOverviewUtils.WIDTH, NameOverviewUtils.METER, numericalValue, element, AttributeType.WIDTH);

		Float[] attributeValues = new Float[] { lengthValue, areaValue };

		this.getValidCalculations().add(new ValidCalculation(attributeValues, Formula.WIDTH, Operators.DIVISION));
	}
}
