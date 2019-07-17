package com.learningplatform.learningplatform.attributes;

import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeType;
import com.learningplatform.learningplatform.types.CalculationStep;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;
import com.learningplatform.learningplatform.utilities.UtilitiesHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import learningplatform.learningplatform.Calculation.ValidCalculation;

public class FormularUtils {

	public enum Formula {
		REPLACEMENT, AREA, WIDTH, LENGTH, VOLUME, ANIMALPERSIZE, FOODCALC, FOODCALC2, BUYINGFOODCALC,
		BUYINGMATERIALCALC, ALLANIMALS, ALLMONEY, CUSTOMERSPENDING, INCOMEVISITORS, PROFIT, PROFITPERCENTAGE, INCOME,
		EXPENSES,
	}

	private static boolean formulaArea(List<Attribute> attributes, List<CalculationStep> calculationSteps) {
		boolean width = containsRequiredAttribute(AttributeType.WIDTH, attributes);
		boolean length = containsRequiredAttribute(AttributeType.LENGTH, attributes);
		boolean tempOperator = containsRequiredOperator(calculationSteps, Operators.MULTIPLICATION);

		return (width && length && tempOperator);
	}

	private static boolean formulaWidth(List<Attribute> attributes, List<CalculationStep> calculationSteps) {

		boolean length = containsRequiredAttribute(AttributeType.LENGTH, attributes);
		boolean area = containsRequiredAttribute(AttributeType.AREA, attributes);
		boolean tempOperator = containsRequiredOperator(calculationSteps, Operators.DIVISION);
		boolean correctOrdering = false;
		if (length && area) {
			Optional<Attribute> firstValueAttr = getAttributeByTypeAndList(attributes, AttributeType.AREA);
			Optional<Attribute> secondValueAttr = getAttributeByTypeAndList(attributes, AttributeType.LENGTH);

			if (firstValueAttr.isPresent() && secondValueAttr.isPresent()) {
				correctOrdering = checkOrdering(calculationSteps, firstValueAttr.get().getNumericalValue(),
						secondValueAttr.get().getNumericalValue());
			} else {
				correctOrdering = false;
			}
		}
		return (length && area && tempOperator && correctOrdering);
	}

	private static boolean formulaLength(List<Attribute> attributes, List<CalculationStep> calculationSteps) {

		boolean width = containsRequiredAttribute(AttributeType.WIDTH, attributes);
		boolean area = containsRequiredAttribute(AttributeType.AREA, attributes);
		boolean tempOperator = containsRequiredOperator(calculationSteps, Operators.DIVISION);
		boolean correctOrdering = false;
		if (width && area) {

			Optional<Attribute> firstValueAttr = getAttributeByTypeAndList(attributes, AttributeType.AREA);
			Optional<Attribute> secondValueAttr = getAttributeByTypeAndList(attributes, AttributeType.WIDTH);

			if (firstValueAttr.isPresent() && secondValueAttr.isPresent()) {
				correctOrdering = checkOrdering(calculationSteps, firstValueAttr.get().getNumericalValue(),
						secondValueAttr.get().getNumericalValue());
			} else {
				correctOrdering = false;
			}

		}

		return (width && area && tempOperator && correctOrdering);
	}

	private static boolean formulaVolume(List<Attribute> attributes, List<CalculationStep> calculationSteps) {

		boolean width = containsRequiredAttribute(AttributeType.WIDTH, attributes);
		boolean length = containsRequiredAttribute(AttributeType.LENGTH, attributes);
		boolean height = containsRequiredAttribute(AttributeType.HEIGHT, attributes);
		boolean tempOperator = containsRequiredOperator(calculationSteps, Operators.MULTIPLICATION);
		return (width && length && height && tempOperator);
	}

	private static boolean formulaReplacement(ValidCalculation validCalculation, List<Attribute> attributes,
			List<CalculationStep> calculationSteps) {

		boolean containsRelevantType = containsRequiredAttribute(validCalculation.getRelevantAttributeType(),
				attributes);
		boolean tempOperator = containsRequiredOperator(calculationSteps, validCalculation.getOperator());

		return (containsRelevantType && tempOperator);
	}

	private static boolean formulaFoodCalc(List<Attribute> attributes, List<CalculationStep> calculationSteps) {
		boolean animalCount = containsRequiredAttribute(AttributeType.ANIMALCOUNT, attributes);
		boolean foodreq = containsRequiredAttribute(AttributeType.FOODREQUIREMENT, attributes);
		boolean tempOperator = containsRequiredOperator(calculationSteps, Operators.MULTIPLICATION);

		return (animalCount && foodreq && tempOperator);

	}

