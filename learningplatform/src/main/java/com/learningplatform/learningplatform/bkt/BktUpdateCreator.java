package com.learningplatform.learningplatform.bkt;

import com.learningplatform.learningplatform.types.QuestionCalculation;
import com.learningplatform.learningplatform.types.TargetAttribute;

import com.learningplatform.learningplatform.attributes.Attribute;
import com.learningplatform.learningplatform.attributes.AttributeHandler.AttributeConcept;
import com.learningplatform.learningplatform.models.User;
import com.learningplatform.learningplatform.question.AttributeAnswerCheck;
import com.learningplatform.learningplatform.question.QuestionAnswerCheck;
import com.learningplatform.learningplatform.question.QuestionHandler;
import com.learningplatform.learningplatform.tasks.WordProblem;
import com.learningplatform.learningplatform.templates.Elements.Element;
import com.learningplatform.learningplatform.types.AttributeCalculationType;
import com.learningplatform.learningplatform.types.Question;
import com.learningplatform.learningplatform.types.OperatorHelper.Operators;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import learningplatform.learningplatform.Calculation.CalculationInformation;
import learningplatform.learningplatform.Calculation.CompletionInformation;
import learningplatform.learningplatform.Calculation.MostDifficultyCalculation;
import learningplatform.learningplatform.Calculation.SingleCalculation;

/**
 * Handles the creation of update packets.
 *
 */
public class BktUpdateCreator {

	/**
	 * Gather information to create an update packet based on an attribute
	 * calculation submission of a student.
	 * 
	 * @param attributeCalculationType contains information about the attributes
	 *                                 that are involved in the calculation
	 * @param attributeAnswerCheck     contains information about the correctness of
	 *                                 the calculation by the student
	 */
	public void updateByAttributeCalculation(AttributeCalculationType attributeCalculationType,
			AttributeAnswerCheck attributeAnswerCheck) {
		List<SingleCalculation> singleCalcs = attributeCalculationType.getSingleCalculations();
		TargetAttribute targetAttribute = attributeCalculationType.getTargetAttribute();

		Map<Integer, Element> elementMap = WordProblem.getInstance().getElementIdMap();
		Element element = elementMap.get(targetAttribute.getElementId());
		Map<Integer, Attribute> attributeMapp = element.getAttributeIdMap();
		Attribute attribute = attributeMapp.get(targetAttribute.getId());

		boolean correctAnswer = (attributeAnswerCheck.getCorrectAnswer()
				|| attributeAnswerCheck.getCorrectRoundedAnswer()
				|| attributeAnswerCheck.isWrongCalculationButCorrect());

		updateByCalculation(singleCalcs, attributeAnswerCheck.getCorrectCalculation());

		if (!attributeCalculationType.getTargetAttribute().isAlreadyUpdated()) {
			updateByConcept(attribute.getAttributeCalculation().getCalculationInformation(), correctAnswer);
			updateByDistractors(attributeCalculationType.getContainedAttributes(), correctAnswer);
			updateByValueInsertion(attributeAnswerCheck.isCorrectFormular(), attributeAnswerCheck.isCorrectValues(),
					correctAnswer);
		}
	}

	/**
	 * Check if the student used the correct values and formula
	 * 
	 * @param correctFormula boolean if the correct formula was used
	 * @param correctValues  boolean if the correct values were used
	 * @param correctAnswer  boolean if the correct answer was given
	 */
	public void updateByValueInsertion(boolean correctFormula, boolean correctValues, boolean correctAnswer) {
		updateByCorrectValues(correctValues || correctAnswer);
		updateByFormula((correctValues || correctFormula || correctAnswer));
	}

	// check if the student used the correct formula
	private void updateByFormula(boolean correctFormular) {

		BktUpdateConceptual bktUpdateConceptual = new BktUpdateConceptual(
				BktTypeConceptual.CHOOSE_CORRECT_FORMULAS_SKILL, correctFormular);
		BktHandler.getInstance().updateSkillMasteryConceptual(bktUpdateConceptual);

	}

