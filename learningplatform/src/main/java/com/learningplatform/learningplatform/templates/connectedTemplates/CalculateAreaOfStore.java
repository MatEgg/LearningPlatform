package com.learningplatform.learningplatform.templates.connectedTemplates;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.google.common.collect.ListMultimap;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeConcept;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.FormularUtils.Formula;
import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentUtils;
import com.learningplatform.learningplatform.difficulty.DifficultyOverviewHelper;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.question.QuestionHandler;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.TemplateHandler.Templates;
import com.learningplatform.learningplatform.templates.Elements.AnimalElement;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.NewBuildingElement;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.types.QuestionText.PositionType;
import com.learningplatform.learningplatform.types.QuestionText;
import com.learningplatform.learningplatform.utilities.AttributeUtils;
import com.learningplatform.learningplatform.utilities.QuestionTextHelper;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

import learningplatform.learningplatform.calculation.AttributeCalculation;
import learningplatform.learningplatform.calculation.ValidCalculation;

public class CalculateAreaOfStore extends GeometryConnectedTemplate {

	StoreElement storeElement;
	AnimalElement animalElement;
	NewBuildingElement newBuildingElement;
	ListMultimap<QuestionAttribute, Element> elementsMap;
	float firstVal;
	int days;
	int animalCount;
	List<Attribute> attributes;
	int level = 1;

	private static final EnumMap<Templates, Boolean> templateCompatibilityMap;

	static {
		templateCompatibilityMap = new EnumMap<>(Templates.class);

	}

	public CalculateAreaOfStore(WordProblem wordProblem) {
		super(wordProblem);
		questionText = getQuestionText();
		attributes = new ArrayList<>();
		sb = getStringBuilder();
		this.wordProblem = wordProblem;
		elementsMap = wordProblem.getElementsMap();
		setRequiredAttr();
	}

	public CalculateAreaOfStore() {
		super();
		questionText = getQuestionText();
		attributes = new ArrayList<>();
		sb = getStringBuilder();
		setRequiredAttr();
	}

	@Override
	public void setQuestionElements() {
		List<Element> elems = new ArrayList<>();
		elems.add(storeElement);
		addQuestionElements(elems);
	}

	@Override
	public void setContainingAttr() {
		addContainingAttr();
	}

	@Override
	public void setRequiredAttr() {
		List<List<QuestionAttribute>> requiredAttributesList = new ArrayList<>();
		List<QuestionAttribute> requiredAttributes = new ArrayList<>();
		requiredAttributes.add(QuestionAttribute.STORE);
		requiredAttributes.add(QuestionAttribute.ANIMAL);

		requiredAttributesList.add(requiredAttributes);

		addRequiredAttributes(requiredAttributesList);

	}

	private Attribute level1Determination() {
		Attribute level1Attribute = null;
		if (UtilitiesHelper.getRandom().nextBoolean()) {
			level1Attribute = storeElement.getAttributeByType(AttributeType.LENGTH);
			storeElement.getAttributeByType(AttributeType.WIDTH).setIsDistractor(false);
			storeElement.getAttributeByType(AttributeType.AREA).setIsDistractor(false);

		} else {
			level1Attribute = storeElement.getAttributeByType(AttributeType.WIDTH);
			storeElement.getAttributeByType(AttributeType.LENGTH).setIsDistractor(false);
			storeElement.getAttributeByType(AttributeType.AREA).setIsDistractor(false);
		}
		firstVal = DifficultyAdjustmentUtils.getMultiplierBasedOnDifficulty(DifficultyOverviewHelper.STORE_BOUND_LVL1,
				Operators.MULTIPLICATION);
		return level1Attribute;
	}

	private Attribute level2Determination() {
		Attribute level2Attribute = null;
		level2Attribute = storeElement.getAttributeByType(AttributeType.AREA);
		firstVal = DifficultyAdjustmentUtils.getMultiplierBasedOnDifficulty(DifficultyOverviewHelper.STORE_BOUND_LVL2,
				Operators.MULTIPLICATION);
		storeElement.getAttributeByType(AttributeType.LENGTH).setIsDistractor(false);
		storeElement.getAttributeByType(AttributeType.WIDTH).setIsDistractor(false);
		return level2Attribute;
	}

	private Attribute level3Determination() {
		Attribute level3Attribute = null;

		if (WordProblem.getInstance().getSettings().getDecimalEnabled() == 1) {
			level3Attribute = storeElement.getAttributeByType(AttributeType.VOLUME);
			firstVal = (int) (DifficultyAdjustmentUtils.getPercentageMultiplierBasedOnDifficulty(
					Operators.MULTIPLICATION, DifficultyOverviewHelper.STORE_BOUND_LVL3_PERCENT_LOWER,
					DifficultyOverviewHelper.STORE_BOUND_LVL3_PERCENT_UPPER) / 10);
			firstVal = firstVal / 10;
			storeElement.getAttributeByType(AttributeType.LENGTH).setIsDistractor(false);
			storeElement.getAttributeByType(AttributeType.WIDTH).setIsDistractor(false);
			storeElement.getAttributeByType(AttributeType.HEIGHT).setIsDistractor(false);

		} else {
			level3Attribute = storeElement.getAttributeByType(AttributeType.AREA);
			firstVal = DifficultyAdjustmentUtils.getMultiplierBasedOnDifficulty(
					DifficultyOverviewHelper.STORE_BOUND_LVL2, Operators.MULTIPLICATION);
			storeElement.getAttributeByType(AttributeType.LENGTH).setIsDistractor(false);
			storeElement.getAttributeByType(AttributeType.WIDTH).setIsDistractor(false);
		}

		return level3Attribute;
	}