	private static boolean formulaFoodCalc2(List<Attribute> attributes, List<CalculationStep> calculationSteps) {
		boolean animalCount = containsRequiredAttribute(AttributeType.ANIMALCOUNT, attributes);
		boolean foodreq = containsRequiredAttribute(AttributeType.FOODREQUIREMENT, attributes);
		boolean tempOperator = containsRequiredOperator(calculationSteps, Operators.MULTIPLICATION);
		boolean tempOperator2 = containsRequiredOperator(calculationSteps, Operators.ADDITION);

		return (animalCount && foodreq && tempOperator && tempOperator2);

	}

	private static boolean formulaBuyingFood(List<Attribute> attributes, List<CalculationStep> calculationSteps) {
		boolean animalCount = containsRequiredAttribute(AttributeType.ANIMALCOUNT, attributes);
		boolean foodreq = containsRequiredAttribute(AttributeType.FOODREQUIREMENT, attributes);
		boolean price = containsRequiredAttribute(AttributeType.PRICE, attributes);
		boolean tempOperator = containsRequiredOperator(calculationSteps, Operators.MULTIPLICATION);

		return (animalCount && foodreq && price && tempOperator);

	}

	private static boolean formulaBuyingMaterial(List<Attribute> attributes, List<CalculationStep> calculationSteps) {
		boolean area = containsRequiredAttribute(AttributeType.AREA, attributes);
		boolean price = containsRequiredAttribute(AttributeType.PRICE, attributes);
		boolean tempOperator = containsRequiredOperator(calculationSteps, Operators.MULTIPLICATION);

		return (area && price && tempOperator);

	}

	private static boolean formulaAllAnimals(List<Attribute> attributes, List<CalculationStep> calculationSteps) {
		boolean animalCount = containsRequiredAttribute(AttributeType.ANIMALCOUNT, attributes);
		boolean tempOperator = containsRequiredOperator(calculationSteps, Operators.ADDITION);

		return (animalCount && tempOperator);

	}

	private static boolean formulaAllMoney(List<Attribute> attributes, List<CalculationStep> calculationSteps) {
		boolean expensesFood = containsRequiredAttribute(AttributeType.EXPENSEFOOD, attributes);
		boolean expensesConstruction = containsRequiredAttribute(AttributeType.EXPENSECONSTRUCTION, attributes);
		boolean tempOperator = containsRequiredOperator(calculationSteps, Operators.ADDITION);

		return (expensesFood && expensesConstruction && tempOperator);

	}

	private static boolean formulaIncomeVisitors(List<Attribute> attributes, List<CalculationStep> calculationSteps) {
		boolean population = containsRequiredAttribute(AttributeType.POPULATION, attributes);
		boolean customerSpending = containsRequiredAttribute(AttributeType.CUSTOMERSPENDING, attributes);
		boolean tempOperator = containsRequiredOperator(calculationSteps, Operators.MULTIPLICATION);

		return (population && customerSpending && tempOperator);

	}

	private static boolean formulaProfit(List<Attribute> attributes, List<CalculationStep> calculationSteps) {
		boolean foodExpenses = containsRequiredAttribute(AttributeType.EXPENSEFOOD, attributes);
		boolean materialExpenses = containsRequiredAttribute(AttributeType.EXPENSECONSTRUCTION, attributes);
		boolean incomeVisitors = containsRequiredAttribute(AttributeType.INCOMEVISITORS, attributes);
		boolean tempOperator = containsRequiredOperator(calculationSteps, Operators.SUBSTRACTION);

		return ((foodExpenses || materialExpenses) && incomeVisitors && tempOperator);

	}

	private static boolean formulaExpenses(List<Attribute> attributes, List<CalculationStep> calculationSteps) {
		boolean foodExpenses = containsRequiredAttribute(AttributeType.EXPENSEFOOD, attributes);
		boolean materialExpenses = containsRequiredAttribute(AttributeType.EXPENSECONSTRUCTION, attributes);
		boolean tempOperator = containsRequiredOperator(calculationSteps, Operators.ADDITION);

		return ((foodExpenses || materialExpenses) && tempOperator);
	}

