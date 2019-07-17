package com.learningplatform.learningplatform.question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.learningplatform.learningplatform.types.CalculationStep;

import learningplatform.learningplatform.Calculation.SingleCalculation;

public class AnswerHandlerUtils {
	/**
	 * Method that prepares the calculation steps in a way that they can be later be
	 * analysed individually
	 * 
	 * @param calcSteps   the calculation steps found in the student's calculation
	 * @param singleCalcs single calculations found in the calculation construction
	 * @return
	 */
	public static float calculationWrapper(List<CalculationStep> calcSteps, List<SingleCalculation> singleCalcs) {
		List<CalculationStep> calculationSteps = new ArrayList<>();

		try {
			for (CalculationStep calcStep : calcSteps) {

				String operator = calcStep.getOperator();
				if (operator == null) {
					operator = "";
				}
				calculationSteps.add(new CalculationStep(calcStep.getVisualText(), calcStep.getValue(), operator));
			}

			List<CalculationStep> splitCalcs = splitCalculationUp(calculationSteps, singleCalcs);

			StringBuilder sb = new StringBuilder();
			for (CalculationStep calcStep : splitCalcs) {
				sb.append(calcStep.getVisualText());
			}

			if (splitCalcs.size() == 1) {
				return splitCalcs.get(0).getValue();
			} else {
				return parseCalculation(splitCalcs, singleCalcs);
			}
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException();
		}

	}

	/**
	 * Parse the calculation by the student and calculate it's sum
	 * 
	 * @param calcSteps   the calculation steps found in the student's calculation
	 * @param singleCalcs single calculations found in the calculation construction
	 * @return sum
	 */
	private static float parseCalculation(List<CalculationStep> calcSteps, List<SingleCalculation> singleCalcs) {

		List<CalculationStep> curatedList = curateCalculationSteps(calcSteps, singleCalcs);

		Iterator<CalculationStep> it = curatedList.iterator();
		CalculationStep calcStep;
		String currentOperator = "";
		float sum = it.next().getValue();

		while (it.hasNext()) {
			calcStep = it.next();
			if (!calcStep.getOperator().isEmpty()) {
				currentOperator = calcStep.getOperator();
			} else if (calcStep.getValue() != 0.0) {
				singleCalcs.add(new SingleCalculation(calcStep.getValue(), sum, currentOperator));
				sum = calculateSum(currentOperator, calcStep.getValue(), sum);
			}
		}
		return sum;
	}

	/**
	 * Splits up the calculation into shorter ones
	 * 
	 * @param calcSteps   the calculation steps found in the student's calculation
	 * @param singleCalcs single calculations found in the calculation construction
	 * @return List of calculation steps that are part of the whole calculation
	 */
	private static List<CalculationStep> splitCalculationUp(List<CalculationStep> calcSteps,
			List<SingleCalculation> singleCalcs) {
		List<CalculationStep> calculationStepsFinal = new ArrayList<>();

		// iteratively go through the calculation steps
		int i = 0;

		while (i < calcSteps.size()) {

			if (calcSteps.get(i).getOperator().equals("(")) {

				List<CalculationStep> splitCalcStep = new ArrayList<>();
				List<CalculationStep> tempCalcSteps = new ArrayList<>(calcSteps);
				int counter = i;
				// Check when brackets occur
				while (!tempCalcSteps.get(counter).getOperator().equals(")")) {
					if (!tempCalcSteps.get(counter).getOperator().equals("(")) {
						splitCalcStep.add(tempCalcSteps.get(counter));
					}
					counter++;
				}
				i = counter;
				// calculate one of the split calculations and replace the original calculation
				// step with it.
				float calculatedSubValue = parseCalculation(splitCalcStep, singleCalcs);
				calculationStepsFinal.add(new CalculationStep("" + calculatedSubValue, calculatedSubValue, ""));
			}
			// if there is no operator and no bracket, add the current number
			if (calcSteps.get(i).getOperator().isEmpty() || !(calcSteps.get(i).getOperator().equals(")"))) {
				calculationStepsFinal.add(calcSteps.get(i));
			}
			i++;
		}

		return calculationStepsFinal;
	}