	// check if the student used the correct values
	private void updateByCorrectValues(boolean correctValues) {

		BktUpdateConceptual bktUpdateConceptual = new BktUpdateConceptual(BktTypeConceptual.INSERT_CORRECT_VALUES_SKILL,
				correctValues);
		BktHandler.getInstance().updateSkillMasteryConceptual(bktUpdateConceptual);

	}

	/**
	 * Gather information to create an update packet based on a question calculation
	 * submission of a student.
	 * 
	 * @param questionCalculation contains information about the question that is
	 *                            relevant to the calculation
	 * @param questionAnswerCheck contains information about the correctness of the
	 *                            student's answer
	 */
	public void updateByQuestionCalculation(QuestionCalculation questionCalculation,
			QuestionAnswerCheck questionAnswerCheck) {

		Question question = QuestionHandler.getInstance().getQuestionCatalogue().getQuestionMap()
				.get(questionCalculation.getQuestionId());

		List<SingleCalculation> singleCalcs = questionCalculation.getSingleCalculations();

		updateByCalculation(singleCalcs, questionAnswerCheck.getCorrectQuestionCalculation());

		if (!questionCalculation.isAlreadyUpdated()) {
			updateByConcept(question.getCalculationInformation(), questionAnswerCheck.getCorrectQuestionAnswer());
			updateByDistractors(questionCalculation.getContainedAttributes(),
					questionAnswerCheck.getCorrectQuestionAnswer());
			updateBySolvingCarryTask(question, questionAnswerCheck.getCorrectQuestionAnswer());
			if (!questionAnswerCheck.isAttributeOnly()) {
				updateByValueInsertion(questionAnswerCheck.isCorrectFormular(), questionAnswerCheck.isCorrectValues(),
						questionAnswerCheck.getCorrectQuestionAnswer());
			}
		}

	}

	/**
	 * Gather information about the general behaviour of the student solving a task.
	 * Used after a whole task is done
	 * 
	 * @param completionInformation contains information about the solving process
	 *                              like amounts of help used
	 */
	public void updateByCompletion(CompletionInformation completionInformation) {
		int stepsNeeded = QuestionHandler.getInstance().calculateStepsNeeded();
		int stepsDone = completionInformation.getCalculationStepCount();
		boolean helpUsed = checkIfTooMuchHelp(completionInformation);

		BktUpdateConceptual bktUpdateConceptualFirst = new BktUpdateConceptual(BktTypeConceptual.STEPS_NEEDED_SKILL,
				stepsDone <= stepsNeeded);
		BktUpdateConceptual bktUpdateConceptualSecond = new BktUpdateConceptual(
				BktTypeConceptual.SOLVING_CARRY_TASKS_SKILL, helpUsed);

		BktHandler.getInstance().updateSkillMasteryConceptual(bktUpdateConceptualFirst);
		BktHandler.getInstance().updateSkillMasteryConceptual(bktUpdateConceptualSecond);

	}

	// Checks if the student used too much help
	private boolean checkIfTooMuchHelp(CompletionInformation completionInformation) {
		int autoSolveCount = completionInformation.getAutoSolveCount();
		int autoSolveQuestionCount = completionInformation.getAutoSolveQuestionCount();
		int helpAttributeCount = completionInformation.getUsedHelpCount();
		int helpQuestionCount = completionInformation.getUsedQuestionHelpCount();

		int attributeCount = completionInformation.getCalculationStepCount();
		int questionCount = completionInformation.getQuestionCount();

		float solveAndHelp = autoSolveCount + autoSolveQuestionCount + helpAttributeCount + helpQuestionCount;
		float calculations = attributeCount + questionCount;

		// if in more than half of the calculations help was used, it is deemed as too
		// much help
		if (solveAndHelp / calculations > 0.50) {
			// false means that too much help was used. Change (Unintuitive)
			return false;
		}

		return true;
	}

