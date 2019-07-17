package com.learningplatform.learningplatform.templates.Elements;

import java.util.List;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.Population;
import com.learningplatform.learningplatform.difficulty.DifficultyHandler;
import com.learningplatform.learningplatform.models.City;
import com.learningplatform.learningplatform.models.service.CityService;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Template;

import learningplatform.learningplatform.calculation.AttributeCalculation;

public class CityElement extends NumericalElement {
	CityService cityService;
	
	City city;
	Attribute population;
	String name;

	public CityElement(Template template) {
		super(template);
		cityService = WordProblem.getInstance().getDaoFactory().getCityService();
		float difficulty = (float) DifficultyHandler.getInstance().getDifficulty().getPercentCalculationDifficulty().getDifficulty() / 100;
		city = cityService.getCityByPercentage(difficulty);
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
		questionAttributes.add(QuestionAttribute.CITY);

	}

	@Override
	void addAttributeList() {
		attributeList.add(population);
		attributeTypes.add(AttributeType.POPULATION);

		attributeMap.put(AttributeType.POPULATION, population);

		setAttributeTypes(attributeTypes);

	}

	@Override
	void findElement() {
		name = city.getName();
		int populationValue = city.getPopulation();

		population = new Population(populationValue, this);

		population.setAttributeCalculation(new AttributeCalculation(population));

		addAttributeList();

	}
}
