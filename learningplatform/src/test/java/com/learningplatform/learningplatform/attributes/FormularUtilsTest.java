package com.learningplatform.learningplatform.attributes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.attributes.FormularUtils.Formula;
import com.learningplatform.learningplatform.attributes.FormularUtils;
import com.learningplatform.learningplatform.models.Store;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.templates.Elements.StoreElement;
import com.learningplatform.learningplatform.types.CalculationStep;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

import learningplatform.learningplatform.calculation.ValidCalculation;

public class FormularUtilsTest {

	List<Attribute> attributes;
	List<CalculationStep> calculationSteps;

	Element element;
	EnumMap<AttributeType, Attribute> map;
	Store store;
	Attribute areaAttribute;
	Attribute lengthAttribute;
	Attribute widthAttribute;
	Attribute volumeAttribute;
	Attribute heightAttribute;
	Attribute foodReqAttribute;
	Attribute animalAttribute;
	Attribute priceAttribute;
	Attribute expensesFoodAttribute;
	Attribute expensesConstructionAttribute;
	Attribute incomeVisitors;
	Attribute populationAttribute;
	Attribute customerSpendingAttribute;

	List<ValidCalculation> validCalcs;

	@Before
	public void setUp() throws Exception {

		store = new Store("", "", "", "", "", 5, 5, 5, 5, 5);
		element = new StoreElement(store, 1);
		map = new EnumMap<>(AttributeType.class);

		attributes = new ArrayList<>();
		calculationSteps = new ArrayList<>();
		validCalcs = new ArrayList<ValidCalculation>();

		element.setAttributeMap(map);
	}

	@Test
	public void formulaAreaTest() {
		widthAttribute = new Attribute("test", "", 10, element, AttributeType.WIDTH);
		lengthAttribute = new Attribute("test", "", 10, element, AttributeType.LENGTH);

		attributes.add(widthAttribute);
		attributes.add(lengthAttribute);

		CalculationStep widthStep = new CalculationStep("width", widthAttribute.getNumericalValue(), null);
		CalculationStep operatorStep = new CalculationStep("*", 0.0f, "*");
		CalculationStep lengthStep = new CalculationStep("lengthAttribute.getNumericalValue()",
				lengthAttribute.getNumericalValue(), null);

		calculationSteps.add(widthStep);
		calculationSteps.add(operatorStep);
		calculationSteps.add(lengthStep);
		validCalcs.add(new ValidCalculation(null, Formula.AREA, Operators.MULTIPLICATION));

		assertTrue(FormularUtils.checkIfFormularCorrect(validCalcs, attributes, calculationSteps));

	}

	@Test
	public void formulaWidthTestDifferent() {
		areaAttribute = new Attribute("test", "", 10, element, AttributeType.AREA);
		lengthAttribute = new Attribute("test", "", 9, element, AttributeType.LENGTH);

		attributes.add(areaAttribute);
		attributes.add(lengthAttribute);

		CalculationStep areaStep = new CalculationStep("area", areaAttribute.getNumericalValue(), null);
		CalculationStep operatorStep = new CalculationStep("/", 0.0f, "/");
		CalculationStep lengthStep = new CalculationStep("length", lengthAttribute.getNumericalValue(), null);

		calculationSteps.add(areaStep);
		calculationSteps.add(operatorStep);
		calculationSteps.add(lengthStep);
		validCalcs.add(new ValidCalculation(null, Formula.WIDTH, Operators.DIVISION));

		assertTrue(FormularUtils.checkIfFormularCorrect(validCalcs, attributes, calculationSteps));

	}

	@Test
	public void formulaWidthTestSame() {
		areaAttribute = new Attribute("test", "", 10, element, AttributeType.AREA);
		lengthAttribute = new Attribute("test", "", 10, element, AttributeType.LENGTH);

		attributes.add(areaAttribute);
		attributes.add(lengthAttribute);

		CalculationStep areaStep = new CalculationStep("area", areaAttribute.getNumericalValue(), null);
		CalculationStep operatorStep = new CalculationStep("/", 0.0f, "/");
		CalculationStep lengthStep = new CalculationStep("length", lengthAttribute.getNumericalValue(), null);

		calculationSteps.add(areaStep);
		calculationSteps.add(operatorStep);
		calculationSteps.add(lengthStep);
		validCalcs.add(new ValidCalculation(null, Formula.WIDTH, Operators.DIVISION));

		assertTrue(FormularUtils.checkIfFormularCorrect(validCalcs, attributes, calculationSteps));

	}

