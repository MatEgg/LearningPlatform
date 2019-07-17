package com.learningplatform.learningplatform.templates.Elements;

import java.util.List;
import java.util.Map;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.FoodRequirement;
import com.learningplatform.learningplatform.models.Animal;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Template;

public class AnimalElement extends NumericalElement {

	Animal animal;
	Attribute dailyFoodRequirement;
	String name;

	public AnimalElement(Template template) {
		super(template);
		animal = WordProblem.getInstance().getDaoFactory().getAnimalService().getRandomModel();
		findElement();
		finishElementConstruction();
	}

	public AnimalElement(Animal animal, Template template) {
		super(template);
		this.animal = animal;
		findElement();
		finishElementConstruction();
	}

	@Override
	String elementText() {
		return animal.getName();
	}

	@Override
	String elementTextStart() {
		return animal.getName();
	}

	@Override
	String elementTextPlural() {
		return animal.getName();
	}

	@Override
	String replacementString() {
		return animal.getName();
	}

	@Override
	void addQuestionAttributes() {
		List<QuestionAttribute> questionAttributes = getQuestionAttributes();
		questionAttributes.add(QuestionAttribute.ANIMAL);
	}

	@Override
	void findElement() {
		name = animal.getName();

		dailyFoodRequirement = new FoodRequirement(animal.getFoodReq(), this);

		addAttributeList();
	}

	@Override
	public void addAttributeList() {
		attributeList.add(dailyFoodRequirement);
		attributeTypes.add(AttributeType.FOODREQUIREMENT);

		Map<AttributeType, Attribute> attributes = getAttributeMap();
		attributes.put(AttributeType.FOODREQUIREMENT, dailyFoodRequirement);
		setAttributeTypes(attributeTypes);
	}
}
