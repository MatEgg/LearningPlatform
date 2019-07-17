package com.learningplatform.learningplatform.templates.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.learningplatform.learningplatform.attributes.AnimalCount;
import com.learningplatform.learningplatform.attributes.Area;
import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeConcept;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.Height;
import com.learningplatform.learningplatform.attributes.Length;
import com.learningplatform.learningplatform.attributes.Volume;
import com.learningplatform.learningplatform.attributes.Width;
import com.learningplatform.learningplatform.difficulty.DifficultyAdjustmentUtils;
import com.learningplatform.learningplatform.difficulty.DifficultyOverviewHelper;
import com.learningplatform.learningplatform.models.Store;
import com.learningplatform.learningplatform.models.service.StoreService;
import com.learningplatform.learningplatform.question.QuestionAttribute;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Template;
import com.learningplatform.learningplatform.types.Construction;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

import learningplatform.learningplatform.Calculation.AttributeCalculation;

public class StoreElement extends NumericalElement {

	StoreService storeService;
	Store store;

	Attribute area;
	Attribute length;
	Attribute width;
	Attribute height;
	Attribute volume;

	Attribute animalCount;
	Attribute income;

	Attribute expenseConstruction;
	Attribute expenseFood;
	Attribute incomeVisitors;

	private int animalPerArea = 0;

	private float daysWorthOfFood = 1;
	Attribute chosenAttribute;
	String name;
	private Construction construction;

	public StoreElement(Template template) {
		super(template);
		storeService = WordProblem.getInstance().getDaoFactory().getStoreService();
		float difficulty = DifficultyAdjustmentUtils.getAverageConceptDifficulty() / 100;
		store = storeService.getStoreAreaByPercentage(difficulty);
		random = new Random();
		findElement();
		finishElementConstruction();
	}
	public StoreElement(Store store, int id) {
		super(id);
		this.store = store;
		random = new Random();
		assignNames();
	}

	public StoreElement(Store store, Template template) {
		super(template);
		storeService = WordProblem.getInstance().getDaoFactory().getStoreService();
		this.store = store;
		random = new Random();
		findElement();
		finishElementConstruction();
	}

	@Override
	String elementText() {
		return name;
	}

	public String getTextStartStart() {
		return store.getNameStartStart();
	}

	public String getTextMiddle() {
		return store.getNameMiddle();
	}

	@Override
	String elementTextStart() {
		return store.getNameStart();
	}

	@Override
	String elementTextPlural() {
		return store.getNamePlural();
	}

	@Override
	String replacementString() {
		return store.getName();
	}

	@Override
	void addQuestionAttributes() {
		List<QuestionAttribute> questionAttributes = getQuestionAttributes();
		questionAttributes.add(QuestionAttribute.STORE);
		questionAttributes.add(QuestionAttribute.AREA);
		questionAttributes.add(QuestionAttribute.NUMERICALTHING);
		questionAttributes.add(QuestionAttribute.EXPENSECONSTRUCTION);
		questionAttributes.add(QuestionAttribute.EXPENSEFOOD);
		questionAttributes.add(QuestionAttribute.INCOMEVISITORS);
	}