	@Test
	public void formulaLengthTest() {
		areaAttribute = new Attribute("test", "", 10, element, AttributeType.AREA);
		widthAttribute = new Attribute("test", "", 10, element, AttributeType.WIDTH);

		attributes.add(areaAttribute);
		attributes.add(widthAttribute);

		CalculationStep areaStep = new CalculationStep("area", areaAttribute.getNumericalValue(), null);
		CalculationStep operatorStep = new CalculationStep("/", 0.0f, "/");
		CalculationStep widthStep = new CalculationStep("width", widthAttribute.getNumericalValue(), null);

		calculationSteps.add(areaStep);
		calculationSteps.add(operatorStep);
		calculationSteps.add(widthStep);
		validCalcs.add(new ValidCalculation(null, Formula.LENGTH, Operators.DIVISION));

		assertTrue(FormularUtils.checkIfFormularCorrect(validCalcs, attributes, calculationSteps));

	}

	@Test
	public void formulaVolumeTest() {
		widthAttribute = new Attribute("test", "", 10, element, AttributeType.WIDTH);
		lengthAttribute = new Attribute("test", "", 10, element, AttributeType.LENGTH);
		heightAttribute = new Attribute("test", "", 10, element, AttributeType.HEIGHT);

		attributes.add(widthAttribute);
		attributes.add(lengthAttribute);
		attributes.add(heightAttribute);

		CalculationStep widthStep = new CalculationStep("width", widthAttribute.getNumericalValue(), null);
		CalculationStep operatorStep = new CalculationStep("*", 0.0f, "*");
		CalculationStep lengthStep = new CalculationStep("length", lengthAttribute.getNumericalValue(), null);
		CalculationStep operatorStep2 = new CalculationStep("*", 0.0f, "*");
		CalculationStep heightStep = new CalculationStep("height", heightAttribute.getNumericalValue(), null);

		calculationSteps.add(widthStep);
		calculationSteps.add(operatorStep);
		calculationSteps.add(lengthStep);
		calculationSteps.add(operatorStep2);
		calculationSteps.add(heightStep);

		validCalcs.add(new ValidCalculation(null, Formula.VOLUME, Operators.MULTIPLICATION));

		assertTrue(FormularUtils.checkIfFormularCorrect(validCalcs, attributes, calculationSteps));

	}

	@Test
	public void formulaReplacementTest() {
		areaAttribute = new Attribute("test", "", 10, element, AttributeType.AREA);

		attributes.add(areaAttribute);

		CalculationStep areaStep = new CalculationStep("area", areaAttribute.getNumericalValue(), null);
		CalculationStep operatorStep = new CalculationStep("*", 0.0f, "*");

		calculationSteps.add(areaStep);
		calculationSteps.add(operatorStep);
		validCalcs.add(new ValidCalculation(null, AttributeType.AREA, Formula.REPLACEMENT, Operators.MULTIPLICATION));

		assertTrue(FormularUtils.checkIfFormularCorrect(validCalcs, attributes, calculationSteps));

	}

	@Test
	public void formulaFoodCalcTest() {
		animalAttribute = new Attribute("test", "", 10, element, AttributeType.ANIMALCOUNT);
		foodReqAttribute = new Attribute("test", "", 10, element, AttributeType.FOODREQUIREMENT);

		attributes.add(animalAttribute);
		attributes.add(foodReqAttribute);

		CalculationStep animalStep = new CalculationStep("dog", animalAttribute.getNumericalValue(), null);
		CalculationStep operatorStep = new CalculationStep("*", 0.0f, "*");
		CalculationStep foodReqStep = new CalculationStep("food", foodReqAttribute.getNumericalValue(), null);

		calculationSteps.add(animalStep);
		calculationSteps.add(operatorStep);
		calculationSteps.add(foodReqStep);
		validCalcs.add(new ValidCalculation(null, Formula.FOODCALC, Operators.MULTIPLICATION));

		assertTrue(FormularUtils.checkIfFormularCorrect(validCalcs, attributes, calculationSteps));

	}

	@Test
	public void formulaBuyingFoodTest() {
		animalAttribute = new Attribute("test", "", 10, element, AttributeType.ANIMALCOUNT);
		foodReqAttribute = new Attribute("test", "", 10, element, AttributeType.FOODREQUIREMENT);
		priceAttribute = new Attribute("test", "", 10, element, AttributeType.PRICE);

		attributes.add(animalAttribute);
		attributes.add(foodReqAttribute);
		attributes.add(priceAttribute);

		CalculationStep animalStep = new CalculationStep("dog", animalAttribute.getNumericalValue(), null);
		CalculationStep operatorStep = new CalculationStep("*", 0.0f, "*");
		CalculationStep foodReqStep = new CalculationStep("food", foodReqAttribute.getNumericalValue(), null);
		CalculationStep operatorStep2 = new CalculationStep("*", 0.0f, "*");
		CalculationStep priceStep = new CalculationStep("dollars", priceAttribute.getNumericalValue(), null);

		calculationSteps.add(animalStep);
		calculationSteps.add(operatorStep);
		calculationSteps.add(foodReqStep);
		calculationSteps.add(operatorStep2);
		calculationSteps.add(priceStep);

		validCalcs.add(new ValidCalculation(null, Formula.BUYINGFOODCALC, Operators.MULTIPLICATION));

		assertTrue(FormularUtils.checkIfFormularCorrect(validCalcs, attributes, calculationSteps));

	}