	// create specific update packet for solving carry tasks related skills
	private void updateBySolvingCarryTask(Question question, boolean result) {
		float stepsNeeded = question.getOptimalSolution().getCalculationStats().getStepsNeeded();
		User currentUser = WordProblem.getInstance().getUser();
		float solvingSkillsLevel = BktHandlerUtils.extractLevel(
				currentUser.getConceptualSkill().getGeneralPerformanceSkill().getSolvingCarryTasksSkill());
		int updateCount = 1;

		if (result) {
			if (stepsNeeded > solvingSkillsLevel) {
				updateCount = 2;
			}
		} else {
			if (stepsNeeded < solvingSkillsLevel / 2) {
				updateCount = 2;
			}
		}

		BktUpdateConceptual bktUpdateConceptual = new BktUpdateConceptual(BktTypeConceptual.SOLVING_CARRY_TASKS_SKILL,
				result);
		for (int i = 0; i < updateCount; i++) {
			BktHandler.getInstance().updateSkillMasteryConceptual(bktUpdateConceptual);
		}

	}

	// create specific update packet for solving operational task related skills
	private void updateByCalculation(List<SingleCalculation> singleCalcs, boolean correctResult) {
		List<BktSingleCalculation> bktSingleCalcs = new ArrayList<>();

		for (SingleCalculation singleCalc : singleCalcs) {
			bktSingleCalcs.add(new BktSingleCalculation(singleCalc));
		}

		List<BktSingleCalculation> bktSingleCalcsAddition;
		List<BktSingleCalculation> bktSingleCalcsSubtraction;

		bktSingleCalcsAddition = bktSingleCalcs.stream().filter(calc -> calc.getOperator() == Operators.ADDITION)
				.collect(Collectors.toList());
		bktSingleCalcsSubtraction = bktSingleCalcs.stream().filter(calc -> calc.getOperator() == Operators.SUBSTRACTION)
				.collect(Collectors.toList());

		List<BktSingleCalculation> bktSingleCalcsMultiplication;
		List<BktSingleCalculation> bktSingleCalcsDivision;

		bktSingleCalcsMultiplication = bktSingleCalcs.stream()
				.filter(calc -> calc.getOperator() == Operators.MULTIPLICATION).collect(Collectors.toList());
		bktSingleCalcsDivision = bktSingleCalcs.stream().filter(calc -> calc.getOperator() == Operators.DIVISION)
				.collect(Collectors.toList());

		List<MostDifficultyCalculation> calculationList = new ArrayList<>();
		findMostDifficultyCalculation(bktSingleCalcsAddition, calculationList, Operators.ADDITION);
		findMostDifficultyCalculation(bktSingleCalcsSubtraction, calculationList, Operators.SUBSTRACTION);
		findMostDifficultyCalculation(bktSingleCalcsMultiplication, calculationList, Operators.MULTIPLICATION);
		findMostDifficultyCalculation(bktSingleCalcsDivision, calculationList, Operators.DIVISION);

		List<BktPotentialOperationalUpdate> potentialUpdates = new ArrayList<>();

		for (MostDifficultyCalculation mostDifficultyCalculation : calculationList) {
			potentialUpdates.add(new BktPotentialOperationalUpdate(correctResult, mostDifficultyCalculation));
		}

		for (BktPotentialOperationalUpdate bktPotentialOperationalUpdate : potentialUpdates) {
			BktHandler.getInstance().updateSkillMasteryOperational(bktPotentialOperationalUpdate);
		}
	}

