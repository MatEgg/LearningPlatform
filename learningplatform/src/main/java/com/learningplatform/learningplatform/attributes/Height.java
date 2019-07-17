package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.utilities.NameOverviewUtils;

public class Height extends Attribute {

	public Height(float numericalValue, Element element) {
		super(NameOverviewUtils.HEIGHT, NameOverviewUtils.METER, numericalValue, element, AttributeType.HEIGHT);
		
	}

}
