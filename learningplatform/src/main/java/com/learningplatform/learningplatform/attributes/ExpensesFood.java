package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.templates.Elements.Element;

public class ExpensesFood extends Attribute {

	public ExpensesFood(float numericalValue, Element element) {
		super("Ausgaben Verpfl.", "Euro", numericalValue, element, AttributeType.EXPENSEFOOD);
	}

}
