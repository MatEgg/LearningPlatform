package com.learningplatform.learningplatform.templates.connectedTemplates;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ListMultimap;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentUtils;
import com.learningplatform.learningplatform.difficulty.DifficultyOverviewHelper;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.question.QuestionHandler;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.TemplateHandler.Templates;
import com.learningplatform.learningplatform.templates.Elements.AnimalElement;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.ProductElement;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.QuestionText;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.types.QuestionText.PositionType;

import learningplatform.learningplatform.Calculation.AttributeCalculation;
import learningplatform.learningplatform.Calculation.OptimalSolution;

public class ProductTimeUntilDepletion extends ComputationalConnectedTemplate {

	ProductElement productElement;
	AnimalElement animalElement;
	StoreElement storeElement;
	ListMultimap<QuestionAttribute, Element> elementsMap;
	List<Attribute> attributes;
	int firstVal;
	int secondVal;
	int days;
	int animalCount;
	int days2;

	private static final EnumMap<Templates, Boolean> templateCompatibilityMap;

	static {
		templateCompatibilityMap = new EnumMap<>(Templates.class);

	}

	public ProductTimeUntilDepletion(WordProblem wordProblem) {
		super(wordProblem);
		questionText = getQuestionText();
		attributes = new ArrayList<>();

		sb = getStringBuilder();
		this.wordProblem = wordProblem;
		elementsMap = wordProblem.getElementsMap();
	}

	@Override
	public void setQuestionElements() {
		elems = new ArrayList<>();
		elems.add(productElement);
		elems.add(animalElement);
		addQuestionElements(elems);
	}

	@Override
	public void setContainingAttr() {
		addContainingAttr();
	}

	@Override
	public void setRequiredAttr() {

		List<QuestionAttribute> requiredAttributes = new ArrayList<>();
		requiredAttributes.add(QuestionAttribute.STORE);
		requiredAttributes.add(QuestionAttribute.ANIMAL);
		requiredAttributes.add(QuestionAttribute.ANIMALFOOD);

		List<List<QuestionAttribute>> requiredAttributesList = new ArrayList<>();
		requiredAttributesList.add(requiredAttributes);

		addRequiredAttributes(requiredAttributesList);
	}

	@Override
	public QuestionText constructQuestion() {
		Attribute foodReq = animalElement.getAttributeByType(AttributeType.FOODREQUIREMENT);
		questionText = new QuestionText();
		int foodRequirementValue = (int) foodReq.getNumericalValue();
		int multiplier = (int) DifficultyAdjustmentUtils
				.getMultiplierBasedOnDifficulty(DifficultyOverviewHelper.ANIMALPACKAGECOUNT, Operators.DIVISION);
		animalCount = foodRequirementValue * multiplier;
		int dayCount = 1 * multiplier;
		addAttributeCalculation(foodReq, animalCount, dayCount);

		questionText.addTextLine(Integer.toString(animalCount));
		questionText.addTextLine(" Packungen ");
		questionText.addElementLine(productElement, PositionType.NORMAL);
		questionText.addTextLine(" können ein ");
		questionText.addElementLine(animalElement, PositionType.NORMAL);
		questionText.addTextLine(" ");
		questionText.addTextLine(Integer.toString(dayCount));
		questionText.addTextLine(" Tage lang versorgen.");

		return questionText;
	}

	private void addAttributeCalculation(Attribute foodReq, int animalCount, int dayCount) {
		foodReq.setAttributeCalculation(
				new AttributeCalculation(animalCount, "", dayCount, "Tage", Operators.DIVISION));
		foodReq.setCanBeAsked(true);
		foodReq.setIsDistractor(false);
		foodReq.setIsVisible(false);
		OptimalSolution optimalSolution = new OptimalSolution(foodReq.getAttributeCalculation());
		foodReq.setOptimalSolution(optimalSolution);
		foodReq.getOptimalSolution().getCalculationStats().setStepsNeeded(1);

	}

	@Override
	public void callFinishTemplate() {
		this.productElement = (ProductElement) elementsMap.get(QuestionAttribute.ANIMALFOOD).get(0);
		this.animalElement = (AnimalElement) elementsMap.get(QuestionAttribute.ANIMAL).get(0);
		this.storeElement = (StoreElement) elementsMap.get(QuestionAttribute.STORE).get(0);

		EnumMap<QuestionAttribute, Element> feedingAttributes = new EnumMap<>(QuestionAttribute.class);
		feedingAttributes.put(QuestionAttribute.ANIMALFOOD, productElement);
		feedingAttributes.put(QuestionAttribute.STORE, storeElement);
		feedingAttributes.put(QuestionAttribute.ANIMAL, animalElement);

		QuestionHandler.getInstance().getQuestionTypeFlags().put(QuestionHandler.QuestionType.FEEDING,
				feedingAttributes);

		finishTemplate();
	}

	@Override
	public void setRelevantAttributes() {
		attributes.add(animalElement.getAttributeByType(AttributeType.FOODREQUIREMENT));
	}

	@Override
	public void callSetTemplateType() {
		setTemplateType(Templates.PRODUCT_TIME_UNTIL_COMPLETION);
	}

	public static Map<Templates, Boolean> getTemplateCompatibilityMap() {
		return templateCompatibilityMap;
	}
}
