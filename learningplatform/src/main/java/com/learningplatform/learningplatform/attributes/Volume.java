package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.FormularUtils.Formula;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.utilities.NameOverviewUtils;

import learningplatform.learningplatform.Calculation.ValidCalculation;

public class Volume extends Attribute {

	public Volume(float numericalValue, Element element, float widthValue, float lengthValue, float heightValue) {
		super(NameOverviewUtils.VOLUME, NameOverviewUtils.QUBIC_METER, numericalValue, element, AttributeType.VOLUME);


		Float[] attributeValues = new Float[] { widthValue, lengthValue, heightValue };

		this.getValidCalculations()
				.add(new ValidCalculation(attributeValues, Formula.VOLUME, Operators.MULTIPLICATION));
	}

}