	/**
	 * A certain store attribute will be chosen based on randomness and difficulty
	 * settings
	 * 
	 * @return The chosen attribute
	 */
	private Attribute chooseStoreAttribute() {
		Attribute storeAttribute = null;

		// If template is on level 1, a random length or width attribute is chosen
		if (level == 1) {
			storeAttribute = level1Determination();

			// If template is on level 2, the area attribute is chosen
		} else if (level == 2) {
			storeAttribute = level2Determination();
			// If template is on level 3, the volume attribute is chosen if decimal
			// calculations are allowed
		} else if (level == 3) {
			storeAttribute = level3Determination();

		} else {
			storeAttribute = storeElement.getAttributeByType(AttributeType.WIDTH);
		}

		AttributeHandler.getInstance().setAmountOfAnimalsPer(firstVal);

		float newAnimalCountVal = (int) (firstVal * storeAttribute.getNumericalValue());

		if (newBuildingElement != null) {
			Attribute multiplierElement = null;
			if (storeAttribute.getAttributeType() == AttributeType.AREA) {
				multiplierElement = newBuildingElement.getAttributeByType(AttributeType.AREA);
			} else if (storeAttribute.getAttributeType() == AttributeType.WIDTH) {
				multiplierElement = newBuildingElement.getAttributeByType(AttributeType.WIDTH);
			} else if (storeAttribute.getAttributeType() == AttributeType.LENGTH) {
				multiplierElement = newBuildingElement.getAttributeByType(AttributeType.LENGTH);
			} else if (storeAttribute.getAttributeType() == AttributeType.VOLUME) {
				multiplierElement = newBuildingElement.getAttributeByType(AttributeType.VOLUME);
			} else {
				multiplierElement = newBuildingElement.getAttributeByType(AttributeType.LENGTH);
			}

			newBuildingElement.getAttributeByType(AttributeType.ANIMALCOUNT)
					.setNumericalValue(firstVal * multiplierElement.getNumericalValue());
		}

		// prepare the attributes by setting numerical values and calculations

		storeElement.setAnimalPerArea((int) firstVal);
		Attribute animalAttr = storeElement.getAttributeByType(AttributeType.ANIMALCOUNT);
		animalAttr.setNumericalValue(newAnimalCountVal);
		animalAttr.setNumericalValueText(newAnimalCountVal);
		animalAttr
				.setAttributeCalculation(new AttributeCalculation(storeAttribute, firstVal, Operators.MULTIPLICATION));
		Float[] values = new Float[] { storeAttribute.getNumericalValue(), firstVal };
		animalAttr.getValidCalculations()
				.add(new ValidCalculation(values, Formula.ANIMALPERSIZE, Operators.MULTIPLICATION));
		animalAttr.getAttributeCalculation().getCalculationInformation()
				.setAttributeConcept(AttributeConcept.COMPUTATIONAL_RULES_SKILL);
		animalAttr.setCanBeAsked(true);
		animalAttr.setIsVisible(false);

		storeAttribute.setIsBlocked(true);

		storeAttribute.setIsDistractor(false);
		storeAttribute.setIsVisible(false);

		AttributeUtils.changeAttributeCalculation(storeAttribute, storeElement);

		if (newBuildingElement != null) {
			newBuildingElement.getAttributeByType(AttributeType.ANIMALCOUNT).setIsVisible(false);

		}

		return storeAttribute;
	}

	@Override
	public QuestionText constructQuestion() {
		Attribute storeAttribute = chooseStoreAttribute();
		storeElement.setChosenAttribute(storeAttribute);
		questionText = new QuestionText();
		questionText.addTextLine(" ");
		questionText.addElementLine(storeElement, PositionType.START);
		questionText.addTextLine(" bringt pro ");
		questionText.addTextLine(storeAttribute.getUnit());
		questionText.addTextLine(" ");
		questionText.addTextLine(storeAttribute.getName());
		questionText.addTextLine(" ");
		questionText.addTextLine(QuestionTextHelper.fixFloatRepresentation(firstVal));
		questionText.addTextLine(" ");
		questionText.addElementLine(animalElement, PositionType.PLURAL);
		questionText.addTextLine(" unter.");

		return questionText;
	}

	@Override
	public void callFinishTemplate() {
		this.storeElement = (StoreElement) elementsMap.get(QuestionAttribute.STORE).get(0);
		if (!elementsMap.get(QuestionAttribute.BUILD).isEmpty()) {
			this.newBuildingElement = (NewBuildingElement) elementsMap.get(QuestionAttribute.BUILD).get(0);
		}
		this.animalElement = (AnimalElement) elementsMap.get(QuestionAttribute.ANIMAL).get(0);
		finishTemplate();
		EnumMap<QuestionAttribute, Element> buildingAttributes = new EnumMap<>(QuestionAttribute.class);
		buildingAttributes.put(QuestionAttribute.STORE, storeElement);

		QuestionHandler.getInstance().getQuestionTypeFlags().put(QuestionHandler.QuestionType.ANIMALPERAREA,
				buildingAttributes);

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
		setTemplateType(Templates.CALCULATE_AREA_OF_STORE);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public static Map<Templates, Boolean> getTemplateCompatibilityMap() {
		return templateCompatibilityMap;
	}

}
