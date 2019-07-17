package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.utilities.NameOverviewUtils;

public class Cost  extends Attribute {
	
	public Cost(float numericalValue, Element element) {
		super(NameOverviewUtils.COST, "Euro", numericalValue, element, AttributeType.PRICE);
	}
}
