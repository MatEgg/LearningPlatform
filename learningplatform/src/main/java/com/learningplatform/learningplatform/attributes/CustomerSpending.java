package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.utilities.NameOverviewUtils;

public class CustomerSpending extends Attribute {

	public CustomerSpending(float numericalValue, Element element) {
		super(NameOverviewUtils.CUSTOMER_SPENDING, "Euro", numericalValue, element, AttributeType.CUSTOMERSPENDING);
	}

}