	@Override
	void findElement() {

		name = store.getName();
		int lengthVal = store.getLength();
		int widthVal = store.getWidth();
		int heightVal = store.getHeight();
		int areaVal = lengthVal * widthVal;
		int volumeVal = lengthVal * widthVal * heightVal;
		int animalCountVal = (int) (store.getAnimalCount() * DifficultyAdjustmentUtils
				.getMultiplierBasedOnDifficulty(DifficultyOverviewHelper.STOREANIMALCOUNT, Operators.MULTIPLICATION));

		area = new Area(areaVal, this, widthVal, lengthVal);
		length = new Length(lengthVal, this, areaVal, widthVal);
		width = new Width(widthVal, this, areaVal, lengthVal);
		height = new Height(heightVal, this);
		volume = new Volume(volumeVal, this, widthVal, lengthVal, heightVal);

		animalCount = new AnimalCount(animalCountVal, this);

		List<Attribute> attributesArea = new ArrayList<>();
		attributesArea.add(length);
		attributesArea.add(width);
		area.setAttributeCalculation(new AttributeCalculation(attributesArea, Operators.MULTIPLICATION));

		area.getAttributeCalculation().getCalculationInformation()
				.setAttributeConcept(AttributeConcept.AREA_CALCULATION_SKILL);

		width.setAttributeCalculation(new AttributeCalculation(width));
		width.getAttributeCalculation().getCalculationInformation()
				.setAttributeConcept(AttributeConcept.AREA_CALCULATION_SKILL);

		length.setAttributeCalculation(new AttributeCalculation(length));
		length.getAttributeCalculation().getCalculationInformation()
				.setAttributeConcept(AttributeConcept.AREA_CALCULATION_SKILL);

		List<Attribute> attributesVolume = new ArrayList<>();
		attributesVolume.add(length);
		attributesVolume.add(width);
		attributesVolume.add(height);

		volume.setAttributeCalculation(new AttributeCalculation(attributesVolume, Operators.MULTIPLICATION));
		volume.getAttributeCalculation().getCalculationInformation()
				.setAttributeConcept(AttributeConcept.AREA_CALCULATION_SKILL);

		animalCount.setAttributeCalculation(new AttributeCalculation(animalCount));

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

		attributeList.add(animalCount);
		attributeTypes.add(AttributeType.ANIMALCOUNT);

		attributeList.add(income);
		attributeTypes.add(AttributeType.INCOME);

		attributeList.add(expenseFood);
		attributeTypes.add(AttributeType.EXPENSEFOOD);

		attributeList.add(expenseConstruction);
		attributeTypes.add(AttributeType.EXPENSECONSTRUCTION);

		attributeList.add(incomeVisitors);
		attributeTypes.add(AttributeType.INCOMEVISITORS);

		attributeMap.put(AttributeType.AREA, area);
		attributeMap.put(AttributeType.LENGTH, length);
		attributeMap.put(AttributeType.WIDTH, width);
		attributeMap.put(AttributeType.HEIGHT, height);
		attributeMap.put(AttributeType.VOLUME, volume);
		attributeMap.put(AttributeType.ANIMALCOUNT, animalCount);
		attributeMap.put(AttributeType.INCOME, income);
		attributeMap.put(AttributeType.EXPENSEFOOD, expenseFood);
		attributeMap.put(AttributeType.EXPENSECONSTRUCTION, expenseConstruction);
		attributeMap.put(AttributeType.INCOMEVISITORS, incomeVisitors);

		setAttributeTypes(attributeTypes);
	}

	/**
	 * Adds a single attribute to the attributes that are stored in this element.
	 * Used to have an associated attribute that is not yet present at instantiation
	 * of a template.
	 * 
	 * @param attribute attribute to be added
	 */
	public void addOneAttribute(Attribute attribute) {

		attributeList.add(attribute);
		attributeTypes.add(attribute.getAttributeType());
		attributeMap.put(attribute.getAttributeType(), attribute);
		setAttributeTypes(attributeTypes);

	}

	public float getDaysWorthOfFood() {
		return daysWorthOfFood;
	}

	public void setDaysWorthOfFood(float daysWorthOfFood) {
		this.daysWorthOfFood = daysWorthOfFood;
	}

	public Construction getConstruction() {
		return construction;
	}

	public void setConstruction(Construction construction) {
		this.construction = construction;
	}

	public Attribute getChosenAttribute() {
		return chosenAttribute;
	}

	public void setChosenAttribute(Attribute chosenAttribute) {
		this.chosenAttribute = chosenAttribute;
	}

	public Attribute getIncome() {
		return income;
	}

	public void setIncome(Attribute income) {
		this.income = income;
	}

	public Attribute getExpenseConstruction() {
		return expenseConstruction;
	}

	public void setExpenseConstruction(Attribute expenseConstruction) {
		this.expenseConstruction = expenseConstruction;
	}

	public Attribute getExpenseFood() {
		return expenseFood;
	}

	public void setExpenseFood(Attribute expenseFood) {
		this.expenseFood = expenseFood;
	}

	public Attribute getIncomeVisitors() {
		return incomeVisitors;
	}

	public void setIncomeVisitors(Attribute incomeVisitors) {
		this.incomeVisitors = incomeVisitors;
	}

	public int getAnimalPerArea() {
		return animalPerArea;
	}

	public void setAnimalPerArea(int animalPerArea) {
		this.animalPerArea = animalPerArea;
	}
}