	/**
	 * Checks if the calculation by the student uses formulas correctly.
	 * 
	 * @param validCalculations The valid calculations of an attribute
	 * @param attributes        attributes that need to be in the calculation for it
	 *                          to be valid
	 * @param calculationSteps  the CalculatioNSteps constructed by the student
	 * @return boolean if the student constructed valid calculations
	 */
	public static boolean checkIfFormularCorrect(List<ValidCalculation> validCalculations, List<Attribute> attributes,
			List<CalculationStep> calculationSteps) {

		Formula formula;

		for (ValidCalculation validCalculation : validCalculations) {
			formula = validCalculation.getFormular();

			if (formula == Formula.AREA && formulaArea(attributes, calculationSteps)) {
				return true;

			} else if (formula == Formula.WIDTH && formulaWidth(attributes, calculationSteps)) {
				return true;

			} else if (formula == Formula.LENGTH && formulaLength(attributes, calculationSteps)) {
				return true;

			} else if (formula == Formula.VOLUME && formulaVolume(attributes, calculationSteps)) {
				return true;

			} else if ((formula == Formula.REPLACEMENT || formula == Formula.ANIMALPERSIZE)
					&& formulaReplacement(validCalculation, attributes, calculationSteps)) {
				return true;

			} else if (formula == Formula.FOODCALC && formulaFoodCalc(attributes, calculationSteps)) {
				return true;

			} else if (formula == Formula.FOODCALC2 && formulaFoodCalc2(attributes, calculationSteps)) {
				return true;

			} else if (formula == Formula.BUYINGFOODCALC && formulaBuyingFood(attributes, calculationSteps)) {
				return true;

			} else if (formula == Formula.BUYINGMATERIALCALC && formulaBuyingMaterial(attributes, calculationSteps)) {
				return true;

			} else if (formula == Formula.ALLANIMALS && formulaAllAnimals(attributes, calculationSteps)) {

				return true;

			} else if (formula == Formula.ALLMONEY && formulaAllMoney(attributes, calculationSteps)) {
				return true;

			} else if (formula == Formula.INCOMEVISITORS && formulaIncomeVisitors(attributes, calculationSteps)) {
				return true;

			} else if ((formula == Formula.PROFIT || formula == Formula.PROFITPERCENTAGE)
					&& (formulaProfit(attributes, calculationSteps))) {
				return true;

			} else if (formula == Formula.EXPENSES && (formulaExpenses(attributes, calculationSteps))) {
				return true;

			}

		}

		return false;

	}

	// checks the order of the attributes in a calculation. Relevant for area
	// calculations.
	private static boolean checkOrdering(List<CalculationStep> calcSteps, float firstValue, float secondValue) {
		if (firstValue == secondValue) {
			return true;
		}
		int firstIndex = 0;
		int secondIndex = 0;
		for (int i = 0; i < calcSteps.size(); i++) {
			if (calcSteps.get(i).getOperator() == null) {
				if (calcSteps.get(i).getValue() == firstValue) {
					firstIndex = i;
				}
				if (calcSteps.get(i).getValue() == secondValue) {
					secondIndex = i;
				}
			}

		}
		return firstIndex < secondIndex;
	}

	// work around method to extract an attribute of a certain type. Better to use
	// map.
	private static Optional<Attribute> getAttributeByTypeAndList(List<Attribute> attributes,
			AttributeType attributeType) {
		for (Attribute attribute : attributes) {
			if (attribute.getAttributeType() == attributeType) {
				return Optional.of(attribute);
			}
		}
		return Optional.empty();
	}

	// check if the required operators are included in the calculation steps
	private static boolean containsRequiredOperator(List<CalculationStep> calcSteps, Operators operator) {
		for (CalculationStep calcStep : calcSteps) {
			if (calcStep.getOperator() != null
					&& calcStep.getOperator().equals(UtilitiesHelper.getOperatorString(operator))) {
				return true;
			}
		}
		return false;
	}

	// checks if the attribute list contains a certain attribute type. Better to use
	// maps and contain.
	private static boolean containsRequiredAttribute(AttributeType attributeType, List<Attribute> attributes) {

		for (Attribute attribute : attributes) {

			if (attribute.getAttributeType() == attributeType) {
				return true;
			}
		}

		return false;

	}

	/**
	 * checks if the correct values that need to be in the calculation are present.
	 * Ignores if the attributes are in the calculation.
	 * 
	 * @param validCalculations The valid calculations of an attribute
	 * @param attributes        attributes that need to be in the calculation for it
	 *                          to be valid
	 * @param calculationSteps  the CalculatioNSteps constructed by the student
	 * @return
	 */
	public static boolean checkIfCorrectValuesPresent(List<ValidCalculation> validCalculations,
			List<CalculationStep> calculationSteps) {

		// counts how many of the necessary values are in the calculation
		int valueCount = 0;
		List<Float> positiveFinds;
		List<Float> attributeValues;

		for (ValidCalculation validCalculation : validCalculations) {
			positiveFinds = new ArrayList<>();
			attributeValues = new ArrayList<>(Arrays.asList(validCalculation.getAttributeValues()));

			for (CalculationStep calcStep : calculationSteps) {
				if (calcStep.getOperator() == null) {
					if (attributeValues.contains(calcStep.getValue())) {
						attributeValues.remove(calcStep.getValue());
						positiveFinds.add(calcStep.getValue());
					}
					valueCount++;
				}
			}
			// compares how many correct values were found with the amount of necessary
			// correct values that are included in a valid calculation
			if (positiveFinds.size() == validCalculation.getAttributeValues().length && positiveFinds.size() == valueCount) {
				return true;
			}
		}
		return false;
	}

}
