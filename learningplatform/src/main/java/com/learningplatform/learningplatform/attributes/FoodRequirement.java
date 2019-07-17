package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.utilities.NameOverviewUtils;

public class FoodRequirement extends Attribute {

	public FoodRequirement(float numericalValue, Element element) {
		super(NameOverviewUtils.FOODREQUIREMENT, "", numericalValue, element, AttributeType.FOODREQUIREMENT);
	}

}