	@Test
	public void formulaBuyingMaterialTest() {
		areaAttribute = new Attribute("test", "", 10, element, AttributeType.AREA);
		priceAttribute = new Attribute("test", "", 10, element, AttributeType.PRICE);

		attributes.add(areaAttribute);
		attributes.add(priceAttribute);

		CalculationStep areaStep = new CalculationStep("dog", areaAttribute.getNumericalValue(), null);
		CalculationStep operatorStep = new CalculationStep("*", 0.0f, "*");
		CalculationStep priceStep = new CalculationStep("dollars", priceAttribute.getNumericalValue(), null);

		calculationSteps.add(areaStep);
		calculationSteps.add(operatorStep);
		calculationSteps.add(priceStep);

		validCalcs.add(new ValidCalculation(null, Formula.BUYINGMATERIALCALC, Operators.MULTIPLICATION));

		assertTrue(FormularUtils.checkIfFormularCorrect(validCalcs, attributes, calculationSteps));

	}

	@Test
	public void formulaAllAnimalsTest() {
		animalAttribute = new Attribute("test", "", 10, element, AttributeType.ANIMALCOUNT);

		attributes.add(animalAttribute);
		attributes.add(animalAttribute);

		CalculationStep animalStep1 = new CalculationStep("dogs", 9, null);
		CalculationStep operatorStep = new CalculationStep("+", 0.0f, "+");
		CalculationStep animalStep2 = new CalculationStep("dogs", 10, null);

		calculationSteps.add(animalStep1);
		calculationSteps.add(operatorStep);
		calculationSteps.add(animalStep2);

		validCalcs.add(new ValidCalculation(null, Formula.ALLANIMALS, Operators.ADDITION));

		assertTrue(FormularUtils.checkIfFormularCorrect(validCalcs, attributes, calculationSteps));

	}

	@Test
	public void formulaAllMoneyTest() {
		expensesFoodAttribute = new Attribute("test", "", 10, element, AttributeType.EXPENSEFOOD);
		expensesConstructionAttribute = new Attribute("test", "", 10, element, AttributeType.EXPENSECONSTRUCTION);

		attributes.add(expensesFoodAttribute);
		attributes.add(expensesConstructionAttribute);

		CalculationStep expenseFoodStep = new CalculationStep("Food", 9, null);
		CalculationStep operatorStep = new CalculationStep("+", 0.0f, "+");
		CalculationStep expenseConstructionStep = new CalculationStep("Material", 10, null);

		calculationSteps.add(expenseFoodStep);
		calculationSteps.add(operatorStep);
		calculationSteps.add(expenseConstructionStep);

		validCalcs.add(new ValidCalculation(null, Formula.ALLMONEY, Operators.ADDITION));

		assertTrue(FormularUtils.checkIfFormularCorrect(validCalcs, attributes, calculationSteps));

	}

	@Test
	public void formulaIncomeTest() {
		populationAttribute = new Attribute("test", "", 10, element, AttributeType.POPULATION);
		customerSpendingAttribute = new Attribute("test", "", 10, element, AttributeType.CUSTOMERSPENDING);

		attributes.add(populationAttribute);
		attributes.add(customerSpendingAttribute);

		CalculationStep populationStep = new CalculationStep("pop", 9, null);
		CalculationStep operatorStep = new CalculationStep("*", 0.0f, "*");
		CalculationStep customerSpendingStep = new CalculationStep("money", 10, null);

		calculationSteps.add(populationStep);
		calculationSteps.add(operatorStep);
		calculationSteps.add(customerSpendingStep);

		validCalcs.add(new ValidCalculation(null, Formula.INCOMEVISITORS, Operators.MULTIPLICATION));

		assertTrue(FormularUtils.checkIfFormularCorrect(validCalcs, attributes, calculationSteps));

	}