	private static int curatePlusAndMinus(int i, List<CalculationStep> calcSteps, List<CalculationStep> curatedList) {
		int currentCount = i;
		if (calcSteps.get(currentCount).getOperator().isEmpty()
				&& (calcSteps.get(currentCount + 1).getOperator().equals("+")
						|| calcSteps.get(currentCount + 1).getOperator().equals("-"))) {
			curatedList.add(calcSteps.get(currentCount));
			curatedList.add(calcSteps.get(currentCount + 1));
			currentCount++;
		}
		return currentCount;
	}

	private static int curateMultiplicationAndDivision(List<CalculationStep> calcSteps, int i,
			List<CalculationStep> curatedList, List<SingleCalculation> singleCalcs) {
		int currentCounter = i;
		if (!calcSteps.get(currentCounter).getOperator().isEmpty()
				&& (calcSteps.get(currentCounter).getOperator().equals("*")
						|| calcSteps.get(currentCounter).getOperator().equals("/"))) {
			float firstValue = calcSteps.get(currentCounter - 1).getValue();
			float secondValue = calcSteps.get(currentCounter + 1).getValue();
			float tempSum = calculateSum(calcSteps.get(currentCounter).getOperator(), firstValue, secondValue);
			singleCalcs
					.add(new SingleCalculation(firstValue, secondValue, calcSteps.get(currentCounter).getOperator()));
			currentCounter++;
			currentCounter++;

			// if the following continues with either a * or / operation, create more single
			// calculations with calculated temporary Sum of the last calculation and
			// continue
			while (currentCounter < calcSteps.size() - 1 && !calcSteps.get(currentCounter).getOperator().isEmpty()
					&& (calcSteps.get(currentCounter).getOperator().equals("*")
							|| calcSteps.get(currentCounter).getOperator().equals("/"))) {
				singleCalcs.add(new SingleCalculation(calcSteps.get(currentCounter + 1).getValue(), tempSum,
						calcSteps.get(currentCounter).getOperator()));
				tempSum = calculateSum(calcSteps.get(currentCounter).getOperator(),
						calcSteps.get(currentCounter + 1).getValue(), tempSum);
				currentCounter++;
				currentCounter++;
			}
			curatedList.add(new CalculationStep("" + tempSum, tempSum, ""));

			if (currentCounter < calcSteps.size() && !calcSteps.get(i).getOperator().isEmpty()
					&& (calcSteps.get(currentCounter).getOperator().equals("+")
							|| calcSteps.get(currentCounter).getOperator().equals("-"))) {

				curatedList.add(calcSteps.get(currentCounter));
			}
		}
		return currentCounter;

	}

	/**
	 * Method to prepare the calculation so that it can be parsed
	 * 
	 * @param calcSteps   the calculation steps found in the student's calculation
	 * @param singleCalcs single calculations found in the calculation construction
	 * @return List of curated Calculation Steps
	 */
	public static List<CalculationStep> curateCalculationSteps(List<CalculationStep> calcSteps,
			List<SingleCalculation> singleCalcs) {
		List<CalculationStep> curatedList = new ArrayList<>();

		int i = 0;
		// iterate through the calculation steps
		while (i < calcSteps.size()) {

			if (i != calcSteps.size() - 1) {
				// if there is no operator, but either a + or - in the next step, add i and i++
				// to the curated List
				i = curatePlusAndMinus(i, calcSteps, curatedList);

				// if there is no operator, but either a * or /, create a single calculation
				// containing this operation and continue

				i = curateMultiplicationAndDivision(calcSteps, i, curatedList, singleCalcs);
			} else {
				if (calcSteps.get(i - 1).getOperator().equals("+") || calcSteps.get(i - 1).getOperator().equals("-")) {

					curatedList.add(calcSteps.get(i));
				}
			}
			i++;
		}
		return curatedList;
	}

	/**
	 * @param operator operator used in the operation
	 * @param value    value used in the operation
	 * @param sum      current sum
	 * @return new sum
	 */
	private static float calculateSum(String operator, float value, float sum) {
		float newSum = sum;
		switch (operator) {
		case "+":
			newSum = newSum + value;
			return newSum;
		case "-":
			newSum = newSum - value;
			return newSum;
		case "*":
			newSum = newSum * value;
			return newSum;
		case "/":
			newSum = value / newSum;
			return newSum;
		default:
			return newSum;
		}
	}
}
