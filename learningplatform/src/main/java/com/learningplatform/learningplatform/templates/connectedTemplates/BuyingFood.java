package com.learningplatform.learningplatform.templates.connectedTemplates;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ListMultimap;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentUtils;
import com.learningplatform.learningplatform.difficulty.DifficultyOverviewHelper;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.question.QuestionHandler;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.TemplateHandler.Templates;
import com.learningplatform.learningplatform.templates.Elements.AnimalElement;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.MaterialElement;
import com.learningplatform.learningplatform.templates.Elements.NewBuildingElement;
import com.learningplatform.learningplatform.templates.Elements.ProductElement;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.QuestionText;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.types.QuestionText.PositionType;
import com.learningplatform.learningplatform.utilities.QuestionTextHelper;

public class BuyingFood extends ComputationalConnectedTemplate {

	ListMultimap<QuestionAttribute, Element> elementsMap;
	List<Attribute> attributes;
	ProductElement productElement;
	StoreElement storeElement;
	AnimalElement animalElement;
	MaterialElement materialElement;
	NewBuildingElement newBuildingElement;
	float days = 1;

	private static final EnumMap<Templates, Boolean> templateCompatibilityMap;

	static {
		templateCompatibilityMap = new EnumMap<>(Templates.class);

	}

	public BuyingFood(WordProblem wordProblem) {
		super(wordProblem);
		questionText = getQuestionText();
		attributes = new ArrayList<>();
		sb = getStringBuilder();
		elementsMap = wordProblem.getElementsMap();
		setRequiredAttr();
	}

	@Override
	public void callFinishTemplate() {
		this.storeElement = (StoreElement) elementsMap.get(QuestionAttribute.STORE).get(0);
		this.productElement = (ProductElement) elementsMap.get(QuestionAttribute.ANIMALFOOD).get(0);
		this.animalElement = (AnimalElement) elementsMap.get(QuestionAttribute.ANIMAL).get(0);

		finishTemplate();
		EnumMap<QuestionAttribute, Element> buyingAttributes = new EnumMap<>(QuestionAttribute.class);
		buyingAttributes.put(QuestionAttribute.ANIMALFOOD, productElement);
		buyingAttributes.put(QuestionAttribute.STORE, storeElement);
		buyingAttributes.put(QuestionAttribute.ANIMAL, animalElement);

		QuestionHandler.getInstance().getQuestionTypeFlags().put(QuestionHandler.QuestionType.BUYINGFOOD,
				buyingAttributes);

	}

	@Override
	public void setQuestionElements() {
		elems = new ArrayList<>();
		elems.add(productElement);
		addQuestionElements(elems);

	}

	@Override
	public void setContainingAttr() {
		addContainingAttr();
	}

	@Override
	public void setRequiredAttr() {
		List<List<QuestionAttribute>> requiredAttributesList = new ArrayList<>();

		List<QuestionAttribute> requiredAttributesFood = new ArrayList<>();
		requiredAttributesFood.add(QuestionAttribute.STORE);
		requiredAttributesFood.add(QuestionAttribute.ANIMAL);
		requiredAttributesFood.add(QuestionAttribute.ANIMALFOOD);

		requiredAttributesList.add(requiredAttributesFood);

		addRequiredAttributes(requiredAttributesList);
	}

	@Override
	public void callSetTemplateType() {
		setTemplateType(Templates.PAYING_FOR_FOOD);
	}

	@Override
	public QuestionText constructQuestion() {
		questionText = new QuestionText();
		days = DifficultyAdjustmentUtils.getMultiplierBasedOnDifficulty(DifficultyOverviewHelper.FEEDING_DAYS,
				Operators.MULTIPLICATION);
		storeElement.setDaysWorthOfFood(days);

		questionText.addTextLine(" Es muss ");
		questionText.addElementLine(productElement, PositionType.START);
		questionText.addTextLine(" für ");
		questionText.addTextLine(QuestionTextHelper.fixFloatRepresentation(days));
		questionText.addTextLine(" Tage nachgekauft werden. ");
		questionText.addTextLine(" Für eine Packung muss ");
		questionText
				.addTextLine(QuestionTextHelper.fixFloatRepresentation(productElement.getCost().getNumericalValue()));
		questionText.addTextLine(" Euro ausgegeben werden.");

		return questionText;
	}

	public float getDays() {
		return days;
	}

	public void setDays(float days) {
		this.days = days;
	}

	public static Map<Templates, Boolean> getTemplateCompatibilityMap() {
		return templateCompatibilityMap;
	}

}
