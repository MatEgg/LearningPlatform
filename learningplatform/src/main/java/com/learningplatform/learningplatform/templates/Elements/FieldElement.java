package com.learningplatform.learningplatform.templates.Elements;

import java.util.List;
import java.util.Random;

import com.learningplatform.learningplatform.models.Field;
import com.learningplatform.learningplatform.attributes.Area;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.Length;
import com.learningplatform.learningplatform.attributes.Volume;
import com.learningplatform.learningplatform.attributes.Width;
import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentUtils;
import com.learningplatform.learningplatform.attributes.Height;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Template;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

public class FieldElement extends NumericalElement {

	Field field;
	Attribute area;
	Attribute length;
	Attribute width;
	Attribute height;
	Attribute volume;
	String name;

	public FieldElement(Template template) {
		super(template);
		setModelService(WordProblem.getInstance().getDaoFactory().getFieldService());
		field = (Field) getModelService().getRandomModel();
		random = new Random();
		findElement();
		finishElementConstruction();
	}

	public FieldElement(Field field, Template template) {
		super(template);
		setModelService(WordProblem.getInstance().getDaoFactory().getFieldService());
		this.field = field;
		random = new Random();
		findElement();
		finishElementConstruction();
	}

	@Override
	String elementText() {
		return field.getName();
	}

	@Override
	String elementTextStart() {
		return field.getNameStart();
	}

	@Override
	String elementTextPlural() {
		return field.getNamePlural();
	}

	@Override
	String replacementString() {
		return field.getNamePlural();
	}

	@Override
	void addQuestionAttributes() {
		List<QuestionAttribute> questionAttributes = getQuestionAttributes();
		questionAttributes.add(QuestionAttribute.AREA);
		questionAttributes.add(QuestionAttribute.NUMERICALTHING);
	}

	@Override
	void findElement() {
		name = field.getName();

		float lengthVal = field.getLength();
		float widthVal = field.getWidth();
		float heightVal = field.getHeight();
		float areaVal = DifficultyAdjustmentUtils.roundNumber(lengthVal * widthVal, Operators.MULTIPLICATION);
		float volumeVal = DifficultyAdjustmentUtils.roundNumber(lengthVal * widthVal * heightVal, Operators.MULTIPLICATION);

		area = new Area(areaVal, this, widthVal, lengthVal);
		length = new Length(lengthVal, this, areaVal, widthVal);
		width = new Width(widthVal, this, areaVal, lengthVal);
		height = new Height(heightVal, this);
		volume = new Volume(volumeVal, this, widthVal, lengthVal, heightVal);

		addAttributeList();
	}

	@Override
	public void addAttributeList() {
		attributeList.add(area);
		attributeTypes.add(AttributeType.AREA);

		attributeList.add(length);
		attributeTypes.add(AttributeType.LENGTH);

		attributeList.add(width);
		attributeTypes.add(AttributeType.WIDTH);

		attributeList.add(height);
		attributeTypes.add(AttributeType.HEIGHT);

		attributeList.add(volume);
		attributeTypes.add(AttributeType.VOLUME);

		attributeMap.put(AttributeType.AREA, area);
		attributeMap.put(AttributeType.LENGTH, length);
		attributeMap.put(AttributeType.WIDTH, width);
		attributeMap.put(AttributeType.HEIGHT, height);
		attributeMap.put(AttributeType.VOLUME, volume);

		setAttributeTypes(attributeTypes);
	}
}
