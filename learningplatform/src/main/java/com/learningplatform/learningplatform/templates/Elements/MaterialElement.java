package com.learningplatform.learningplatform.templates.Elements;

import java.util.List;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.Cost;
import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentUtils;
import com.learningplatform.learningplatform.difficulty.DifficultyOverviewHelper;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.templates.Template;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

public class MaterialElement extends NumericalElement {

	String name;
	Attribute cost;

	public MaterialElement(Template template) {
		super(template);
		findElement();
		finishElementConstruction();
	}

	@Override
	String elementText() {
		return name;
	}

	@Override
	String elementTextStart() {
		return name;
	}

	@Override
	String elementTextPlural() {
		return name;
	}

	@Override
	String replacementString() {
		return name;
	}

	@Override
	void addQuestionAttributes() {
		List<QuestionAttribute> questionAttributes = getQuestionAttributes();
		questionAttributes.add(QuestionAttribute.BUILDMATERIAL);

	}

	@Override
	void addAttributeList() {
		attributeList.add(cost);
		attributeTypes.add(AttributeType.PRICE);

		attributeMap.put(AttributeType.PRICE, cost);

		setAttributeTypes(attributeTypes);

	}

	@Override
	void findElement() {
		name = "Baumaterialien";

		int costVal = (int) DifficultyAdjustmentUtils.getMultiplierBasedOnDifficulty(DifficultyOverviewHelper.MATERIAL_COST, Operators.MULTIPLICATION);
		cost = new Cost(costVal, this);

		addAttributeList();

	}

}