	@Test
	public void formulaProfitTest() {

		expensesFoodAttribute = new Attribute("test", "", 10, element, AttributeType.EXPENSEFOOD);
		expensesConstructionAttribute = new Attribute("test", "", 10, element, AttributeType.EXPENSECONSTRUCTION);
		incomeVisitors = new Attribute("test", "", 10, element, AttributeType.INCOMEVISITORS);

		attributes.add(expensesFoodAttribute);
		attributes.add(expensesConstructionAttribute);
		attributes.add(incomeVisitors);

		CalculationStep operatorStepBracket1 = new CalculationStep("(", 0.0f, "(");
		CalculationStep expensesFoodStep = new CalculationStep("pop", 9, null);
		CalculationStep operatorStep1 = new CalculationStep("+", 0.0f, "+");
		CalculationStep expensesConstructionAttribute = new CalculationStep("money", 10, null);
		CalculationStep operatorStepBracket2 = new CalculationStep(")", 0.0f, ")");
		CalculationStep operatorStep2 = new CalculationStep("-", 0.0f, "-");
		CalculationStep customerSpendingStep = new CalculationStep("money", 10, null);

		calculationSteps.add(operatorStepBracket1);
		calculationSteps.add(expensesFoodStep);
		calculationSteps.add(operatorStep1);
		calculationSteps.add(expensesConstructionAttribute);
		calculationSteps.add(operatorStepBracket2);
		calculationSteps.add(operatorStep2);
		calculationSteps.add(customerSpendingStep);

		validCalcs.add(new ValidCalculation(null, Formula.PROFIT, Operators.SUBSTRACTION));

		assertTrue(FormularUtils.checkIfFormularCorrect(validCalcs, attributes, calculationSteps));

	}

	@Test
	public void formulaExpenseTest() {
		expensesFoodAttribute = new Attribute("test", "", 10, element, AttributeType.EXPENSEFOOD);
		expensesConstructionAttribute = new Attribute("test", "", 10, element, AttributeType.EXPENSECONSTRUCTION);

		attributes.add(expensesFoodAttribute);
		attributes.add(expensesConstructionAttribute);

		CalculationStep expenseFoodStep = new CalculationStep("food", 9, null);
		CalculationStep operatorStep = new CalculationStep("+", 0.0f, "+");
		CalculationStep expenseConstructionStep = new CalculationStep("material", 0.0f, null);

		calculationSteps.add(expenseFoodStep);
		calculationSteps.add(operatorStep);
		calculationSteps.add(expenseConstructionStep);

		validCalcs.add(new ValidCalculation(null, Formula.EXPENSES, Operators.ADDITION));

		assertTrue(FormularUtils.checkIfFormularCorrect(validCalcs, attributes, calculationSteps));

	}

	@Test
	public void checkIfCorrectValuesPresentTest() {

		Float[] testVals = new Float[] { 20f, 10f };

		widthAttribute = new Attribute("test", "", 20f, element, AttributeType.WIDTH);
		lengthAttribute = new Attribute("test", "", 10f, element, AttributeType.LENGTH);

		attributes.add(widthAttribute);
		attributes.add(lengthAttribute);

		CalculationStep widthStep = new CalculationStep("width", widthAttribute.getNumericalValue(), null);
		CalculationStep operatorStep = new CalculationStep("*", 0.0f, "*");
		CalculationStep lengthStep = new CalculationStep("lengthAttribute.getNumericalValue()",
				lengthAttribute.getNumericalValue(), null);

		calculationSteps.add(widthStep);
		calculationSteps.add(operatorStep);
		calculationSteps.add(lengthStep);

		validCalcs.add(new ValidCalculation(testVals, Formula.AREA, Operators.MULTIPLICATION));

		assertTrue(FormularUtils.checkIfCorrectValuesPresent(validCalcs, calculationSteps));

	}

	@Test
	public void checkIfCorrectValuesPresentTestNegative() {

		Float[] testVals = new Float[] { 20f, 30f };

		widthAttribute = new Attribute("test", "", 30, element, AttributeType.WIDTH);
		lengthAttribute = new Attribute("test", "", 10, element, AttributeType.LENGTH);

		attributes.add(widthAttribute);
		attributes.add(lengthAttribute);

		CalculationStep widthStep = new CalculationStep("width", widthAttribute.getNumericalValue(), null);
		CalculationStep operatorStep = new CalculationStep("*", 0.0f, "*");
		CalculationStep lengthStep = new CalculationStep("lengthAttribute.getNumericalValue()",
				lengthAttribute.getNumericalValue(), null);

		calculationSteps.add(widthStep);
		calculationSteps.add(operatorStep);
		calculationSteps.add(lengthStep);

		validCalcs.add(new ValidCalculation(testVals, Formula.AREA, Operators.MULTIPLICATION));

		assertFalse(FormularUtils.checkIfCorrectValuesPresent(validCalcs, calculationSteps));

	}

}
