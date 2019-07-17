package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.templates.Elements.Element;

public class ExpensesConstruction extends Attribute {

	public ExpensesConstruction(float numericalValue, Element element) {
		super("Ausgaben Anbau", "Euro", numericalValue, element, AttributeType.EXPENSECONSTRUCTION);
	}

}
