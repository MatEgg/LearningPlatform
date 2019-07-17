package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.utilities.NameOverviewUtils;

public class Population extends Attribute {

	public Population(float numericalValue, Element element) {
		super(NameOverviewUtils.POPULATION, "", numericalValue, element, AttributeType.POPULATION);
	}

}
