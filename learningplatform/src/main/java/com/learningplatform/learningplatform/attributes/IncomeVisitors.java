package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.templates.Elements.Element;

public class IncomeVisitors extends Attribute {

	public IncomeVisitors(float numericalValue, Element element) {
		super("Einnahmen Besucher", "Euro", numericalValue, element, AttributeType.INCOMEVISITORS);
	}

}
