package com.learningplatform.learningplatform.templates.baseTemplates;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.difficulty.DifficultyCalculated;
import com.learningplatform.learningplatform.difficulty.DifficultyHandler;
import com.learningplatform.learningplatform.difficulty.conceptDifficulty.ComputationalRulesDifficulty;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.question.QuestionHandler;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.TemplateHandler.Templates;
import com.learningplatform.learningplatform.templates.Elements.AnimalElement;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.ProductElement;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.Question;
import com.learningplatform.learningplatform.types.QuestionText;
import com.learningplatform.learningplatform.types.QuestionText.PositionType;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

public class StoreProductAnimalTemplate extends ComputationalBaseTemplate {

	StoreElement storeElement;
	ProductElement productElement;
	AnimalElement animalElement;
	List<Question> templateQuestions;
	List<Attribute> attributes;
	int level = 1;
	private static final EnumMap<Templates, Boolean> templateCompatibilityMap;

	static {
		templateCompatibilityMap = new EnumMap<>(Templates.class);
		templateCompatibilityMap.put(Templates.CALCULATE_AREA_OF_STORE, false);
		templateCompatibilityMap.put(Templates.PRODUCT_TIME_UNTIL_COMPLETION, false);
		templateCompatibilityMap.put(Templates.BUILDS_A_NEW_THING, false);
		templateCompatibilityMap.put(Templates.PAYING_FOR_FOOD, false);
		templateCompatibilityMap.put(Templates.INCOME_VISITORS, false);
	}

	public StoreProductAnimalTemplate(WordProblem wordProblem) {
		super(wordProblem);
		this.wordProblem = wordProblem;
		sb = getStringBuilder();
		storeElement = new StoreElement(this);
		productElement = new ProductElement(this);
		animalElement = new AnimalElement(this);
		templateQuestions = new ArrayList<>();
		attributes = new ArrayList<>();
	}

	public QuestionText constructQuestion() {
		questionText = new QuestionText();
		questionText.addTextLine(storeElement.getTextStartStart());
		questionText.addTextLine(" kauft ");
		questionText.addElementLine(productElement, PositionType.START);
		questionText.addTextLine(" für ");
		questionText.addElementLine(animalElement, PositionType.NORMAL);
		questionText.addTextLine(".");

		return questionText;
	}

	@Override
	public void setQuestionElements() {
		List<Element> questionElements = new ArrayList<>();
		questionElements.add(storeElement);
		questionElements.add(productElement);
		questionElements.add(animalElement);
		addQuestionElements(questionElements);
	}

	@Override
	public void setContainingAttr() {
		addContainingAttr();
	}

	@Override
	public void callFinishTemplate() {

		EnumMap<QuestionAttribute, Element> feedingAttributes = new EnumMap<>(QuestionAttribute.class);
		feedingAttributes.put(QuestionAttribute.ANIMALFOOD, productElement);
		feedingAttributes.put(QuestionAttribute.STORE, storeElement);
		feedingAttributes.put(QuestionAttribute.ANIMAL, animalElement);

		QuestionHandler.getInstance().getQuestionTypeFlags().put(QuestionHandler.QuestionType.FEEDING,
				feedingAttributes);
		DifficultyCalculated difficultyCalculated = DifficultyHandler.getInstance().getDifficulty();
		ComputationalRulesDifficulty computationalRulesDifficulty = difficultyCalculated
				.getComputationalRulesDifficulty();
		int difficulty = computationalRulesDifficulty.getDifficulty();

		if (difficulty > 35 && difficulty <= 70) {
			level = 2;
		} else if (difficulty > 70) {
			level = 3;
		}

		if (level == 1) {
			if (UtilitiesHelper.getRandom().nextBoolean()) {
				storeElement.setChosenAttribute(storeElement.getAttributeByType(AttributeType.LENGTH));
			} else {
				storeElement.setChosenAttribute(storeElement.getAttributeByType(AttributeType.WIDTH));
			}
		} else if (level == 2) {
			storeElement.setChosenAttribute(storeElement.getAttributeByType(AttributeType.AREA));
		} else {
			if (WordProblem.getInstance().getSettings().getDecimalEnabled() == 1) {
				storeElement.setChosenAttribute(storeElement.getAttributeByType(AttributeType.VOLUME));
			} else {
				storeElement.setChosenAttribute(storeElement.getAttributeByType(AttributeType.AREA));
			}
		}

		finishTemplate();
	}

	@Override
	public void setRelevantAttributes() {
		attributes.add(storeElement.getAttributeByType(AttributeType.AREA));
		attributes.add(storeElement.getAttributeByType(AttributeType.LENGTH));
		attributes.add(storeElement.getAttributeByType(AttributeType.WIDTH));
		addRelevantAttributes(attributes);
	}

	@Override
	public void callSetTemplateType() {
		setTemplateType(Templates.STORE_PRODUCT_ANIMAL);
	}
	
	public static Map<Templates, Boolean> getTemplateCompatibilityMap() {
		return templateCompatibilityMap;
	}

}
