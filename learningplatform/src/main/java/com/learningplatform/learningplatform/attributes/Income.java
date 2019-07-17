package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.templates.Elements.Element;

public class Income extends Attribute {

	public Income(float numericalValue, Element element) {
		super("Einnahmen", "Euro", numericalValue, element, AttributeType.INCOME);
	}

}
