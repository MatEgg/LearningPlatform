package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.utilities.NameOverviewUtils;

public class AnimalCount extends Attribute {

	public AnimalCount(float numericalValue, Element element) {
		super(NameOverviewUtils.ANIMALCOUNT, "", numericalValue, element, AttributeType.ANIMALCOUNT);
	}
}