	// create specific update packet for solving concept related skills
	private void updateByConcept(CalculationInformation calculationInformation, boolean correctResult) {
		AttributeConcept attributeConcept = calculationInformation.getAttributeConcept();
		BktTypeConceptual bktTypeConceptual = null;

		if (attributeConcept != null) {
			switch (attributeConcept) {
			case AREA_CALCULATION_SKILL:
				bktTypeConceptual = BktTypeConceptual.AREA_CALCULATION_SKILL;
				break;
			case CALCULATE_PROBABILITIES_SKILL:
				bktTypeConceptual = BktTypeConceptual.CALCULATE_PROBABILITIES_SKILL;
				break;
			case COMPUTATIONAL_RULES_SKILL:
				bktTypeConceptual = BktTypeConceptual.COMPUTATIONAL_RULES_SKILL;
				break;
			default:
				break;
			}

			if (bktTypeConceptual != null) {
				BktUpdateConceptual bktUpdateConceptual = new BktUpdateConceptual(bktTypeConceptual, correctResult);
				BktHandler.getInstance().updateSkillMasteryConceptual(bktUpdateConceptual);
			}
		}

	}

	// create specific update packet for distractor related skills
	private void updateByDistractors(List<TargetAttribute> cointainedSimpleAttributes, boolean correctAnswer) {
		if (correctAnswer) {
			BktUpdateConceptual bktUpdateConceptual = new BktUpdateConceptual(
					BktTypeConceptual.CHOOSE_CORRECT_INFORMATION_SKILL, true);
			BktHandler.getInstance().updateSkillMasteryConceptual(bktUpdateConceptual);
		} else {
			List<Attribute> containingAttributes = extractAttributesFromTargetAttributes(cointainedSimpleAttributes);

			boolean containsDistractors = false;

			for (Attribute attribute : containingAttributes) {
				if (attribute.getIsDistractor()) {
					containsDistractors = true;
				}
			}

			if (containsDistractors) {
				BktUpdateConceptual bktUpdateConceptual = new BktUpdateConceptual(
						BktTypeConceptual.CHOOSE_CORRECT_INFORMATION_SKILL, false);
				BktHandler.getInstance().updateSkillMasteryConceptual(bktUpdateConceptual);
			}
		}

	}

	// converts the Target Attributes to normal Attributes
	private List<Attribute> extractAttributesFromTargetAttributes(List<TargetAttribute> targetAttributes) {
		List<Attribute> attributes = new ArrayList<>();
		Map<Integer, Element> elementMap = WordProblem.getInstance().getElementIdMap();
		Element element;
		Map<Integer, Attribute> attributeMap;
		Attribute attribute;

		for (TargetAttribute targetAttribute : targetAttributes) {

			element = elementMap.get(targetAttribute.getElementId());
			attributeMap = element.getAttributeIdMap();
			attribute = attributeMap.get(targetAttribute.getId());
			attributes.add(attribute);
		}

		return attributes;
	}

	// Find the most difficulty single calculation of the whole calculation
	private void findMostDifficultyCalculation(List<BktSingleCalculation> bktSingleCalculations,
			List<MostDifficultyCalculation> calculationList, Operators operator) {
		if (bktSingleCalculations.size() > 0) {
			MostDifficultyCalculation mostDifficultyCalculation = new MostDifficultyCalculation(0, 0, 0, operator);
			for (BktSingleCalculation bktSingleCalculation : bktSingleCalculations) {
				if (bktSingleCalculation.getDecimalLevel() > mostDifficultyCalculation.getDecimalLevel()) {
					mostDifficultyCalculation.setDecimalLevel(bktSingleCalculation.getDecimalLevel());
				}

				if (bktSingleCalculation.getBigDigitLevel() > mostDifficultyCalculation.getBigDigitLevel()) {
					mostDifficultyCalculation.setBigDigitLevel((bktSingleCalculation.getBigDigitLevel()));
				}

				if (bktSingleCalculation.getNumericalLevel() > mostDifficultyCalculation.getNumericalLevel()) {
					mostDifficultyCalculation.setNumericalLevel((bktSingleCalculation.getNumericalLevel()));
				}
			}
			calculationList.add(mostDifficultyCalculation);
		}
	}
}
